package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeFonction;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.SymboleFonction;
import Compilation.yal.arbre.Variables.SymboleParametre;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;

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

        EntreeFonction e = new EntreeFonction(nom, parametres.size());
        SymboleFonction s = (SymboleFonction) TDS.getInstance().identification(e);

        // Si la fonction n'existe pas, on envoie une erreur sémantique
        if (s == null) {
            throw new AnalyseSemantiqueException(getNoLigne()+1, "aucune déclaration de `" + nom + "()`");
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

        */

        StringBuilder appel = new StringBuilder(50);

        appel.append("# Allocation de la place pour les paramètres\n");
        appel.append("add $sp, $sp, -");
        appel.append(parametres.size() * 4);
        appel.append("\n\n");

        for (int i = 1; i <= parametres.size(); i ++) {
            Expression parEff = parametres.get(i - 1);

            appel.append(parEff.toMIPS());
            appel.append("sw $v0, ");
            appel.append(i * 4);
            appel.append("($sp)\n");
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
