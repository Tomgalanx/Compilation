package yal.arbre;

import yal.arbre.Variables.Entree;
import yal.arbre.Variables.Symbole;
import yal.arbre.Variables.SymboleVariable;
import yal.exceptions.DoubleDeclarationExcepion;
import yal.exceptions.NonDeclareException;

import java.util.HashMap;

public class TDS {

    // Collection
    private HashMap<Entree,Symbole> tds;

    // Instance pour le patron singleton
    private static TDS instance = new TDS();


    // Constructeur qui initialise la collection
    private TDS(){
        tds = new HashMap<Entree, Symbole>();
    }


    // Méthode qui permet de teste si un nom existe dans notre programme
    public Symbole identification(Entree e) throws NonDeclareException {

        // Si le nom existe alors on retourne son contenue
        if(tds.get(e) != null)
            return tds.get(e);
        // Si il n'existe pas, on retourne une exception
        else
            throw new NonDeclareException();
    }


    // Méthode qui permet d'ajouter un Symbole dans notre collection
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationExcepion {

        // Si le nom existe deja dans notre collection, on retourne une exception de double declaration
        if(tds.containsKey(e)){
            throw new DoubleDeclarationExcepion();
        }

        // Sinon on l'ajoute
        else{
            tds.put(e,s);
        }

    }


    // Retourn la taille de la pile pour les variables
    public int getZoneVariable(){

        int res=0;

        for(Symbole s : tds.values()){

            if(s instanceof SymboleVariable){
                res = res -4;
            }

        }

        return res;

    }


    // Retourne l'instance unique pour la TDS
    public static TDS getInstance(){
        return instance;
    }
}
