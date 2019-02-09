package Compilation.yal.arbre.expressions;

abstract public class ExpressionBooleenBinaire extends ExpressionBinaire {
    
    protected ExpressionBooleenBinaire(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
