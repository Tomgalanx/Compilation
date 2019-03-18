package Compilation.yal.arbre.Variables;

import java.util.Objects;

public class EntreeFonction extends Entree{

    private int nombre;

    public EntreeFonction(String nom, int nombre, int ligne) {
        super(nom,ligne);

        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        return Objects.hash(nom,nombre);
    }

}
