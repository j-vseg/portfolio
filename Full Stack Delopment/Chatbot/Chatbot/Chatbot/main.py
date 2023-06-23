from flask import Flask
from flask_restful import request, Api, reqparse
import bot_response
from flask_cors import CORS
import ast

app = Flask(__name__)
api = Api(app)
CORS(app)


@app.route('/bot', methods=['GET'])
def bot_request():
    args = request.args.get('question')

    response = bot_response.get_bot_response_from_csv(args)

    return response, 200


if __name__ == '__main__':
    app.run()
