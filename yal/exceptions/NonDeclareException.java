package Compilation.yal.exceptions;

public class NonDeclareException extends AnalyseSemantiqueException {



    public NonDeclareException(int ligne) {
        super(ligne,"Variable non déclaré");
    }
}
