package Compilation.yal.exceptions;

public class DoubleDeclarationExcepion extends AnalyseSemantiqueException {


    public DoubleDeclarationExcepion(int n){
        super(n,"La variable à déja été déclaré");
    }
}
