package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

public class Boucle extends Instruction{

    private Expression exp;
    private Instruction inst;

    public Boucle(int n, Expression exp, Instruction inst) {
        super(n);
        this.exp = exp;
        this.inst = inst;
    }

    @Override
    public void verifier() throws NonDeclareException {
        exp.verifier();
        inst.verifier();
    }

    @Override
    public String toMIPS() {

        String res = "" +
                "# Boucle\n" + "\n";

        return res;
    }
}
