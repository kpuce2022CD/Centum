import module

class Sample(object):
    def __init__(self):
        self.name = module().run()

    def getName(self):
        print("example")
        return self.name