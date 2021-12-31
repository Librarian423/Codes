import util

def csp(num):
    if num == '3' or num == '2':
        util.no_sol(num, "csp")
        print("no solution")
        return

    result = solve_Nqueen(int(num))
    util.make_file(num, "csp", result)
    print(result)
    return

def Nqueen(board, num):
    result = []
    for i in range(num):
        for j in range(num):
            if board[j][i] == 1:
                result.append(j + 1)
    return result

def solve(board, col, num):
    if col >= num:
        return True

    for i in range(num):
        if util.CSP_Check(board, i, col, num):
            board[i][col] = 1

            if solve(board, col + 1, num):
                return True

            board[i][col] = 0
    return False

def solve_Nqueen(num):
    board = [[0 for j in range(num)] for i in range(num)]

    solve(board, 0, num)

    result = Nqueen(board, num)
    return result