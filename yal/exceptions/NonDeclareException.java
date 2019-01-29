package Compilation.yal.exceptions;

public class NonDeclareException extends Exception {



    public NonDeclareException(){
        super("Variable non déclaré");
    }
}
