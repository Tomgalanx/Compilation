package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.Entree;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.expressions.IDF;
import Compilation.yal.arbre.expressions.constante.Constante;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class AffectationDeTableau extends Instruction{

    private Expression indice;
    private Expression value;
    private String nom;
    private Entree entree;
    private Symbole symbole;
    private int numbloc;
    private int dep;

    public AffectationDeTableau(int n,String nom,Expression indice,Expression value) {
        super(n);
        this.value = value;
        this.indice = indice;
        this.value=value;
        this.nom = nom;
    }


    @Override
    public void verifier() throws NonDeclareException {


        // On vérifie les parametres
        indice.verifier();

        value.verifier();


        // Si l'indice n'est pas de type arithmetique
        if(!indice.getType().equals(Expression.ARITHMETIQUE)){
            throw new AnalyseSemantiqueException(indice.getNoLigne(),"L'indice de l'affectation du tableau doit etre arithmetique");
        }

        // Si la valeur de l'affectation n'est pas de type arithmetique
        if(!value.getType().equals(Expression.ARITHMETIQUE)){
            throw new AnalyseSemantiqueException(indice.getNoLigne(),"La valeur d'un tableau doit etre de type entier");
        }

        // Si l'indice est une constante
        if(indice instanceof Constante){

            // Si cette constante est inferieur a 0
            if(Integer.parseInt(indice.toString()) < 0){
                throw new AnalyseSemantiqueException(indice.getNoLigne(),"L'indice du tableau ne peut pas etre negative");

            }
        }


        // On vérifie que le tableau existe dans la TDS
        entree=new EntreeVariable(nom,noLigne);
        symbole = TDS.getInstance().identification(entree);

        // Si la variable n'est pas un tableau
        if(!symbole.getType().equals(Symbole.TABLEAU)){
            throw new AnalyseSemantiqueException(indice.getNoLigne(),"La variable doit etre un tableau");
        }

        // On récupert le numero de bloc
        numbloc = symbole.getNumBloc();

        // Et le deplacement du tableau
        dep = -(symbole.deplacement());


    }

    @Override
    public String toMIPS() {


        StringBuilder res = new StringBuilder();

        // Etiquette unique
        int etiquette = FabriqueEtiquette.getEtiquette();


        // On charge la value de l'affectation
        res.append("# Valeur de l'affectation\n");
        res.append(value.toMIPS());

        res.append("# Empile value\n");
        res.append("sw $v0, 0($sp)\n");
        res.append("add $sp, $sp, -4\n");


        // Meme code que dans IDF
        res.append("# Base courrante\n");
        res.append("move $t2, $s7\n");

        res.append("# On récupert le numéro de bloc\n");
        res.append("li $v1, ");
        res.append(numbloc);
        res.append("\n");

        res.append("tq_");
        res.append(etiquette);
        res.append(" :\n");

        res.append("# On stock le numéro de bloc\n");
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


        // On récupert l'adresse du tableau
        res.append("# @Tab\n");
        res.append("lw $t8, ");
        res.append(dep);
        res.append("($t2)");
        res.append("\n");

        // On charge l'indice du tableau
        res.append(indice.toMIPS());

        // On vérifie l'indice du tableau
        res.append("bltz $v0, TableauInvalide\n");

        // On récupert la longueur
        res.append("lw $t2, 0($t8)\n");

        // On compare la longueur a l'indice
        res.append("sub $t2, $t2, $v0\n");

        // Si l'indice n'est pas correcte, on ecrit un message et on arrete l'application
        res.append("blez $t2, TableauInvalide\n");

        // Sinon on calcule l'adresse de la case
        res.append("li $t3, -4\n");
        res.append("mult $v0, $t3\n");
        res.append("mflo $t1\n");

        // On enleve la case de la longueur
        res.append("add $t1, $t1, -4");

        // On charge value
        res.append("add $sp, $sp, 4\n");
        res.append("lw $v0, 0($sp)\n");

        res.append("add $t8, $t8, $t1\n");

        // Et enfin on charge value dans la case
        res.append("sw $v0, 0($t8)\n");

        return res.toString();
    }


}
