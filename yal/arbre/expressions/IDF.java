package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.NonDeclareException;

public class IDF extends Expression{

    private String nom;
    private int dep;

    private int ligne;
    // Constructeur de l'identifiant :
    // n : Numro de ligne
    // nom : nom de l'identifiant
    public IDF(int n,String nom) {
        super(n);
        this.ligne = n;
        this.nom = nom;
    }

    @Override
    public void verifier() throws NonDeclareException {

        Symbole s = TDS.getInstance().identification(new EntreeVariable(nom,noLigne));

        dep = s.deplacement();


    }


    public int getDep() {
        return dep;
    }

    @Override
    public String toMIPS() {

        String res ="#Load word \n" +
                "lw $v0,"+dep+"($s7) \n";


        return res;
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }
}
