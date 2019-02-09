package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.expressions.Expression;
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
        inst.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder boucle = new StringBuilder(150);
        int hash = hashCode();

        boucle.append("# Boucle\n");

        boucle.append("tq_");
        boucle.append(hash);
        boucle.append(" :\n");
        boucle.append(exp.toMIPS());

        boucle.append("beqz $v0, fintq_");
        boucle.append(hash);
        boucle.append("\n");

        boucle.append("iter_");
        boucle.append(hash);
        boucle.append(" :\n");
        boucle.append(inst.codeMipsInstruction());
        boucle.append("j tq_");
        boucle.append(hash);
        boucle.append("\n");

        boucle.append("fintq_");
        boucle.append(hash);
        boucle.append(" :\n");

        return boucle.toString();
    }
}
