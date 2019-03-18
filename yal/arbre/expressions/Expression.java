package Compilation.yal.arbre.expressions;

import Compilation.yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {


    public static String PARAMETRE = "para";
    public static String BOOLEEN = "boolean";
    public static String ARITHMETIQUE = "arithmetique";
    public static String IDF ="idf";
    
    protected Expression(int n) {
        super(n) ;
    }


    public abstract String getType();
}
