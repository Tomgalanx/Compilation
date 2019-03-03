package Compilation.yal.arbre.instructions;


import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.SymboleVariable;
import Compilation.yal.exceptions.DoubleDeclarationExcepion;

import java.util.Objects;

public class Declaration extends Instruction{

    private EntreeVariable entree;
    private SymboleVariable symbole;

    public Declaration(int n,String nom) throws DoubleDeclarationExcepion {
        super(n);

        entree = new EntreeVariable(nom,noLigne);

        int dep = tds.getZoneVariable();

        symbole = new SymboleVariable(dep-4);

        tds.ajouter(entree, symbole);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {

        // Code qui permet de générér une variable

        String res = "" +
                "# réservation de l'espace pour la variables\n" +
                "    addi $sp, $sp,"+ symbole.deplacement()+"\n";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaration that = (Declaration) o;
        return Objects.equals(entree, that.entree) &&
                Objects.equals(symbole, that.symbole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entree, symbole);
    }
}
