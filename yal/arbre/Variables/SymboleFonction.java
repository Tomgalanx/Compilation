package Compilation.yal.arbre.Variables;

public class SymboleFonction extends Symbole{


    private String etiquette;

    public SymboleFonction(int deplacement,String e) {
        super(deplacement);
        etiquette=e;
    }

    @Override
    public String getType() {
        return Symbole.FONCTION;
    }


    public String getEtiquette() {
        return etiquette;
    }
}
