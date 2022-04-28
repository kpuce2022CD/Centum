from flask import Flask, request, jsonify, url_for, render_template
from werkzeug.utils import redirect

app = Flask(__name__)


@app.route('/api')
def api():  # put application's code here
    return redirect("http://localhost:8080/")

@app.route('/api', methods=['POST'])
def receive_api():
    data = request.get_json()
    print(data)
    # return jsonify({'data': 'success'})
    return data

if __name__ == '__main__':
    app.run()
