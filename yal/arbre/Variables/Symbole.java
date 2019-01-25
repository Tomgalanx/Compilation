package Compilation.yal.arbre.Variables;

public abstract class Symbole {


    protected int dep;

    public Symbole(int deplacement){
        dep= deplacement;
    }

    public int deplacement() {
        return dep;
    }


}
