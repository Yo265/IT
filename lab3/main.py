import math

f = open(r"main.py", "r")
text = ''

while True:
    line = f.readline()
    if not line:
        break
    if "#" in line or "print" in line is True:
        continue
    text += line

f.close()


def remove_chars_from_text(txt, chars):
    return ''.join([ch for ch in txt if ch not in chars])


text = text.lower()
text = remove_chars_from_text(text, "\n\t\xa0\x0c")

text_tokens = dict()


def shannon(txt):
    for d in range(1, 4):
        for i in range(len(txt) - d + 1):
            if text_tokens.get(txt[i:i + d]) is None:
                text_tokens[txt[i:i + d]] = 1
            else:
                text_tokens[txt[i:i + d]] = text_tokens.get(txt[i:i + d]) + 1
        if d == 1:
            max_h = math.log(len(text_tokens), 2)
            print("max_h = ", max_h)
        h = 0
        summa = sum(text_tokens.values())
        for value in text_tokens.values():
            h += (-1) * (value / summa) * math.log(value / summa, 2)
        h /= d
        print(f"h{d} = {h}")
        text_tokens.clear()


shannon(text)
