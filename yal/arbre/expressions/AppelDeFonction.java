package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeFonction;
import Compilation.yal.arbre.Variables.SymboleFonction;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class AppelDeFonction extends Expression {


    // Nom de la fonction appelé
    private String nom;


    // Etiquette de la fonction
    private String etiquette;

    public AppelDeFonction(int n,String nom) {
        super(n);
        this.nom = nom;
    }

    // La fonction retourne que des entiers
    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }

    @Override
    public void verifier() throws NonDeclareException {

        EntreeFonction e = new EntreeFonction(nom, noLigne);
        SymboleFonction s = (SymboleFonction) TDS.getInstance().identification(e);

        // Si la fonction n'existe pas, on envoie une erreur sémantique
        if (s == null) {
            throw new AnalyseSemantiqueException(getNoLigne()+1, "aucune déclaration de `" + nom + "()`");
        }

        // Sinon on récupert l'étquette MIPS de la fonction
        etiquette=s.getEtiquette();




    }

    @Override
    public String toMIPS() {

        StringBuilder res = new StringBuilder();

        res.append("# Saut vers la fonction\n");
        res.append("# On prépare la valeur de retour\n");
        res.append("add $sp, $sp, -4\n");
        res.append("\n");

        res.append("# On fait un jump vers la fonction avec l'étiquette \n");
        res.append("jal " + etiquette + "\n");
        res.append("\n");

        res.append("# Et on dépile dans v0\n");
        res.append("add $sp, $sp, 4\n");
        res.append("lw $v0, 0($sp)\n");
        res.append("\n");


        return res.toString();
    }
}
