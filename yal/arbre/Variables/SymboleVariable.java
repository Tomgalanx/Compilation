package yal.arbre.Variables;

public class SymboleVariable extends Symbole{


    public SymboleVariable(int deplacement) {
        super(deplacement);
    }

    @Override
    public int deplacement() {
        return dep;
    }
}
