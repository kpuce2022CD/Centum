from flask import Flask, request, jsonify, url_for, render_template
from werkzeug.utils import redirect
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app, resources={r'*': {'origins': 'http://localhost:3000'}})


@app.route('/api')
def api():  # put application's code here
    return "success"

@app.route('/api')
def receive_api():
    data = request.get_json()
    print(data)
    return jsonify({'data': 'success'})

if __name__ == '__main__':
    app.run()
