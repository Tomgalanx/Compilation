package Compilation.yal.arbre;


import Compilation.yal.exceptions.NonDeclareException;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;
    
    protected static String zoneData = ".data\n" +
                                            "finLigne:     .asciiz \"\\n\"\n" +
                                            "              .align 2\n" ;
    
    protected static String debutCode = ".text\n" +
                                        "main :\n" ;
    protected static String finCode = "end :\n" +
                                      "    li $v0, 10" +
            "                         # retour au système\n" +
                                      "    syscall\n" ;

    protected static String variable = " # initialiser s7 avec sp (initialisation de la base des variables)\n" +
            "    move $s7,$sp \n";

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier() throws NonDeclareException {
        for (ArbreAbstrait a : programme) {
            a.verifier() ;
        }
    }
    
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData) ;
        sb.append(debutCode) ;
        sb.append(variable);
        for (ArbreAbstrait a : programme) {
            sb.append(a.toMIPS()) ;
        }
        sb.append(finCode) ;
        return sb.toString() ;
    }

}
