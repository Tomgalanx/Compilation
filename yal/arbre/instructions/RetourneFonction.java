package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class RetourneFonction extends Instruction {

    // La valeur retourne par la fonction
    private  Expression retour;

    // Le numéro de bloc de la fonction
    private int numeroBloc;

    public RetourneFonction(int n, Expression retour) {
        super(n);
        this.retour = retour;
    }

    @Override
    public void verifier() throws NonDeclareException {

        retour.verifier();


        // On vérifie que la retour de la fonction est bien entiere
        if(retour.getType() != Expression.ARITHMETIQUE){
            throw new AnalyseSemantiqueException(getNoLigne()+1,"Le retour doit etre de type Arithmetiques");
        }

        // On récupert le numero de bloc de la fonction
        numeroBloc= TDS.getInstance().getNumeroBloc();
    }


    @Override
    public String toMIPS() {

        // On génére le code MIPS qui va nous faire revenir dans le programme principale
        StringBuilder res = new StringBuilder();

        res.append("# Retour vers la suite du main\n");
        res.append(retour.toMIPS());


        // On dépile tous dans l'ordre inverse

        // Dabord les variables
        res.append("#Dépiles les variables\n");
        res.append("move $sp, $s7\n");
        res.append("lw $s7, 8($sp)\n");

        // Ensuite le numero de bloc
        res.append("#Dépile le numero de bloc\n");
        res.append("add $sp, $sp, 4\n");

        // Ensuite pour le chainage dynamique
        res.append("#Dépile dynamique\n");
        res.append("add $sp, $sp, 4\n");

        // Ensuite l'adresse de retour
        res.append("# @retour\n");
        res.append("add $sp, $sp, 4\n");
        res.append("lw $ra, 0($sp)\n");

        // Et Enfin la valeur de retour
        res.append("# retourne exp\n");
        res.append("sw $v0, 4($sp)\n");
        res.append("\n");


        // Après tous ca on peut revenir au programme principale
        res.append("# Jump\n");
        res.append("jr $ra\n");
        res.append("\n");


        return res.toString();
    }
}
