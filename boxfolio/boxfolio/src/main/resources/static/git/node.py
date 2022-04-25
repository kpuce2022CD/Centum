import sys
import random



class Node:
    def __init__(self, data=None):
        sys.setrecursionlimit(10 ** 7)
        # 모든 트리에 사용
        self.key = data
        self.right = None
        self.left = None

        # AVL 트리에 사용
        self.height = 0

        # RB 트리에 사용
        self.parent = None
        self.color = "Red"

        # T 에 사용
        self.property = random.randint(0, 100_000_000)

    def search(self, val, root):

        if root is None:
            return False

        while root.key != val:
            if root.key < val:
                if root.right is None:
                    return False
                root = root.right
            elif root.key > val:
                if root.left is None:
                    return False
                root = root.left
        return True


if __name__ == "__main__":
    print("this is node class\n")
