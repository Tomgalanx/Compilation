package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeFonction;
import Compilation.yal.arbre.Variables.SymboleFonction;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.ArrayList;
import java.util.Collections;

public class AppelDeFonction extends Expression {


    // Nom de la fonction appelé
    private String nom;


    // Etiquette de la fonction
    private String etiquette;

    private int zoneVar;


    private ArrayList<Expression> parametres;

    public AppelDeFonction(int n, String nom, ArrayList<Expression> liste) {
        super(n);
        this.nom = nom;
        this.parametres=liste;
        zoneVar = TDS.getInstance().getZoneVariable();

    }

    public AppelDeFonction(int n, String nom) {
        super(n);
        this.nom = nom;
        parametres= new ArrayList<>();
    }

    // La fonction retourne que des entiers
    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }

    @Override
    public void verifier() throws NonDeclareException {

        EntreeFonction e = new EntreeFonction(nom, parametres.size(),noLigne);
        SymboleFonction s = (SymboleFonction) TDS.getInstance().identification(e);

        // Si la fonction n'existe pas, on envoie une erreur sémantique
        if (s == null) {
            throw new AnalyseSemantiqueException(getNoLigne()+1, "aucune déclaration de " + nom + "()");
        }

        // Sinon on récupert l'étquette MIPS de la fonction
        etiquette=s.getEtiquette();

        for(Expression exp : parametres){

            exp.verifier();

            if(exp.getType() != Expression.ARITHMETIQUE){
                throw new AnalyseSemantiqueException(getNoLigne()+1, "Les parametres de la fonction "+nom+" doivent etres de type entier");
            }
        }




    }

    @Override
    public String toMIPS() {


        /*
        StringBuilder res = new StringBuilder();

          int i =1;
        for(Expression expression : parametres){
            res.append(expression.toMIPS());
            res.append("sw $v0, ");
            res.append(i * 4);
            res.append("($sp)\n");
            i++;
        }

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

        res.append("# Dépiler les paramètres\n");
        res.append("add $sp, $sp, ");
        res.append(parametres.size() * 4);
        res.append("\n\n");


        return res.toString();

        */




        StringBuilder appel = new StringBuilder();


        appel.append("# Allocation de la place pour les paramètres\n");
        appel.append("add $sp, $sp, -");
        appel.append(parametres.size() * 4);
        appel.append("\n\n");

        Collections.reverse(parametres);
        int i =0;
        for(Expression expression : parametres){
            appel.append(expression.toMIPS());
            appel.append("sw $v0, ");
            appel.append(i * 4);
            appel.append("($sp)\n");
            i++;

        }

        appel.append("# Appel d'une fonction \n");
        appel.append("# Allocation de la place pour la valeur retour\n");
        appel.append("add $sp, $sp, -4\n");
        appel.append("\n");

        appel.append("# Saut vers l'étiquette de la fonction " + nom + "\n");
        appel.append("jal " + etiquette + "\n");
        appel.append("\n");

        appel.append("# Dépiler dans $v0\n");
        appel.append("add $sp, $sp, 4\n");
        appel.append("lw $v0, 0($sp)\n");
        appel.append("\n");

        appel.append("# Dépiler les paramètres\n");
        appel.append("add $sp, $sp, ");
        appel.append(parametres.size() * 4);
        appel.append("\n\n");

        return appel.toString();
    }
}
