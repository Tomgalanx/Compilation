package Compilation.yal.arbre;


import Compilation.yal.arbre.Variables.Entree;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.DoubleDeclarationExcepion;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.HashMap;

public class TDS {


    private TDSLocale courante;

    // Instance pour le patron singleton
    private static TDS instance = new TDS();

    // Compteur de bloc
    private static int numeroBloc=0;


    // Constructeur qui initialise la collection
    private TDS(){
        courante = new TDSLocale(numeroBloc);
    }


    // Méthode qui permet de teste si un nom existe dans notre programme
    public Symbole identification(Entree e) throws NonDeclareException {

        // Si le nom existe alors on retourne son contenue
        if(courante.identification(e) != null)
            return courante.identification(e);
        // Si il n'existe pas, on retourne une exception
        else
            throw new NonDeclareException(e.getLigne());

    }


    // Méthode qui permet d'ajouter un Symbole dans notre collection
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationExcepion {
        courante.ajouter(e,s);
    }

    public int getZoneVariable(){
        return courante.getZoneVariable();
    }

    public void entreeBloc() {

        //System.out.println("entrer bloc");

        numeroBloc++;
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
        courante = courante.getTable(0);
    }


    public int getNumeroBloc(){
        return numeroBloc;
    }
}
