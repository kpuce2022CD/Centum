import tokenize

def main():
    filename = 'example.py'

    token_list = []
    with open(filename, "rb") as f:
        tokens = tokenize.tokenize(f.readline)
        for token in tokens:
            token_list.append([tokenize.tok_name[token.exact_type], token.string])
        print(token_list)


if __name__ == "__main__":
    main()