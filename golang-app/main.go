package main

import (
	"encoding/json"
	"log"
	"net/http"
)

func main() {
	if err := Run(); err != nil {
		log.Fatalf("error starting service: %+v\n", err)
	}
}

func Run() error {
	http.HandleFunc("/health", home)
	if err := http.ListenAndServe(":8000", nil); err != nil {
		return err
	}
	return nil
}

func home(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(map[string]string{
		"status": "UP",
	})
}
