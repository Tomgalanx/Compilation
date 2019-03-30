package Compilation.yal.arbre.Variables;

import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.expressions.Expression;

public class SymboleParametre extends Symbole{

    private static int PARAM = 24;

    public SymboleParametre(int dep,int n){
        super(dep,n);


        super.dep = PARAM + dep;



    }


    @Override
    public String getType() {
        return Symbole.PARAMETRE;
    }
}
