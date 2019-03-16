package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.NonDeclareException;

public class IDF extends Expression{

    private String nom;
    private int dep;

    private int ligne;
    private int numeroBloc;

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

        numeroBloc = s.getNumBloc();


    }


    public int getDep() {
        return dep;
    }

    @Override
    public String toMIPS() {

        StringBuilder res = new StringBuilder();

        int etiquette = FabriqueEtiquette.getEtiquette();

        System.out.println("idf toMips "+nom+" "+numeroBloc);


        res.append("# Récupère la base courante\n");
        res.append("move $t2, $s7\n");

        res.append("# Récupère le numéro de région où est déclarée la variable\n");
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

        res.append("# Chargement dans $v0\n");
        res.append("lw $v0, ");
        res.append(dep);
        res.append("($t2)");
        res.append("\n");

        return res.toString();
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }


    public String getNom() {
        return nom;
    }
}
