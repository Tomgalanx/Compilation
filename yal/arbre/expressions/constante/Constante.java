package Compilation.yal.arbre.expressions.constante;

import Compilation.yal.arbre.expressions.Expression;

public abstract class Constante extends Expression {

    protected String cste ;

    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toString() {
        return cste ;
    }

}
