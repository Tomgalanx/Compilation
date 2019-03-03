package Compilation.yal.arbre.expressions.Binaire.Arithmetique;


import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class Division extends ExpressionBinaire {

    public Division(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();

        if(!gauche.getType().equals(Expression.ARITHMETIQUE) || !droite.getType().equals(Expression.ARITHMETIQUE)){
            throw new AnalyseSemantiqueException(getNoLigne(),"Les opérandes doivent etre de type Arithmetiques");
        }
    }

    @Override
    public String toMIPS() {


        // Lo quotien
        // Hi reste

        StringBuilder res =new StringBuilder("# Division \n");

        res.append(super.toMIPS());

        res.append("div $t8,$v0 \n");

        res.append("mflo $v0 \n");

        return res.toString();
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }
}
