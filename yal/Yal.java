package Compilation.yal;

import Compilation.yal.analyse.AnalyseurLexical;
import Compilation.yal.analyse.AnalyseurSyntaxique;
import Compilation.yal.arbre.ArbreAbstrait;
import Compilation.yal.arbre.TDS;
import Compilation.yal.exceptions.AnalyseException;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Yal {
    
    public Yal(String nomFichier) {
        try {


            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            TDS.getInstance().reset();

            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(arbre.toMIPS());
            flot.close() ;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new Yal(args[0]) ;
    }
    
}
