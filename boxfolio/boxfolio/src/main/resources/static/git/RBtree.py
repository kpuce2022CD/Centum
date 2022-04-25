import sys
import node as n


class RedBlackTree:
    def __init__(self, data=None):
        sys.setrecursionlimit(10 ** 7)
        self.root = data
        self. insertedNode = None

    def insert(self, data):
        self.root = self._insert_node(self.root, data, None)
        self._balancing(self.inserted_node)
        return

    def _insert_node(self, cur, data, parent):
        if cur is None:
            cur = n.Node(data)
            cur.parent = parent
            self.inserted_node = cur
        else:
            if data < cur.key:
                cur.left = self._insert_node(cur.left, data, cur)
            elif data > cur.key:
                cur.right = self._insert_node(cur.right, data, cur)
        return cur

    def _balancing(self, node):
        P = node.parent
        if P is None:  # if node is root node
            node.color = "Black"
        else:  # if node isn't root node
            if P.color == "Red":  # if parent node is red
                G = P.parent  # must exist grandparent
                U = None
                if G.left == P:
                    U = G.right
                elif G.right == P:
                    U = G.left

                if U is not None and U.color == "Red":
                    # parent, uncle -> Black, grandparent -> Red
                    P.color, U.color = "Black", "Black"
                    G.color = "Red"
                    # rebalncing from grandparent
                    self._balancing(G)
                else:  # uncle is None or uncle.color is black
                    if P == G.left and P.left == node:  # LL Case
                        G.color, P.color = P.color, G.color
                        self._right_rotate(G)
                    elif P == G.left and P.right == node:  # LR Case
                        self._left_rotate(P)
                        G.color, node.color = node.color, G.color
                        self._right_rotate(G)
                    elif P == G.right and P.right == node:  # RR Case
                        G.color, P.color = P.color, G.color
                        self._left_rotate(G)
                    elif P == G.right and P.left == node:  # RL Case
                        self._right_rotate(P)
                        G.color, node.color = node.color, G.color
                        self._left_rotate(G)

    # 로테이션 함수 ( parent 사용 )
    def _left_rotate(self, node):
        c = node.right
        p = node.parent

        if c.left is not None:
            c.left.parent = node

        node.right = c.left
        node.parent = c
        c.left = node
        c.parent = p
        if p is None:
            self.root = c
        elif p is not None:
            if p.left == node:
                p.left = c
            elif p.right == node:
                p.right = c

    def _right_rotate(self, node):
        c = node.left
        p = node.parent

        if c.right is not None:
            c.right.parent = node

        node.left = c.right
        node.parent = c
        c.right = node
        c.parent = p
        if p is None:
            self.root = c
        elif p is not None:
            if p.left == node:
                p.left = c
            elif p.right == node:
                p.right = c