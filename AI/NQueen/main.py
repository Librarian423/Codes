import BFS
import CSP
import HC
import re

f = open("input.txt",'r')
line = f.read()
f.close()

num = re.findall(r'\d+',line)
array = line.split()


def main():
    number = 0

    for i in num:
        if 'bfs' in array[2*number+1]:
            BFS.bfs(int(num[number]))
            number = number + 1

        elif 'hc' in array[2 * number + 1]:
            HC.hc(int(num[number]))
            number = number + 1

        elif 'csp' in array[2*number+1]:
            CSP.csp(num[number])
            number = number + 1

        else:
            print('해당사항 없음\n')

if __name__ == "__main__":
    main()