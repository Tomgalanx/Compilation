package Compilation.yal.arbre.Variables;

public class SymboleVariable extends Symbole{


    public SymboleVariable(int deplacement,int numeroBloc) {
        super(deplacement,numeroBloc);
    }

    @Override
    public String getType() {
        return Symbole.VARIABLE;
    }

}
