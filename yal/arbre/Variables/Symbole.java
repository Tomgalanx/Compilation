package Compilation.yal.arbre.Variables;

public abstract class Symbole {


    protected int dep;
    public static String VARIABLE ="Variable";
    protected static String FONCTION ="Fonction";

    public Symbole(int deplacement){
        dep= deplacement;
    }

    public int deplacement() {
        return dep;
    }

    public abstract String getType();


}
