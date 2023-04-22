
import os
from flask import Flask, jsonify

app = Flask(__name__)

port = 5000
host = os.getenv('APP_HOST', 'localhost')

@app.route('/api/python/hello')
def hello():
    return 'hello'

@app.route('/health')
def health():
    return jsonify({'status': 'UP'})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=port, debug=True)
