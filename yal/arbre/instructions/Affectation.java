package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.expressions.IDF;
import Compilation.yal.exceptions.NonDeclareException;

public class Affectation extends Instruction{

    private IDF idf;
    private Expression exp;

    public Affectation(int n,String nom,Expression exp) {
        super(n);

        idf = new IDF(n,nom);
        this.exp = exp;
    }


    @Override
    public void verifier() throws NonDeclareException {


        idf.verifier();

        exp.verifier();


    }

    @Override
    public String toMIPS() {


        String res ="# Affectation \n"+
                idf.toMIPS() + exp.toMIPS()+"" +
                "sw $v0,"+idf.getDep()+"($s7)\n";


        return res;
    }
}
