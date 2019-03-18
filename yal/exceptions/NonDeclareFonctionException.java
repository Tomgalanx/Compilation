package Compilation.yal.exceptions;

public class NonDeclareFonctionException extends AnalyseSemantiqueException {



    public NonDeclareFonctionException(int ligne) {
        super(ligne,"Appel d'une fonction non déclarée");
    }
}
