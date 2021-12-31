from collections import deque
import util

def bfs(num):
    array = []
    if num == 3 or num == 2:
        util.no_sol(num, "bfs")
        print("no solution")
        return

    size = num
    queue = deque((queen_pos,) for queen_pos in range(size))

    while queue:
        board_state = queue.popleft()

        if len(board_state) == size:
            for i in range(num):
                array.append(int(board_state[i])+1)
            util.make_file(num, "bfs", array)
            print(array)
            break
        for queen_pos in range(size):
            if not util.BFS_Check(board_state, queen_pos):
                continue
            next_state = board_state + (queen_pos,)
            queue.append(next_state)