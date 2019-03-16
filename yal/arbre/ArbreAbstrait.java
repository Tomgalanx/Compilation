package Compilation.yal.arbre;

import Compilation.yal.exceptions.NonDeclareException;

public abstract class ArbreAbstrait {

    // numéro de ligne du début de l'instructiont
    protected int noLigne ;

    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }

    public int getNoLigne() {
            return noLigne+1 ;
    }

    public abstract void verifier() throws NonDeclareException;
    public abstract String toMIPS();

}
