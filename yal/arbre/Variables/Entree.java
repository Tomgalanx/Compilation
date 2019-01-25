package Compilation.yal.arbre.Variables;

import java.util.Objects;

public abstract class Entree {


    private String nom;


    public Entree(String nom){

        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entree entree = (Entree) o;
        return Objects.equals(nom, entree.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
