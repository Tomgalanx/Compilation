package Compilation.yal.arbre.instructions;


import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.SymboleParametre;
import Compilation.yal.arbre.Variables.SymboleVariable;
import Compilation.yal.exceptions.DoubleDeclarationExcepion;

import java.util.Objects;

public class DeclarationParametre extends Instruction{

    private EntreeVariable entree;
    private SymboleParametre symbole;

    // Cette classe ajoute juste un parametre a la TDS
    public DeclarationParametre(int n,String nom) throws DoubleDeclarationExcepion {
        super(n);

        EntreeVariable entree = new EntreeVariable(nom,noLigne);

        int dep = tds.getZoneParametre()-4;

        SymboleParametre symbole = new SymboleParametre(dep,tds.getNumeroBloc());

        tds.ajouter(entree, symbole);





    }

    // Toutes cette partie n'est jamais utilisé car la classe n'est pas ajoute a l'arbre abstrait

    @Override
    public void verifier() {

    }


    // N'est jamais utilisé
    @Override
    public String toMIPS() {

        System.out.println("PETIT TEST");

        // Code qui permet de générér une variable

        String res = "" +
                "# réservation de l'espace pour la variables\n" +
                "    addi $sp, $sp,"+ symbole.deplacement()+"\n";
        return res;
    }
}
