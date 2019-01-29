package Compilation.yal.exceptions;

public class DoubleDeclarationExcepion extends Exception {


    public DoubleDeclarationExcepion(){
        super("La variable a été déclaré plusieurs fois");
    }
}
