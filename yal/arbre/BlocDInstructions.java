package Compilation.yal.arbre;


import Compilation.yal.arbre.instructions.Fonction;
import Compilation.yal.arbre.instructions.RetourneFonction;
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
                                            "vrai:     .asciiz \"vrai\" \n" +
                                            "faux:     .asciiz \"faux \" \n" +
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

        ArrayList<ArbreAbstrait> fonc = new ArrayList<>();
        for (ArbreAbstrait a : programme) {
                a.verifier();
        }

    }
    
    @Override
    public String toMIPS() {

        ArrayList<ArbreAbstrait> fonc = new ArrayList<>();
        StringBuilder sb = new StringBuilder("") ;
        sb.append(zoneData);
        sb.append(debutCode);
        sb.append(variable);
        for (ArbreAbstrait a : programme) {
            if(a instanceof Fonction)
                fonc.add(a);
            else
            sb.append(a.toMIPS()) ;
        }
        sb.append(finCode) ;

        for (ArbreAbstrait a : fonc){
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }

    public String codeMipsInstruction() {

        StringBuilder sb = new StringBuilder("") ;

        for (ArbreAbstrait a : programme) {
            sb.append(a.toMIPS()) ;
        }

        return sb.toString() ;
    }

    public boolean containRetour() {
        for(ArbreAbstrait a : programme){
            if(a instanceof RetourneFonction)
                return true;
        }

        return false;
    }
}
