package Compilation.yal.arbre.expressions;

public abstract class ExpressionArithmetique extends ExpressionBinaire {

    protected ExpressionArithmetique(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }
}
