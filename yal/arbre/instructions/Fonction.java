package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.TDS;
import Compilation.yal.arbre.Variables.EntreeFonction;
import Compilation.yal.arbre.Variables.SymboleFonction;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class Fonction extends Instruction{


    // Nom de la fonction
    private  String nom;

    // Liste des instructions de la fonction
    private  BlocDInstructions liste;

    // Numéro de ligne
    private  int parametre;

    // Numéro de bloc
    private int numeroBloc;

    // La zone variable pour la fonction
    private int zoneVariable;

    // On génére l'étiquette pour le code mips
    private String etiquette = "fonc_"+FabriqueEtiquette.getEtiquette();

    public Fonction(String nom, BlocDInstructions liste, int n){
        super(n);
        this.nom = nom;
        this.liste = liste;
        this.parametre = n;
        numeroBloc = TDS.getInstance().getNumeroBloc();
        zoneVariable = TDS.getInstance().getZoneVariable();


        EntreeFonction entree = new EntreeFonction(nom,noLigne);
        SymboleFonction symbole = new SymboleFonction(zoneVariable,etiquette,tds.getNumeroBloc());

        TDS.getInstance().ajouter(entree,symbole);

    }

    @Override
    public void verifier() throws NonDeclareException {

        EntreeFonction entreeFonction = new EntreeFonction(nom,noLigne);
        SymboleFonction symboleFonction = (SymboleFonction) TDS.getInstance().identification(entreeFonction);

        // On vérifie que la fonction a été déclaré
        if (symboleFonction == null) {
            throw new AnalyseSemantiqueException(getNoLigne()+1, "La fonction "+nom+" n'est pas déclaré ");
        }

        // On rentre dans le bloc pour faire l'analyse sémantique des instructions
        TDS.getInstance().visiteBloc();

        liste.verifier();

        if(!liste.containRetour()){
            throw new AnalyseSemantiqueException(getNoLigne()+1, "La fonction "+nom+" doit avoir un retour");
        }

        // Si on n'a pas détécté d'erreur, on peut sortir du bloc
        TDS.getInstance().sortirBloc();

    }

    @Override
    public String toMIPS() {


        StringBuilder res = new StringBuilder();


        res.append("#Préparation de la pile pour les fonctions \n");
        res.append(etiquette + " :\n");

        res.append("# @Retour\n");
        res.append("sw $ra, 0($sp)\n");
        res.append("add $sp, $sp, -4\n");


        res.append("#Dynamique\n");
        res.append("sw $s7, 0($sp)\n");
        res.append("add $sp, $sp, -4\n");

        res.append("#Numéro de bloc\n");
        res.append("li $t8, " + numeroBloc + "\n");
        res.append("sw $t8, 0($sp)\n");
        res.append("add $sp, $sp, -4\n");

        res.append("#base pour les variables de la fonctions\n");
        res.append("move $s7, $sp\n");

        res.append("#Zone variables\n");
        res.append("add $sp, $sp, -" + zoneVariable + "\n");

        res.append("# Liste d'instructions de la fonction\n");
        res.append(liste.codeMipsInstruction());

        return res.toString();
    }
}
