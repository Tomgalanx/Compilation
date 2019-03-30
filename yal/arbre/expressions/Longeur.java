package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.arbre.Variables.SymboleTableau;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class Longeur extends Expression {

    private String nom;
    private int dep;
    private int numeroBloc;

    public Longeur(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }

    @Override
    public void verifier() throws NonDeclareException {

        // On vérifie que la variable existe
        EntreeVariable e = new EntreeVariable(nom,noLigne);
        Symbole s = TDS.getInstance().identification(e);

        // Si c'est un tableau alors on ne fait rien
        if(s instanceof SymboleTableau){

        }
        // Sinon on retourne une AnalyseSemantiqueException
        else{
            throw new AnalyseSemantiqueException(getNoLigne(), "Pour faire longeur sur un identifiant, l'identifiant doit etre un tableau");
        }

        // On récupert le deplacement
        dep= -(s.deplacement());

        // Le numéro de région
        numeroBloc=s.getNumBloc();

    }

    @Override
    public String toMIPS() {


        StringBuilder res = new StringBuilder();

        // Etiquette unique
        int etiquette = FabriqueEtiquette.getEtiquette();


        // Meme code que dans IDF
        res.append("# Base courrante\n");
        res.append("move $t2, $s7\n");

        res.append("#Numero de bloc\n");
        res.append("li $v1, ");
        res.append(numeroBloc);
        res.append("\n");

        res.append("tq_");
        res.append(etiquette);
        res.append(" :\n");

        res.append("lw $v0, 4($t2) \n");
        res.append("sub $v0, $v0, $v1\n");

        res.append("beqz $v0, fintq_");
        res.append(etiquette);
        res.append("\n");

        res.append("lw $t2, 8($t2) \n");
        res.append("j tq_");
        res.append(etiquette);
        res.append("\n");


        res.append("fintq_");
        res.append(etiquette);
        res.append(" :\n");

        // on récupert l'adresse du tableau
        res.append("# @TAb\n");
        res.append("lw $t4, ");
        res.append(dep);
        res.append("($t2)");
        res.append("\n");


        // On récupert la longueur du tableaus
        res.append("#Longueur tab\n");
        res.append("lw $v0, 0($t4)\n");

        return res.toString();
    }
}
