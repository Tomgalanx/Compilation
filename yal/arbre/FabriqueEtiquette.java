package Compilation.yal.arbre;

public class FabriqueEtiquette {


    private static int etiquette=0;


    /*
        Return une etiquette et incremente le compteur
     */
    public static int getEtiquette(){

        return etiquette++;
    }




}
