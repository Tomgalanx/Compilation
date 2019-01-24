package yal.arbre.expressions;

import yal.arbre.TDS;
import yal.arbre.Variables.EntreeVariable;
import yal.arbre.Variables.Symbole;
import yal.exceptions.NonDeclareException;

public class IDF extends Expression{

    private String nom;
    private int dep;

    // Constructeur de l'identifiant :
    // n : Numro de ligne
    // nom : nom de l'identifiant
    public IDF(int n,String nom) {
        super(n);

        this.nom = nom;
    }

    @Override
    public void verifier() throws NonDeclareException {

        Symbole s = TDS.getInstance().identification(new EntreeVariable(nom));

        dep = s.deplacement();

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
