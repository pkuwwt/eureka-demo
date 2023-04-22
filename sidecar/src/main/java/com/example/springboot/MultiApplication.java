package com.example.springboot;

import java.util.AbstractMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.io.IOException;

public class MultiApplication {
	static class SidecarConfig {
		Map<String, String> maps = new HashMap<String, String>();

		static Map.Entry<String, String> parsePair(String s) {
			int index = s.indexOf("=");
			if (index < 0) {
				return new AbstractMap.SimpleEntry<String, String>(s, "");
			}
			return new AbstractMap.SimpleEntry<String, String>(s.substring(0, index), s.substring(index+1));
		}

		public void parse(String config) {
			if (config == null) {
				return;
			}
			Arrays.stream(config.split(";"))
				.map(SidecarConfig::parsePair)
				.forEach(entry -> maps.put(entry.getKey(), entry.getValue()));
		}

		public String[] getArgs() {
			Predicate<Map.Entry<String,String>> isSpringEntry = e -> e.getKey().startsWith("spring.");
			Function<Map.Entry<String,String>, String> getArgPrefix = e -> "--";
			Function<Map.Entry<String,String>, String> getArgString = e -> getArgPrefix.apply(e) + e.getKey() + "=" + e.getValue();
			return this.maps.entrySet().stream()
				.map(getArgString)
				.collect(Collectors.toList())
				.stream().toArray(String[]::new);
		}
		public String toString() {
			String configs = this.maps.entrySet().stream()
				.map(e -> e.getKey() + "=" + e.getValue())
				.collect(Collectors.joining(","));
			return "SidebarConfig[" + configs + "]";
		}
	}

	public static String[] concatenate(String[] ...arrays)
	{
		return Stream.of(arrays)
			.flatMap(Stream::of)        // or, use `Arrays::stream`
			.toArray(String[]::new);
	}

	public static int port = 8080;
	public static int getPort() {
		return port++;
	}

	public static Process createSidecarProcess(SidecarConfig config) {
		String jarPath = JarUtils.getJarFilePath(MultiApplication.class);
		String[] cmd = concatenate(
				new String[]{"java", "-jar", jarPath},
				config.getArgs(),
				new String[]{"--server.port=" + getPort()}
				);
		StringUtils.println(cmd);
		try {
			return ProcessUtils.runCommand(cmd);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<SidecarConfig> parseSidecarConfigs() {
		String base = EnvUtils.getEnv("APP_CONFIG_BASE");
		Predicate<Map.Entry<String,String>> isAppConfig = entry -> {
			return entry.getKey().startsWith("APP_CONFIG_") && !entry.getKey().equals("APP_CONFIG_BASE");
		};
		Function<String, SidecarConfig> merge = conf -> {
			SidecarConfig c = new SidecarConfig();
			c.parse(base);
			c.parse(conf);
			return c;
		};
		List<String> envs = EnvUtils.getAllEnvs()
			.entrySet().stream()
			.filter(isAppConfig)
			.map(Map.Entry::getValue)
			.filter(s -> !s.isEmpty())
			.collect(Collectors.toList());
		return envs.stream().map(merge).collect(Collectors.toList());
	}

	public static void executeSidecars() {
		try {
		    List<Process> processes = parseSidecarConfigs().stream()
		    	.map(MultiApplication::createSidecarProcess)
		    	.filter(s -> s != null)
		    	.collect(Collectors.toList());
		    for (int i=0;i<processes.size(); ++i) {
		    	processes.get(i).waitFor();
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

