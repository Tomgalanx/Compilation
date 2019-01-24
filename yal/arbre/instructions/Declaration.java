package yal.arbre.instructions;

import yal.arbre.Variables.EntreeVariable;
import yal.arbre.Variables.SymboleVariable;
import yal.exceptions.DoubleDeclarationExcepion;
import yal.exceptions.NonDeclareException;

public class Declaration extends Instruction{


    private EntreeVariable entree;
    private SymboleVariable symbole;

    public Declaration(int n,String nom) throws DoubleDeclarationExcepion {
        super(n);

        entree = new EntreeVariable(nom);

        int dep = tds.getZoneVariable();

        symbole = new SymboleVariable(dep-4);


        tds.ajouter(entree, symbole);
    }

    @Override
    public void verifier() throws NonDeclareException {

    }

    @Override
    public String toMIPS() {

        // Code qui permet de générér une variable

        String res = "" +
                "# réservation de l'espace pour la variables\n" +
                "    addi $s7, $s7,"+ symbole.deplacement()+"\n";
        return res;
    }
}
