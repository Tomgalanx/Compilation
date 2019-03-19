package Compilation.yal.arbre.expressions.Binaire.Arithmetique;


import Compilation.yal.arbre.FabriqueEtiquette;
import Compilation.yal.arbre.expressions.Binaire.ExpressionBinaire;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.exceptions.AnalyseSemantiqueException;
import Compilation.yal.exceptions.NonDeclareException;

public class Division extends ExpressionBinaire {

    public Division(int n, Expression gauche, Expression droite) {
        super(n, gauche, droite);
    }

    @Override
    public void verifier() throws NonDeclareException {
        gauche.verifier();
        droite.verifier();

        if(!gauche.getType().equals(Expression.ARITHMETIQUE) || !droite.getType().equals(Expression.ARITHMETIQUE)){
            throw new AnalyseSemantiqueException(getNoLigne(),"Les opérandes doivent etre de type Arithmetiques");
        }
    }

    @Override
    public String toMIPS() {


        // Lo quotien
        // Hi reste

        StringBuilder res = new StringBuilder();
        int etiquette = FabriqueEtiquette.getEtiquette();

        // On charge les operandes gauche et droit dans le bon registre avec la classe ExpressionBinaire
        res.append(super.toMIPS());

        // Ici on vérifie que l'opérande de droite n'est pas égale a 0
        res.append("beqz $v0, alors_" + etiquette + "\n");

        // Si opérande droit != 0 alors on fait la division
        res.append("div $v0, $t8, $v0\n");
        res.append("j verifDiv_" + etiquette + "\n");

        // Sinon on écrit un message d'erreur
        res.append("alors_" + etiquette + " :\n");
        res.append("li $v0, 4\n");
        res.append("la $a0,division\n");
        res.append("syscall\n");
        // et on arrete l'application
        res.append("j end\n");


        res.append("verifDiv_" + etiquette + " :\n");

        return res.toString();
    }

    @Override
    public String getType() {
        return Expression.ARITHMETIQUE;
    }
}
