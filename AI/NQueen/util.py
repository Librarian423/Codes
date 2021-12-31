def BFS_Check(state, new_queen):
    for col, row in enumerate(state):
          if abs(row - new_queen) == abs(col - len(state)) or\
          new_queen in state:
              return False
    return True

def CSP_Check(array, row, col, num):
    for i in range(col):
        if array[row][i] == 1:
            return False
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if array[i][j] == 1:
            return False
    for i, j in zip(range(row, num, 1), range(col, -1, -1)):
        if array[i][j] == 1:
            return False

    return True

def HC_Check(array):
    heuristic = 0
    max_col = len(array)
    for i in range(max_col):
        count = 0
        for j in range(max_col - i - 1):
            count += 1
            if int(array[i]) == int(array[i + j + 1]):
                heuristic = heuristic + 1
            elif int(array[i + j + 1]) == int(array[i]) + count:
                heuristic = heuristic + 1
            elif int(array[i + j + 1]) == int(array[i]) - count:
                heuristic = heuristic + 1
            else:
                continue
    return heuristic

def make_file(num, string, array):
    file = str(num) + "_" + string + "_output.txt"
    f = open(file, 'w')
    for i in range(int(num)):
        f.write(str(array[i]) + " ")
    f.close()

def no_sol(num, string):
    file = str(num) + "_" + string + "_output.txt"
    f = open(file, 'w')
    f.write("no solution")
