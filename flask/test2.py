import pprint


class Country:
    pass


class Korea(Country):
    ww = 0
    def dump(self):
        a = 0
    def temp(self):
        b = 0
        ww = 1
        self.dump()


class china(Country):
    pass


class Japan(Country):
    pass


class Korean(Korea):
    pass


class Person:
    pass


class KoreanMan(Korean, Person):
    pass


class KoreanGirl(Korean, Person):
    pass
