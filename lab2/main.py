# -*- coding: windows-1251 -*-
import string
import math

f = open(r"res/1.txt", "r", encoding="windows-1251")
text = f.read()
text = text.lower()


def remove_chars_from_text(txt, chars):
    return ''.join([ch for ch in txt if ch not in chars])


spec_chars = string.punctuation + '«»–…1234567890—qwertyuiopasdfghjklzxcvbnm\n\t\xa0©'
text = remove_chars_from_text(text, spec_chars)
text = text.replace('ё', 'е')
text = text.replace('ъ', 'ь')

text_tokens = dict()


def shannon(txt):
    for d in range(1, 10):
        summa = 0
        for i in range(len(txt) - d + 1):
            if text_tokens.get(txt[i:i + d]) is None:
                text_tokens[txt[i:i + d]] = 1
                summa += 1
            else:
                text_tokens[txt[i:i + d]] = text_tokens.get(txt[i:i + d]) + 1
                summa += 1
        if d == 1:
            max_h = math.log(len(text_tokens), 2)
            print("max_h = ", max_h)
        h = 0
        for value in text_tokens.values():
            h += (-1) * (value / summa) * math.log(value / summa, 2)
        h /= d
        print(f"h{d} = {h}")
        text_tokens.clear()


shannon(text)
