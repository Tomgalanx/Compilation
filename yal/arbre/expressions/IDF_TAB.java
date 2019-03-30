package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class IDF_TAB extends Expression {

    private String nom;
    private Expression indice;
    private int numeroBloc;

    public IDF_TAB(int n, String nom, Expression indice) {
        super(n);
        this.nom = nom;
        this.indice = indice;
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }

    @Override
    public void verifier() throws NonDeclareException {

        // Verifie l'indice
        indice.verifier();

        // Si l'indice n'est pas de type arithmetique
        if(!indice.getType().equals(Expression.ARITHMETIQUE)){
            throw new AnalyseSemantiqueException(getNoLigne()+1, "L'indice du tableau doit etre un entier");
        }

        // récupert le symbole de la TDS
        Symbole s = TDS.getInstance().identification(new EntreeVariable(nom,noLigne));

        // récupert le déplacement du tableau
        dep = -(s.deplacement());

        // récupert le numero de bloc
        numeroBloc = s.getNumBloc();

        // vérifie que la variable est de type tableau
        if(!s.getType().equals(Symbole.TABLEAU)){
            throw new AnalyseSemantiqueException(indice.getNoLigne(),"La variable doit etre un tableau");
        }

    }


    @Override
    public String toMIPS() {


        StringBuilder res = new StringBuilder();

        // Etiquette unique
        int etiquette = FabriqueEtiquette.getEtiquette();


        // Récupert la bonne base courrante du tableau, meme code que dans IDF
        res.append("# Récupère la base courante\n");
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

        // Une fois qu'on a la bonne base courrante, on charge l'adresse du tableau
        res.append("# Chargement de l'adresse du tableau dans $t8\n");
        res.append("lw $t8, ");
        res.append(dep);
        res.append("($t2)");
        res.append("\n");

        // On charge l'indice du tableau
        res.append(indice.toMIPS());

        // On vérifie si l'indice est correcte
        res.append("bltz $v0, TableauInvalide\n");

        // On charge la longueur
        res.append("lw $t2, 0($t8)\n");

        // On vérifie que l'indice est correcte
        res.append("sub $t2, $t2, $v0\n");

        // Si l'indice est plus grand que la borne, on affiche un message est on arrete l'application
        res.append("blez $t2, TableauInvalide\n");

        // Sinon on calcule l'adresse de la case
        res.append("li $t3, -4\n");
        res.append("mult $v0, $t3\n");
        res.append("mflo $t1\n");

        // On enleve la case de la longueur
        res.append("add $t1, $t1, -4\n");

        res.append("add $t8, $t8, $t1\n");

        res.append("lw $v0, 0($t8)\n");

        return res.toString();
    }
}
