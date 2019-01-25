package Compilation.yal.arbre.expressions;

public abstract class ExpressionBinaire extends Expression {

    protected   String gauche;
    protected   String droite;

    protected ExpressionBinaire(int n, String gauche, String droite) {
        super(n);
        this.gauche = gauche;
        this.droite = droite;
    }
}
