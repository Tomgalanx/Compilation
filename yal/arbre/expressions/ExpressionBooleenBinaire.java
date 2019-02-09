package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.expressions.ExpressionBinaireArithmetique.ExpressionBinaire;

abstract public class ExpressionBooleenBinaire extends ExpressionBinaire {
    
    protected ExpressionBooleenBinaire(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
