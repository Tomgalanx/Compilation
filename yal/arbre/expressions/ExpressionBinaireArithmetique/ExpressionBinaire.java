package Compilation.yal.arbre.expressions.ExpressionBinaire.Arithmetique;

import Compilation.yal.arbre.expressions.Expression;

public abstract class ExpressionBinaire extends Expression {

    protected  Expression gauche;
    protected  Expression droite;

    protected ExpressionBinaire(int n, Expression gauche, Expression droite) {
        super(n);
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder();

        res.append(gauche.toMIPS());

        res.append("sw $v0,($sp) \n");

        res.append("add $sp,$sp,-4 \n");

        res.append(droite.toMIPS());

        res.append("add $sp,$sp,4 \n");

        res.append("lw $t8,($sp) \n");

        return res.toString();
    }
}
