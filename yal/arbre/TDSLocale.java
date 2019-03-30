package Compilation.yal.arbre;

import Compilation.yal.arbre.Variables.Entree;
import Compilation.yal.arbre.Variables.Symbole;
import Compilation.yal.exceptions.DoubleDeclarationExcepion;
import Compilation.yal.exceptions.NonDeclareException;

import java.util.ArrayList;
import java.util.HashMap;


// Ancienne classe TDS
// Utiliser maintenant pour chaque bloc
public class TDSLocale {

    // Le numéro de bloc actuel
    private int numeroBloc;

    private ArrayList<TDSLocale> enfants;
    private TDSLocale pere;

    // Collection
    protected HashMap<Entree, Symbole> tds;


    // Constructeur pour le main
    public TDSLocale(int numeroBloc){
        pere= null;
        this.numeroBloc = numeroBloc;
        enfants = new ArrayList<>();
        tds = new HashMap<>();
    }


    // Constructeur pour les fonctions
    public TDSLocale(TDSLocale pere, int numeroBloc) {
        enfants = new ArrayList<>();
        this.pere = pere;
        this.numeroBloc = numeroBloc;

        tds = new HashMap<>();
    }

    // Méthode qui permet de teste si un nom existe dans notre programme
    public Symbole identification(Entree e) throws NonDeclareException {

        // Si le nom existe alors on retourne son contenu

        if(tds.get(e) != null){
            //System.out.println("j'ai trouve "+e+" dans le bloc "+numeroBloc);
            return tds.get(e);
        }




        if(pere !=null){
            return pere.identification(e);
        }


        return null;

    }


    public int getNombreParametre(){

        int res = 0;

        for (Symbole s : tds.values()) {
            if (s.getType().equals(Symbole.PARAMETRE)) {
                res++;
            }


        }

        return res;
    }


    // Méthode qui permet d'ajouter un Symbole dans notre collection
    public void ajouter(Entree e, Symbole s) throws DoubleDeclarationExcepion {

        //System.out.println("ajoue "+e);

        // Si le nom existe deja dans notre collection, on retourne une exception de double declaration
        if (tds.containsKey(e)) {
            throw new DoubleDeclarationExcepion(e.getLigne());
        }

        // Sinon on l'ajoute
        else {
            tds.put(e, s);

        }

    }


    // Retourne la taille de la pile pour les variables
    public int getZoneVariable() {

        int res = -4;

        for (Symbole s : tds.values()) {
                if (s.getType().equals(Symbole.VARIABLE) || s.getType().equals(Symbole.TABLEAU)) {
                    res = res - 4;
                }


        }

        return res;

    }


    // Retourne la taille de la pile pour les parametres
    public int getZoneParametre() {

        int res = -4;

        for (Symbole s : tds.values()) {
            if (s.getType().equals(Symbole.PARAMETRE)) {
                res = res - 4;
            }




        }

        //System.out.println("le deplacement des parametres est : "+res );

        return res;

    }


    public void ajouterEnfant(TDSLocale table) {
        enfants.add(table);
    }

    public TDSLocale getPere() {
        return pere;
    }

    public TDSLocale getTable(int numeroBloc) {

        if(getNumero() == numeroBloc)
        {
            return this;
        }

        else{

            for(TDSLocale tdsLocale : enfants){
                if(tdsLocale.getNumero() == numeroBloc){
                    return tdsLocale;
                }
            }

        }

        return null;
    }

    public int getNumero() {
        return numeroBloc;
    }
}
