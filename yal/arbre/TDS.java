package Compilation.yal.arbre;


import Compilation.yal.arbre.Variables.Entree;
import Compilation.yal.arbre.Variables.EntreeFonction;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.DoubleDeclarationExcepion;
import Compilation.yal.exceptions.NonDeclareException;
import Compilation.yal.exceptions.NonDeclareFonctionException;

public class TDS {


    private TDSLocale courante;
    private TDSLocale premiere;

    // Instance pour le patron singleton
    private static TDS instance = new TDS();

    // Compteur de bloc
    private static int numeroBloc=0;


    // Constructeur qui initialise la collection
    private TDS(){
        this.premiere = new TDSLocale(numeroBloc);
        courante =premiere;
    }


    // Méthode qui permet de teste si un nom existe dans notre programme
    public Symbole identification(Entree e) throws NonDeclareException {

        try {

            // Si le nom existe alors on retourne son contenue
            if (courante.identification(e) != null)
                return courante.identification(e);
                // Si il n'existe pas, on retourne une exception
            else {

                if(e instanceof EntreeFonction){
                    throw new NonDeclareFonctionException(e.getLigne());
                }
                throw new NonDeclareException(e.getLigne());
            }
        }
        catch (StackOverflowError e1){
            throw new NonDeclareException(e.getLigne());
        }

    }


    // Méthode qui permet d'ajouter un Symbole dans notre collection
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationExcepion {
        //System.out.println("ajoute "+e);
        courante.ajouter(e,s);
    }

    public int getZoneVariable(){
        return courante.getZoneVariable();
    }


    public int getZoneParametre(){
        return courante.getZoneParametre();
    }

    public void entreeBloc() {



        numeroBloc++;
        //System.out.println("entrer bloc "+ numeroBloc);
        TDSLocale table = new TDSLocale(courante, numeroBloc);
        courante.ajouterEnfant(table);
        courante = table;

    }



    public void sortirBloc(){

        //System.out.println("sortr bloc");
        courante = courante.getPere();
    }


    public void visiteBloc(){
        //System.out.println("visiter bloc");
        numeroBloc++;
        courante=courante.getTable(numeroBloc);
    }

    // Retourne l'instance unique pour la TDS
    public static TDS getInstance(){
        return instance;
    }

    public void reset() {
        numeroBloc=0;
        courante = premiere;

        //System.out.println("reset");
    }


    public int getNumeroBloc(){
        return courante.getNumero();
    }

    public int getNombreParametre(){
        return courante.getNombreParametre();
    }
}
