package Compilation.yal.arbre.expressions;


import Compilation.yal.exceptions.NonDeclareException;

public class Division extends ExpressionArithmetique {

    public Division(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {


        // Lo quotien
        // Hi reste

        StringBuilder res =new StringBuilder("# Division \n");

        res.append(gauche.toMIPS());

        res.append("move $sp,$v0 \n");

        res.append(droite.toMIPS());

        res.append("move $t8,$sp \n");

        res.append("div $t8,$v0 \n");

        res.append("mflo $v0 \n");

        return res.toString();
    }
}