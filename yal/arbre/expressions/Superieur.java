package Compilation.yal.arbre.expressions;

import Compilation.yal.exceptions.NonDeclareException;

public class Superieur extends ExpressionBooleenBinaire {

    public Superieur(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Superieur \n");

        res.append(droite.toMIPS());

        res.append("move $sp,$v0 \n");

        res.append(gauche.toMIPS());

        res.append("move $t8,$sp \n");

        res.append("slt $v0,$t8,$v0 \n");

        return res.toString();
    }
}
