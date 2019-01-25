package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.expressions.IDF;
import Compilation.yal.exceptions.NonDeclareException;

public class Lire extends Instruction {


    private final IDF idf;

    public Lire(int n, String idf) {
        super(n);
        this.idf = new IDF(n,idf);
    }

    @Override
    public void verifier() throws NonDeclareException {

        idf.verifier();

    }

    @Override
    public String toMIPS() {


        return "# Lire un entier\n" +
                "li $v0 , 5 \n" +
                "syscall \n " +
                "lw $v0,"+idf.getDep()+"($s7)\n";

    }
}
