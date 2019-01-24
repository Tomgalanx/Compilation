package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.TDS;

public abstract class Instruction extends ArbreAbstrait {


    protected TDS tds = TDS.getInstance();


    protected Instruction(int n) {
        super(n);
    }

}
