import ast
import symtable
from typing import *

class Visitor(ast.NodeVisitor):
    def __init__(self, symbol_table) -> None:
        self.nodes = []
        self.locals = []
        self.globals = []
        self.symbol_table = symbol_table

    def visit_Module(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Interactive(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Expression(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_FunctionDef(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AsyncFunctionDef(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_ClassDef(self, node) -> Any:
        print(node.col_offset)
        self.generic_visit(node)

    def visit_Return(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Delete(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Assign(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AnnAssign(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_For(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AsyncFor(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_While(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_If(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_With(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AsyncWith(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Raise(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Try(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Assert(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Import(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_ImportFrom(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Global(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Nonlocal(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Expr(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Pass(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Break(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Continue(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_BoolOp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_NamedExpr(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_BinOp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_UnaryOp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Lambda(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_IfExp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Dict(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Set(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_ListComp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_DictComp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Await(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Yield(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_YieldFrom(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Compare(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Call(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_FormattedValue(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_JoinedStr(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Constant(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Attribute(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Subscript(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Starred(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Name(self, node) -> Any:
        print(node)
        self.locals.append(node.id)
        self.generic_visit(node)

    def visit_List(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Tuple(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Slice(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Load(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Store(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Del(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_And(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Or(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Add(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Sub(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Mult(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_MatMult(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Div(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Mod(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Pow(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_LShift(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_RShift(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_BitOr(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_BitXor(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_BitAnd(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_FloorDiv(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Invert(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Not(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_UAdd(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_USub(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Eq(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_NotEq(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Lt(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_LtE(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Gt(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_GtE(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Is(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_IsNot(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_In(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_NotIn(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_ExceptHandler(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_comprehension(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_arguments(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_arg(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_keyword(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_alias(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_withitem(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Num(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Str(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Bytes(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Index(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Param(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Suite(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_Ellipsis(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AugAssign(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AugLoad(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_AugStore(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_ExtSlice(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_GeneratorExp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_NameConstant(self, node) -> Any:
        print(node)
        self.generic_visit(node)

    def visit_SetComp(self, node) -> Any:
        print(node)
        self.generic_visit(node)

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
            for i in sym_data.get_children():
                deeper(i, now_node)


def main():
    filename = 'example.py'

    f = open(filename, "rt", encoding="UTF-8")
    node = ast.parse(f.read())
    symbol_table = symtable.symtable(f.read(), filename, "exec")


    visitor = Visitor(symbol_table)
    visitor.visit(node)
    # print(ast.dump(node, indent=True))
    print(ast.dump(node, indent=True, include_attributes=True, annotate_fields=True))
    print(symbol_table.get_children())


if __name__ == "__main__":
    main()