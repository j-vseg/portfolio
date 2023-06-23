import nltk
import pandas as pd
import logging


def get_bot_response_from_csv(sentence):
    try:
        nouns = filter_token_from_string(sentence, "NN")
        verbs = filter_token_from_string(sentence, "VB")
    except Exception as ex:
        print(ex.message)
        return "something went wrong"

    nouns = [i[0] for i in nouns]
    verbs = [i[0] for i in verbs]

    data = pd.read_csv('.\\resources\\responses.csv')
    logging.warning(data)
    data = pd.DataFrame(columns=['Noun', 'Verb', "Responses"], data=data)


    data = data.loc[data['Noun'].isin(nouns)]
    data = data.loc[data['Verb'].isin(verbs)]

    logging.warning(data)

    return list(data['Responses'])


def filter_token_from_string(sentence, token):
    tokens = nltk.word_tokenize(sentence)
    pos_tagged = nltk.pos_tag(tokens)

    word = filter(lambda x: x[1] == token, pos_tagged)
    return list(word)
