package Compilation.yal.arbre.expressions.Binaire.Booleen;

import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

public class OuLogique extends ExpressionBinaire {

    public OuLogique(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Ou logique \n");

        res.append(super.toMIPS());

        res.append("or $v0,$t8,$v0 \n");

        return res.toString();
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
