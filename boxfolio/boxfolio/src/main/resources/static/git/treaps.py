import sys


class treaps:
    def __init__(self, data=None):
        sys.setrecursionlimit(10 ** 7)
        self.root = data

        # 삽입

    def insert(self, data):
        self.root = self.insert_process(data, self.root)

    def insert_process(self, data, currentNode=None):

        if currentNode is None:
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

        return self.balance(currentNode)

    def balance(self, node):
        if node.left:
            if node.property < node.left.property:
                node = self.R_R(node)

        if node.right:
            if node.property < node.right.property:
                node = self.R_L(node)

        else:
            return node

        return node

    def R_R(self, node):
        temp = node.left

        node.left = temp.right
        temp.right = node

        return temp

    def R_L(self, node):
        temp = node.right

        node.right = temp.left
        temp.left = node

        return temp
