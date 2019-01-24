package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.IDF;
import yal.exceptions.NonDeclareException;

public class Affectation extends Instruction{

    private IDF idf;
    private Expression exp;

    public Affectation(int n,String nom) {
        super(n);
    }


    @Override
    public void verifier() throws NonDeclareException {


        idf.verifier();

        exp.verifier();


    }

    @Override
    public String toMIPS() {
        return null;
    }
}
