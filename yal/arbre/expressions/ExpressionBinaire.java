package Compilation.yal.arbre.expressions;

public abstract class ExpressionBinaire extends Expression {

    protected  Expression gauche;
    protected  Expression droite;

    protected ExpressionBinaire(int n, Expression gauche, Expression droite) {
        super(n);
        this.gauche = gauche;
        this.droite = droite;
    }
}
