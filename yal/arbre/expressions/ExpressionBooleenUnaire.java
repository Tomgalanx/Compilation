package Compilation.yal.arbre.expressions;

import Compilation.yal.exceptions.NonDeclareException;

abstract public class ExpressionBooleenUnaire extends Expression{

    protected  Expression expression;

    protected ExpressionBooleenUnaire(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }
}
