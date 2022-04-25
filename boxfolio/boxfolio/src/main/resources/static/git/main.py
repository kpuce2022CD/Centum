import node
import random
import time
import binarySearchTree
import AVLtree
import RBtree
import treaps
import splay
import sys

sys.setrecursionlimit(10**7)
# 기본 설정값 ( 필요시 수정 가능 )
MAX_NUMBER = 100_000_000    # 최댓값
EPOCH = 100  # 반복 횟수
DATA_SIZE = [1_000, 5_000, 10_000, 50_000, 100_000]     # 데이터 크기
DATA = []   # 데이터가 들어갈 리스트
DATA_WORST = [list(range(0, i)) for i in DATA_SIZE]

# 각 트리들
BS = binarySearchTree.binarySearchTree()
AVL = AVLtree.AVL()
RB = RBtree.RedBlackTree()
T = treaps.treaps()
S = splay.SplayTree()

# 삽입에 소요된 시간
USED_TIME_INSERT = {
    "BS": [0, 0, 0, 0, 0],
    "AVL": [0, 0, 0, 0, 0],
    "RB": [0, 0, 0, 0, 0],
    "T": [0, 0, 0, 0, 0],
    "S": [0, 0, 0, 0, 0]
}

# 삽입에 소요된 시간 ( worst case )
USED_TIME_INSERT_WORST = {
    "BS": [0, 0, 0, 0, 0],
    "AVL": [0, 0, 0, 0, 0],
    "RB": [0, 0, 0, 0, 0],
    "T": [0, 0, 0, 0, 0],
    "S": [0, 0, 0, 0, 0]
}

# 탐색에 소요된 시간
USED_TIME_SEARCH = {
    "BS": 0,
    "AVL": 0,
    "RB": 0,
    "T": 0,
    "S": 0
}

# 탐색에 소요된 시간 ( worst case )
USED_TIME_SEARCH_WORST = {
    "BS": 0,
    "AVL": 0,
    "RB": 0,
    "T": 0,
    "S": 0
}

print("program start")

print("start inserting random data")

# 각 트리에 삽입 ( random case )
for e in range(EPOCH):
    DATA = [random.sample(range(0, MAX_NUMBER), i) for i in DATA_SIZE]

    # BS 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            BS.insert(temp)
        USED_TIME_INSERT["BS"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            BS.root = None

    # AVL 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            AVL.insert(temp)
        USED_TIME_INSERT["AVL"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            AVL.root = None
            
    # RB 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            RB.insert(data)
        USED_TIME_INSERT["RB"][index] += time.time() - start
        index += 1
        if i != DATA[-1]:
            RB.root = None

    # T 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            T.insert(temp)
        USED_TIME_INSERT["T"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            T.root = None

    # S 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            S.insert(temp)
        USED_TIME_INSERT["S"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            S.root = None

    print(f"====== INSERT RANDOM {e+1}% COMPLETE ======", flush=True)
print("start inserting random data done")
print("start searching random data")
# 각 트리에서 특정한 값 탐색 ( 마지막 데이터 리스트에서 한 값을 추출해서 탐색 RANDOM CASE)
for e in range(EPOCH * 10000):

    SEARCH = random.choice(DATA[-1])

    # BS 에서 탐색
    start = time.time()
    if not BS.root.search(SEARCH, BS.root):
        break
    USED_TIME_SEARCH["BS"] += time.time() - start

    # AVL 에서 탐색
    start = time.time()
    if not AVL.root.search(SEARCH, AVL.root):
        break
    USED_TIME_SEARCH["AVL"] += time.time() - start

    # RB 에서 탐색
    start = time.time()
    if not RB.root.search(SEARCH, RB.root):
        break
    USED_TIME_SEARCH["RB"] += time.time() - start

    # T 에서 탐색
    start = time.time()
    if not T.root.search(SEARCH, T.root):
        break
    USED_TIME_SEARCH["T"] += time.time() - start

    # S 에서 탐색
    start = time.time()
    if not S.searchSplay(SEARCH, S.root):
        print("S search error")
        break
    USED_TIME_SEARCH["S"] += time.time() - start
print("start searching random data done")
BS.root = None
AVL.root = None
RB.root = None
T.root = None
S.root = None
print("start inserting worst data")

DATA = [DATA_WORST[0]]
# 각 트리에 삽입 ( worst case )
for e in range(EPOCH):

    # BS 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            BS.insert(temp)
        USED_TIME_INSERT_WORST["BS"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            BS.root = None

    # AVL 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            AVL.insert(temp)
        USED_TIME_INSERT_WORST["AVL"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            AVL.root = None

    # RB 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            RB.insert(data)
        USED_TIME_INSERT_WORST["RB"][index] += time.time() - start
        index += 1
        if i != DATA[-1]:
            RB.root = None

    # T 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            T.insert(temp)
        USED_TIME_INSERT_WORST["T"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            T.root = None

    # S 에 삽입
    index = 0
    for i in DATA:
        start = time.time()
        for data in i:
            temp = node.Node(data)
            S.insert(temp)
        USED_TIME_INSERT_WORST["S"][index] += time.time() - start
        index += 1

        if i != DATA[-1]:
            S.root = None

    print(f"====== INSERT WORST {e + 1}% COMPLETE ======", flush=True)
print("start inserting worst data done")
print("start searching worst data")

# 각 트리에서 특정한 값 탐색 ( 마지막 데이터 리스트에서 한 값을 추출해서 탐색 WORST CASE)
for e in range(EPOCH * 10000):

    SEARCH = random.choice(DATA[-1])

    # BS 에서 탐색
    start = time.time()
    if not BS.root.search(SEARCH, BS.root):
        break
    USED_TIME_SEARCH_WORST["BS"] += time.time() - start

    # AVL 에서 탐색
    start = time.time()
    if not AVL.root.search(SEARCH, AVL.root):
        break
    USED_TIME_SEARCH_WORST["AVL"] += time.time() - start

    # RB 에서 탐색
    start = time.time()
    if not RB.root.search(SEARCH, RB.root):
        break
    USED_TIME_SEARCH_WORST["RB"] += time.time() - start

    # T 에서 탐색
    start = time.time()
    if not T.root.search(SEARCH, T.root):
        break
    USED_TIME_SEARCH_WORST["T"] += time.time() - start

    # S 에서 탐색
    start = time.time()
    if not S.searchSplay(SEARCH, S.root):
        print("S search error")
        break
    USED_TIME_SEARCH_WORST["S"] += time.time() - start
print("start searching worst data done")

print("삽입에 걸린 시간 ( RANDOM CASE )")
print()

index = 0
for key in USED_TIME_INSERT:
    print(key)
    for i in USED_TIME_INSERT[key]:
        print(f"{DATA_SIZE[index]}개 : {i} 초")
        index += 1
    index = 0
print()
print("탐색에 걸린 시간 ( RANDOM CASE )")
print()
for key in USED_TIME_SEARCH:
    print(f"{key} : {USED_TIME_SEARCH[key]}")
print("삽입에 걸린 시간 ( WORST CASE )")
index = 0
for key in USED_TIME_INSERT_WORST:
    print(key)
    for i in USED_TIME_INSERT_WORST[key]:
        print(f"{DATA_SIZE[index]}개 : {i} 초")
        index += 1
    index = 0
print()
print("탐색에 걸린 시간 ( WORST CASE )")
print()
for key in USED_TIME_SEARCH_WORST:
    print(f"{key} : {USED_TIME_SEARCH_WORST[key]}")
