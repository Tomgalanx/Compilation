package Compilation.yal.arbre.instructions;

import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.Variables.EntreeVariable;
import Compilation.yal.arbre.Variables.SymboleTableau;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.expressions.constante.Constante;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class DeclarationDeTableau extends Instruction {

    private  EntreeVariable entree;
    private Expression taille;
    private SymboleTableau symbole;
    private int dep;


    public DeclarationDeTableau(int n, String nom, Expression taille) {
        super(n);

        entree = new EntreeVariable(nom,noLigne);

        this.taille = taille;

        dep = tds.getZoneVariable()-4;

        symbole = new SymboleTableau(dep,tds.getNumeroBloc());

        tds.ajouter(entree, symbole);
    }

    @Override
    public void verifier() throws NonDeclareException {

        taille.verifier();


        // Verifie que la taille du tableau est de type entier
        if(!taille.getType().equals(Expression.ARITHMETIQUE))
            throw new AnalyseSemantiqueException(getNoLigne()+1, "La longeur du tableau doit etre de type entier");


        // Si la longeur du tableau est une constante
        // Utilisé dans les fonctions et le principale
        if(taille instanceof Constante){

            String tmp = taille.toString();
            int res = Integer.parseInt(tmp);

            if(res <= 0)
                throw new AnalyseSemantiqueException(getNoLigne()+1, "La longeur du tableau doit etre superieur ou egale a 0");

        }

        // Dans le programme principale la longeur du tableau est une constante
        if(tds.getNumeroBloc() == 0){
            if(taille instanceof Constante){

                String tmp = taille.toString();
                int res = Integer.parseInt(tmp);

                if(res <= 0)
                    throw new AnalyseSemantiqueException(getNoLigne()+1, "La longeur du tableau doit etre superieur ou egale a 0");

            }
            else{
                throw new AnalyseSemantiqueException(getNoLigne()+1, "La longeur du tableau doit etre une constante dans le programme principale");

            }
        }

        dep = -(symbole.deplacement());
    }

    @Override
    public String toMIPS() {


        StringBuilder res = new StringBuilder();

        // Etiquette unique
        int etiquette = FabriqueEtiquette.getEtiquette();

        res.append("# Déclaration du tableau \n");

        // On stock l'adresse du tableau
        res.append("# @Tableau\n");
        res.append("sw $sp, ");
        res.append(dep);
        res.append("($s7)\n");

        // On charge la longueur
        res.append(taille.toMIPS());

        // On vérifie que la taille du tableau est correct
        res.append("# Longueur > 0 \n");

        // Si la taille n'est pas correct on affiche un message et on arrete l'application
        res.append("blez $v0, LongueurInvalide\n");

        // On stock la longueur
        res.append("# Longueur\n");
        res.append("sw $v0, 0($sp)\n");

        res.append("# Case du tableau\n");

        res.append("tq_");
        res.append(etiquette);
        res.append(" :\n");

        res.append("beqz $v0, fintq_");
        res.append(etiquette);
        res.append("\n");

        res.append("addi $v0, $v0, -1\n");
        res.append("addi $sp, $sp, -4\n");
        res.append("sw $zero, 0($sp)\n");

        res.append("j tq_");
        res.append(etiquette);
        res.append("\n");

        res.append("fintq_");
        res.append(etiquette);
        res.append(" :\n");

        res.append("addi $sp, $sp, -4\n");

        return res.toString();
    }
}
