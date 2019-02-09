package Compilation.yal.arbre.expressions.Unaire;

import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.expressions.Unaire.ExpressionBooleenUnaire;
import Compilation.yal.exceptions.NonDeclareException;

public class NotLogique extends ExpressionBooleenUnaire {

    public NotLogique(int n, Expression expression) {
        super(n, expression);
    }

    @Override
    public void verifier() throws NonDeclareException {
        expression.verifier();
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Non logique \n");

        res.append(expression.toMIPS());

        res.append("move $sp,$v0 \n");

        res.append("not $v0,$v0 \n");

        return res.toString();
    }
}
