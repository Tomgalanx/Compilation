package Compilation.yal.arbre.expressions.constante;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder("# Chargement de la constante \n") ;
        sb.append("li $v0, ") ;
        sb.append(cste) ;
        sb.append("\n") ;
        return sb.toString() ;
    }

    @Override
    public String getType() {
        return "arithmetique";
    }
}
