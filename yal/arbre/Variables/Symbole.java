package yal.arbre.Variables;

public abstract class Symbole {


    protected int dep;

    public Symbole(int deplacement){
        dep= deplacement;
    }

    public abstract int deplacement();


}
