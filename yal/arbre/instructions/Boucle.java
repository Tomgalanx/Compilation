package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.UUID;

public class Boucle extends Instruction{

    private Expression exp;
    private BlocDInstructions inst;

    public Boucle(int n, Expression exp, BlocDInstructions inst) {
        super(n);
        this.exp = exp;
        this.inst = inst;
    }

    @Override
    public void verifier() throws NonDeclareException {

        exp.verifier();

        // On vérifie que l'expression est un booleen
        if(!exp.getType().equals(Expression.BOOLEEN)){
            throw new AnalyseSemantiqueException(exp.getNoLigne(),"Dans une boucle, l'expression doit être un booleen");
        }

        inst.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder boucle = new StringBuilder(150);

        // Get l'etiquette pour avoir un si unique
        int hash = FabriqueEtiquette.getEtiquette();

        boucle.append("# Boucle\n");

        // Préparation de l'étiquette
        boucle.append("tq_");
        boucle.append(hash);
        boucle.append(" :\n");
        boucle.append(exp.toMIPS());

        // Branchement de la boucle avec l'etiquette de fin
        boucle.append("beqz $v0, fintq_");
        boucle.append(hash);
        boucle.append("\n");

        // Instruction dans la boucle
        boucle.append(inst.codeMipsInstruction());

        // Retour a la condition de la boucle
        boucle.append("j tq_");
        boucle.append(hash);
        boucle.append("\n");

        // Etiquette de fin
        boucle.append("fintq_");
        boucle.append(hash);
        boucle.append(" :\n");

        return boucle.toString();
    }
}
