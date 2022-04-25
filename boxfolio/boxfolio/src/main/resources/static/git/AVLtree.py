import sys


class AVL:
    def __init__(self, data=None):
        sys.setrecursionlimit(10 ** 7)
        self.root = data

    # 높이 함수
    def height(self, data):
        if data is None:
            return 0
        else:
            return data.height

    # 삽입

    def insert(self, data):
        self.root = self.insert_process(data, self.root)

    def insert_process(self, data, currentNode=None):

        if currentNode is None:
            data.height = 1
            return data

        if data.key <= currentNode.key:
            if currentNode.left:
                currentNode.left = self.insert_process(data, currentNode.left)
            else:
                currentNode.left = data

        elif data.key > currentNode.key:
            if currentNode.right:
                currentNode.right = self.insert_process(data, currentNode.right)
            else:
                currentNode.right = data

        currentNode.height = max(self.height(currentNode.left), self.height(currentNode.right)) + 1
        return self.balance(currentNode)

    # 양쪽 밸런스 수치 가져옴
    def BF(self, node):
        return self.height(node.left) - self.height(node.right)

    # 밸런스 맞추기
    def balance(self, node):
        if self.BF(node) > 1:
            if self.BF(node.left) < 0:
                node.left = self.R_L(node.left)
            node = self.R_R(node)

        elif self.BF(node) < -1:
            if self.BF(node.right) > 0:
                node.right = self.R_R(node.right)
            node = self.R_L(node)

        return node

    # 로테이션 함수 ( parent 사용 하지 않음 )
    def R_R(self, node):
        temp = node.left

        node.left = temp.right
        temp.right = node

        node.height = max(self.height(node.right), self.height(node.left)) + 1
        temp.height = max(self.height(temp.right), self.height(temp.left)) + 1

        return temp

    def R_L(self, node):
        temp = node.right

        node.right = temp.left
        temp.left = node

        node.height = max(self.height(node.right), self.height(node.left)) + 1
        temp.height = max(self.height(temp.right), self.height(temp.left)) + 1

        return temp
