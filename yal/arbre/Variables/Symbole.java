package Compilation.yal.arbre.Variables;

public abstract class Symbole {


    protected int dep;
    protected int numeroBloc;
    public static String VARIABLE ="Variable";
    protected static String FONCTION ="Fonction";

    public Symbole(int deplacement,int numeroBloc){
        dep= deplacement;
        this.numeroBloc = numeroBloc;
    }

    public int deplacement() {
        return dep;
    }

    public abstract String getType();


}
