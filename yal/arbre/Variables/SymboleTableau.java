package Compilation.yal.arbre.Variables;


public class SymboleTableau extends Symbole {

    public SymboleTableau(int deplacement, int numeroBloc) {
        super(deplacement, numeroBloc);
    }

    @Override
    public String getType() {
        return Symbole.TABLEAU;
    }
}
