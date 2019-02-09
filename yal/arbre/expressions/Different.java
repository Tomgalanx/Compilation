package Compilation.yal.arbre.expressions;

import Compilation.yal.exceptions.NonDeclareException;

public class Different extends ExpressionBooleenBinaire{

    public Different(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Different \n");

        res.append(super.toMIPS());

        res.append("sne $v0,$v0,$t8 \n");

        return res.toString();
    }
}
