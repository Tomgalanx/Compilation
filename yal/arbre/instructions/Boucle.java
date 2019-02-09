package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.UUID;

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

        String hash = UUID.randomUUID().toString();

        String res = "" +
                "# Boucle\n" +
                "tantque" +
                hash +":\n" +
                exp.toMIPS() +
                "beqz $v0, fintantque"+
                hash + "\n" +
                "iteration" +
                hash +":\n" +
                inst.toMIPS() +
                "j tantque" +
                hash + "\n" +
                "fintantque" +
                hash + ":n";

        return res;
    }
}
