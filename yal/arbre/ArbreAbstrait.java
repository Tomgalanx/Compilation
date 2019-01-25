package Compilation.yal.arbre;

import Compilation.yal.exceptions.NonDeclareException;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;
    
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }
    
    public int getNoLigne() {
            return noLigne ;
    }

    public abstract void verifier() throws NonDeclareException, NonDeclareException;
    public abstract String toMIPS();

}