package Compilation.yal.arbre.expressions.Binaire.Booleen;

import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class Different extends ExpressionBinaire {

    public Different(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();


        if(!gauche.getType().equals(droite.getType())){
            throw new AnalyseSemantiqueException(getNoLigne(),"Les opérandes doivent etre de type Booleen");
        }
    }

    @Override
    public String toMIPS() {

        StringBuilder res =new StringBuilder("# Different \n");

        res.append(super.toMIPS());

        res.append("sne $v0,$t8,$v0 \n");

        return res.toString();
    }

    @Override
    public String getType() {
        return Expression.BOOLEEN;
    }
}
