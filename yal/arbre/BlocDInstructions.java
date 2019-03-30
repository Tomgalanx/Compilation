package Compilation.yal.arbre;


import Compilation.yal.arbre.instructions.DeclarationDeTableau;
import Compilation.yal.arbre.instructions.Fonction;
import Compilation.yal.arbre.instructions.RetourneFonction;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.ArrayList;
import java.util.Queue;

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
            "division:     .asciiz \"Division par 0 interdite \" \n" +
            "invalidLong:     .asciiz \"Une déclaration de tableau ne peut avoir une longeur négative ou nulle\" \n"+
            "invalidTab :       .asciiz \"Indice hors des bornes du tableau \" \n"+
            "              .align 2\n" ;

    protected static String debutCode = ".text\n" +
            "main :\n" ;
    protected static String finCode = "end :\n" +
            "    li $v0, 10" +
            "                         # retour au système\n" +
            "    syscall\n" ;


    protected static String variable = " # initialiser s7 avec sp (initialisation de la base des variables)\n" +
            "    move $s7,$sp \n";

    public void fin(StringBuilder res) {
        res.append("end :\n");
        res.append("move $v1, $v0\t \n");
        res.append("li $v0, 10\t \n");
        res.append("# retour au système\n");
        res.append("syscall\n");

        res.append("LongueurInvalide :\n");
        res.append("li $v0, 4\n");
        res.append("la $a0, invalidLong\n");
        res.append("syscall\n");
        res.append("j end\n");

        res.append("TableauInvalide :\n");
        res.append("li $v0, 4\n");
        res.append("la $a0, invalidTab\n");
        res.append("syscall\n");
        res.append("j end\n");
    }



    public void base(StringBuilder mips) {
        mips.append("# Empile le numéro de région\n");
        mips.append("li $t8, 0\n");
        mips.append("sw $t8, 0($sp)\n");
        mips.append("addi $sp, $sp, -4\n");

        mips.append("# Initialisation de la base des variables\n");
        mips.append("move $s7, $sp\n");


        mips.append("addi $sp, $sp, ");
        mips.append(TDS.getInstance().getZoneVariable());
        // System.out.println("la taille des variables est de :"+TDS.getInstance().getZoneVariable());
        mips.append("\n\n");

        mips.append("# Initialisation des variables\n");
        mips.append("li $t8, 0\n");

        for (int depl = 0; depl > TDS.getInstance().getZoneVariable(); depl -= 4) {
            mips.append("sw $t8, ");
            mips.append(depl);
            mips.append("($s7)\n");
        }


        mips.append("\n");
    }


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
            a.verifier();
        }

    }

    @Override
    public String toMIPS() {

        ArrayList<ArbreAbstrait> fonc = new ArrayList<>();
        StringBuilder sb = new StringBuilder() ;
        sb.append(zoneData);
        sb.append(debutCode);
        base(sb);
        //sb.append(variable);

        // DABORD LES TABLEAUX
        ArrayList<ArbreAbstrait> tab= new ArrayList<>();

        for (ArbreAbstrait a : programme) {
            if(a instanceof DeclarationDeTableau)
                tab.add(a);
        }

        for (ArbreAbstrait a : tab){
            sb.append(a.toMIPS()) ;

            programme.remove(a);
        }

        // LES FONCTION EN DERNIER
        for (ArbreAbstrait a : programme) {
            if(a instanceof Fonction)
                fonc.add(a);
            else
                sb.append(a.toMIPS()) ;
        }
        //sb.append(finCode) ;

        fin(sb);

        for (ArbreAbstrait a : fonc){
            sb.append(a.toMIPS()) ;
        }
        return sb.toString() ;
    }

    public String codeMipsInstruction() {

        StringBuilder sb = new StringBuilder() ;

        // DABORD LES TABLEAUX
        ArrayList<ArbreAbstrait> tab= new ArrayList<>();

        for (ArbreAbstrait a : programme) {
            if(a instanceof DeclarationDeTableau)
                tab.add(a);
        }

        for (ArbreAbstrait a : tab){
            sb.append(a.toMIPS()) ;

            programme.remove(a);
        }

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
