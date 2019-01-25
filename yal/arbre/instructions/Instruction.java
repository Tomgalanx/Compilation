package Compilation.yal.arbre.instructions;


import Compilation.yal.arbre.ArbreAbstrait;
import Compilation.yal.arbre.TDS;

public abstract class Instruction extends ArbreAbstrait {


    protected TDS tds = TDS.getInstance();


    protected Instruction(int n) {
        super(n);
    }

}
