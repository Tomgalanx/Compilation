package Compilation.yal.arbre.expressions;

import Compilation.yal.exceptions.NonDeclareException;

public class Inferieur extends ExpressionBooleenBinaire {

    public Inferieur(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Inferieur \n");

        res.append(super.toMIPS());

        res.append("slt $v0,$v0,$t8 \n");

        return res.toString();
    }
}
