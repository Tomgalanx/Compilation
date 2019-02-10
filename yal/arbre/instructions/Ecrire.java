package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() throws NonDeclareException {

        exp.verifier();
    }

    @Override
    public String toMIPS() {

        String res;

        int code = hashCode();

        if(exp.getType().equals(Expression.BOOLEEN)){

            res= "# affichage de l'expression\n" +
                    exp.toMIPS() +
                    "    beqz $v0, alors_" + code + "\n" +
                    "    la $a0, vrai\n" +
                    "    j end_" + code + "\n" +
                    "    alors_" + code + " :\n"+
                        "la $a0, faux \n"+
                    "    end_" + code + " :\n"+
                    "li $v0, 4\n"+
                    "syscall\n";
        }
        else

        res= "# affichage de l'expression\n" +
                exp.toMIPS() +
                "    move $a0, $v0\n" +
                "    li $v0, 1\n" +
                "    syscall\n" +
                "    li $v0, 4  \n " +
                "    la $a0, finLigne\n" +
                "    syscall\n" ;


        return res;
    }

}
