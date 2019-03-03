package Compilation.yal.arbre.Variables;

public class SymboleVariable extends Symbole{


    public SymboleVariable(int deplacement) {
        super(deplacement);
    }

    @Override
    public String getType() {
        return Symbole.VARIABLE;
    }

}
