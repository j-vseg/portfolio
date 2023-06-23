import nltk


def filter_verbs_from_string(sentence):
    tokens = nltk.word_tokenize(sentence)
    pos_tagged = nltk.pos_tag(tokens)

    verbs = filter(lambda x: x[1] == 'VB', pos_tagged)
    return verbs


def filter_nouns_from_string(sentence):
    tokens = nltk.word_tokenize(sentence)
    pos_tagged = nltk.pos_tag(tokens)

    nouns = filter(lambda x: x[1] == 'NN', pos_tagged)
    return nouns
