package Compilation.yal.arbre.expressions.Binaire.Arithmetique;


import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.NonDeclareException;

public class Multiplication extends ExpressionBinaire {

    public Multiplication(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();
    }

    @Override
    public String toMIPS() {


        // Lo quotien
        // Hi reste

        StringBuilder res =new StringBuilder("# Multiplication \n");

        res.append(gauche.toMIPS());

        res.append("sw $v0,($sp) \n");

        res.append("add $sp,$sp,-4 \n");

        res.append(droite.toMIPS());

        res.append("add $sp,$sp,4 \n");

        res.append("lw $t8,($sp) \n");

        res.append("mult $t8,$v0 \n");

        res.append("mflo $v0 \n");

        return res.toString();
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }
}
