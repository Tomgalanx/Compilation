package Compilation.yal.arbre.expressions;


import Compilation.yal.exceptions.NonDeclareException;

public class Addition extends ExpressionBinaire {


    protected Addition(int n, String gauche, String droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
