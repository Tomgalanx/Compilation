package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.BlocDInstructions;
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

        StringBuilder res = new StringBuilder(150);
        int hash = hashCode();

        res.append("#SI\n");

        res.append("if_");
        res.append(hash);
        res.append(" :\n");
        res.append(exp.toMIPS());

        res.append("beqz $v0, sinon_");
        res.append(hash);
        res.append("\n");

        res.append("then");
        res.append(hash);
        res.append(" :\n");
        if(listeSi != null)
            res.append(listeSi.codeMipsInstruction());
        res.append("j fin_");
        res.append(hash);
        res.append("\n");

        res.append("else");
        res.append(hash);
        res.append(" :\n");
        if(listeSinon != null)
            res.append(listeSinon.codeMipsInstruction());

        res.append("end");
        res.append(hash);
        res.append(" :\n");

        return res.toString();
    }
}
