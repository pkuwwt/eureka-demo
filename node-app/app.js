const http = require('http');
const appPort = parseInt(process.env.APP_PORT || 3000);

http.createServer(async (req, res) => {
    const {url, method} = req;

    // 测试动态读取数据
    if (method === 'GET' && url === '/') {
        res.end(`你好!`);
    }

}).listen(appPort, '0.0.0.0'); // 192.168.20.193 为我本地的内网 ip，通过 ifconfig 查看
