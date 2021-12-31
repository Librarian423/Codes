import util
import random
import copy

def hc(num):
    if num == '3' or num == '2':
        util.no_sol(num, "hc")
        print("no solution")

    while(True):
        result = solve(num)
        if util.HC_Check(result) == 0:
            break
    util.make_file(num, "hc", result)
    print(result)
    return

def solve(num):
    result = list(range(1, num + 1))
    random.shuffle(result)
    min = util.HC_Check(result)

    if min == 0:
        return result

    board = [[0 for i in range(num)] for j in range(num)]
    for i in range(num):
        cp_array1 = copy.deepcopy(result)
        for j in range(num):
            cp_array1[i] = j + 1
            board[i][j] = util.HC_Check(cp_array1)
            if board[i][j] < min:
                min = board[i][j]

    cp_array2 = copy.deepcopy(result)
    for i in range(num):
        temp = []
        for j in range(num):
            if (board[i][j]) == min:
                temp.append(j + 1)
        if len(temp) != 0:
            random.shuffle(temp)
            cp_array2[i] = temp.pop()
        for k in range(i):
            check = cp_array2[i] == cp_array2[k]
            if check:
                if len(temp) != 0:
                    cp_array2[i] = temp.pop()
                else:
                    cp_array2[i] = result[i]
    return cp_array2