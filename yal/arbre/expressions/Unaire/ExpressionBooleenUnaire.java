package Compilation.yal.arbre.expressions.Unaire;

import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

abstract public class ExpressionBooleenUnaire extends Expression {

    protected  Expression expression;

    protected ExpressionBooleenUnaire(int n, Expression expression) {
        super(n);
        this.expression = expression;
    }
}
