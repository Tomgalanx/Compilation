package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class SI extends Instruction{


    private Expression exp;
    private BlocDInstructions listeSi;
    private BlocDInstructions listeSinon;



    // Constructeur Condition avec un si et sinon
    public SI(Expression e, BlocDInstructions si, BlocDInstructions sinon){
        super(e.getNoLigne());


        this.listeSi = si;
        this.listeSinon=sinon;

        this.exp=e;


    }

    @Override
    public void verifier() throws NonDeclareException {

        exp.verifier();


        // On vérifie que l'expression est un booleen
        if(!exp.getType().equals(Expression.BOOLEEN)){
            throw new AnalyseSemantiqueException(exp.getNoLigne(),"Dans une condition, l'expression doit être un booleen");
        }

        if(listeSinon != null)
            listeSinon.verifier();

        if(listeSi != null)
            listeSi.verifier();

    }

    @Override
    public String toMIPS() {

        StringBuilder res = new StringBuilder();

        // Get l'etiquette pour avoir un si unique
        int hash = FabriqueEtiquette.getEtiquette();

        res.append("#SI\n");

        // Préparation de l'étiquette
        res.append("if_");
        res.append(hash);
        res.append(" :\n");
        res.append(exp.toMIPS());

        // Branchement et le else avec la bonne etiquette
        res.append("beqz $v0, else");
        res.append(hash);
        res.append("\n");

        // Alors et l'étiquette
        res.append("then");
        res.append(hash);
        res.append(" :\n");

        // Si il n'y a pas de bloc si alors on ne met pas d'instruction
        if(listeSi != null)
            res.append(listeSi.codeMipsInstruction());

        res.append("j end");
        res.append(hash);
        res.append("\n");

        res.append("else");
        res.append(hash);
        res.append(" :\n");

        // Si il n'y a pas de sinon alors on ne met pas d'instruction
        if(listeSinon != null)
            res.append(listeSinon.codeMipsInstruction());


        // Etiquette de fin pour le si
        res.append("end");
        res.append(hash);
        res.append(" :\n");

        return res.toString();
    }
}
