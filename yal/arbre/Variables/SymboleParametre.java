package Compilation.yal.arbre.Variables;


import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.expressions.Expression;

public class SymboleParametre extends Symbole{

    private static final int DEBUT_PAR = 24;

    public SymboleParametre(int dep,int n){
        super(dep,n);
        super.dep = DEBUT_PAR + dep;

    }

    public SymboleParametre(int numeroBloc) {
        super(numeroBloc);
        super.dep = DEBUT_PAR + TDS.getInstance().getZoneParametre();
    }

    @Override
    public String getType() {
        return Expression.PARAMETRE;
    }
}
