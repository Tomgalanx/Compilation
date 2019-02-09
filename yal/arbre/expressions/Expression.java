package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {


    public static String BOOLEEN = "boolean";
    public static String ARITHMETIQUE = "arithmetique";
    
    protected Expression(int n) {
        super(n) ;
    }


    public abstract String getType();

}
