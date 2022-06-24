import symtable
import pprint


class node:
    def __init__(self, _type, lineno, name):
        self.children = []
        self.data = {
            'type': _type,
            'lineno': lineno,
            'name': name
        }

    def print_data(self):
        pprint.pprint(self.data)
        for c in self.children:
            c.print_data()


class module_node (node):
    def __init__(self, _type, lineno, name):
        super().__init__(_type, lineno, name)
        self.data['type'] = 'module'


class class_node (node):
    def __init__(self, _type, lineno, name, methods):
        super().__init__(_type, lineno, name)
        self.data['type'] = 'class'
        self.data['methods'] = methods


class function_node (node):
    def __init__(self, _type, lineno, name, parameters, _locals, _globals, nonlocals, frees):
        super().__init__(_type, lineno, name)
        self.data['type'] = 'function'
        self.data['parameters'] = parameters
        self.data['locals'] = _locals
        self.data['globals'] = _globals
        self.data['nonlocals'] = nonlocals
        self.data['frees'] = frees


def deeper(sym_data, up_node):

    now_node = None

    if sym_data.get_type() == 'class':

        if sym_data.get_methods() == ():
            met = None
        else:
            met = sym_data.get_methods()

        now_node = class_node(
            sym.get_type(),
            sym.get_lineno(),
            sym.get_name(),
            met
        )

    elif sym_data.get_type() == 'module':
        now_node = module_node(
            sym.get_type(),
            sym.get_lineno(),
            sym.get_name()
        )

    elif sym_data.get_type() == 'function':

        param = (None if sym_data.get_parameters() == () else sym_data.get_parameters())
        locals = (None if sym_data.get_locals() == () else sym_data.get_locals())
        globals = (None if sym_data.get_globals() == () else sym_data.get_globals())
        nonlocals = (None if sym_data.get_nonlocals() == () else sym_data.get_nonlocals())
        frees = (None if sym_data.get_frees() == () else sym_data.get_frees())

        now_node = function_node(
            sym.get_type(),
            sym.get_lineno(),
            sym.get_name(),
            param,
            locals,
            globals,
            nonlocals,
            frees,
        )
    up_node.children.append(now_node)
    if sym_data.has_children:
        print(sym_data.get_children())
        for i in sym_data.get_children():
            deeper(i, now_node)


f = open('test2.py', 'rt', encoding='UTF8')

sym = symtable.symtable(f.read(), 'test2.py', 'exec')

result = node('top', 0, 'top')
deeper(sym, result)

# result.print_data()
