package Compilation.yal.arbre.expressions.Binaire.Arithmetique;


import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

public class Addition extends ExpressionBinaire {

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

        res.append(super.toMIPS());

        res.append("add $v0,$v0,$t8 \n");

        return res.toString();
    }

    @Override
    public String getType() {
        return "arithmetique";
    }
}
