package Compilation.yal.arbre.expressions;


import Compilation.yal.exceptions.NonDeclareException;

public class Addition extends ExpressionArithmetique {

    public Addition(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();

    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Addition \n");

        res.append(gauche.toMIPS());

        res.append("sw $v0,($sp) \n");

        res.append("add $sp,$sp,-4 \n");

        res.append(droite.toMIPS());

        res.append("add $sp,$sp,4 \n");

        res.append("lw $t8,($sp) \n");

        res.append("add $v0,$v0,$t8 \n");

        return res.toString();
    }
}
