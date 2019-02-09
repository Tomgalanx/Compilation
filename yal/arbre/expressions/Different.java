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

        res.append(gauche.toMIPS());

        res.append("move $sp,$v0 \n");

        res.append(droite.toMIPS());

        res.append("move $t8,$sp \n");

        res.append("sne $v0,$v0,$t8 \n");

        return res.toString();
    }
}
