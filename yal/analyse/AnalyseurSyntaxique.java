
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Compilation.yal.analyse;

import java.util.*;

import Compilation.yal.arbre.ArbreAbstrait;
import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.expressions.ConstanteEntiere;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.expressions.ExpressionBinaire.Arithmetique.Division;
import Compilation.yal.arbre.expressions.ExpressionBinaire.Arithmetique.Multiplication;
import Compilation.yal.arbre.expressions.ExpressionBinaire.Arithmetique.Soustraction;
import Compilation.yal.arbre.expressions.IDF;
import Compilation.yal.arbre.instructions.Affectation;
import Compilation.yal.arbre.instructions.Declaration;
import Compilation.yal.arbre.instructions.Ecrire;
import Compilation.yal.arbre.instructions.Lire;
import Compilation.yal.exceptions.AnalyseSyntaxiqueException;
import Compilation.yal.arbre.expressions.ExpressionBinaire.Arithmetique.Addition;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class AnalyseurSyntaxique extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return CodesLexicaux.class;
}

  /** Default constructor. */
  @Deprecated
  public AnalyseurSyntaxique() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\004\000\002\002\007\000\002\003" +
    "\004\000\002\003\003\000\002\004\005\000\002\004\004" +
    "\000\002\004\004\000\002\004\005\000\002\010\003\000" +
    "\002\010\003\000\002\010\005\000\002\010\005\000\002" +
    "\010\005\000\002\010\005\000\002\010\004\000\002\010" +
    "\005\000\002\005\004\000\002\006\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\050\000\004\004\005\001\002\000\004\002\052\001" +
    "\002\000\004\022\006\001\002\000\004\005\007\001\002" +
    "\000\012\010\016\011\015\013\011\022\012\001\002\000" +
    "\014\006\ufffe\010\ufffe\011\ufffe\013\ufffe\022\ufffe\001\002" +
    "\000\004\022\050\001\002\000\004\012\046\001\002\000" +
    "\004\007\045\001\002\000\014\006\044\010\016\011\015" +
    "\013\011\022\012\001\002\000\004\022\042\001\002\000" +
    "\012\016\023\020\025\022\022\023\021\001\002\000\004" +
    "\007\020\001\002\000\014\006\ufffc\010\ufffc\011\ufffc\013" +
    "\ufffc\022\ufffc\001\002\000\016\007\ufff9\014\ufff9\015\ufff9" +
    "\016\ufff9\017\ufff9\021\ufff9\001\002\000\016\007\ufff8\014" +
    "\ufff8\015\ufff8\016\ufff8\017\ufff8\021\ufff8\001\002\000\012" +
    "\016\023\020\025\022\022\023\021\001\002\000\014\007" +
    "\040\014\027\015\033\016\031\017\030\001\002\000\012" +
    "\016\023\020\025\022\022\023\021\001\002\000\014\014" +
    "\027\015\033\016\031\017\030\021\032\001\002\000\012" +
    "\016\023\020\025\022\022\023\021\001\002\000\012\016" +
    "\023\020\025\022\022\023\021\001\002\000\012\016\023" +
    "\020\025\022\022\023\021\001\002\000\016\007\ufff2\014" +
    "\ufff2\015\ufff2\016\ufff2\017\ufff2\021\ufff2\001\002\000\012" +
    "\016\023\020\025\022\022\023\021\001\002\000\016\007" +
    "\ufff5\014\027\015\033\016\ufff5\017\ufff5\021\ufff5\001\002" +
    "\000\016\007\ufff6\014\027\015\033\016\031\017\030\021" +
    "\ufff6\001\002\000\016\007\ufff7\014\027\015\033\016\031" +
    "\017\030\021\ufff7\001\002\000\016\007\ufff4\014\027\015" +
    "\033\016\ufff4\017\ufff4\021\ufff4\001\002\000\014\006\ufffd" +
    "\010\ufffd\011\ufffd\013\ufffd\022\ufffd\001\002\000\016\007" +
    "\ufff3\014\027\015\033\016\031\017\030\021\ufff3\001\002" +
    "\000\004\007\ufff1\001\002\000\014\006\uffff\010\uffff\011" +
    "\uffff\013\uffff\022\uffff\001\002\000\004\002\000\001\002" +
    "\000\014\006\ufffb\010\ufffb\011\ufffb\013\ufffb\022\ufffb\001" +
    "\002\000\012\016\023\020\025\022\022\023\021\001\002" +
    "\000\014\007\ufff0\014\027\015\033\016\031\017\030\001" +
    "\002\000\004\007\051\001\002\000\014\006\ufffa\010\ufffa" +
    "\011\ufffa\013\ufffa\022\ufffa\001\002\000\004\002\001\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\050\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\012\003\013\004\007" +
    "\005\016\006\012\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\010\004\042" +
    "\005\016\006\012\001\001\000\002\001\001\000\004\010" +
    "\023\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\010\040\001\001\000" +
    "\002\001\001\000\004\010\025\001\001\000\002\001\001" +
    "\000\004\010\036\001\001\000\004\010\035\001\001\000" +
    "\004\010\034\001\001\000\002\001\001\000\004\010\033" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\010\046\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalyseurSyntaxique$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalyseurSyntaxique$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalyseurSyntaxique$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    public void report_error(String message, Object info) {

        HashMap<Integer, String> lesTerminaux = new HashMap<>() ;
    
        lesTerminaux.put(new Integer(CodesLexicaux.DEBUT), "debut") ;
        lesTerminaux.put(new Integer(CodesLexicaux.FIN), "fin") ;
        lesTerminaux.put(new Integer(CodesLexicaux.POINTVIRGULE), ";") ;

        StringBuffer m = new StringBuffer() ;

        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);

            if (s.left >= 0) {                
                m.append("\tligne : " + (s.left + 1)) ;
                if (s.right >= 0)                    
                    m.append(" colonne : " + (s.right+1)) ;
            }
            
            if (s.value != null) {
                lesTerminaux.put(CodesLexicaux.CSTENTIERE, "" + s.value) ;
            }

            if (lesTerminaux.containsKey(new Integer(s.sym))) {
                m.append(" dernier token lu : " + lesTerminaux.get(new Integer(s.sym))) ;
            }
            else {
                m.append(" expression non terminée") ;
            }

        }
        throw new AnalyseSyntaxiqueException("" + m) ;
    }

    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$AnalyseurSyntaxique$actions {


             
  private final AnalyseurSyntaxique parser;

  /** Constructor */
  CUP$AnalyseurSyntaxique$actions(AnalyseurSyntaxique parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$do_action_part00000000(
    int                        CUP$AnalyseurSyntaxique$act_num,
    java_cup.runtime.lr_parser CUP$AnalyseurSyntaxique$parser,
    java.util.Stack            CUP$AnalyseurSyntaxique$stack,
    int                        CUP$AnalyseurSyntaxique$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$result;

      /* select the action based on the action number */
      switch (CUP$AnalyseurSyntaxique$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= PROG EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait start_val = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		RESULT = start_val;
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalyseurSyntaxique$parser.done_parsing();
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // PROG ::= PROGRAMME IDF DEBUT LINST FIN 
            {
              ArbreAbstrait RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait li = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // LINST ::= LINST INST 
            {
              ArbreAbstrait RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait li = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait i = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 ((BlocDInstructions)li).ajouter(i) ;
                   RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // LINST ::= INST 
            {
              ArbreAbstrait RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait i = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 BlocDInstructions b = new BlocDInstructions(ileft + 1) ;
                   b.ajouter(i) ;
                   RESULT = b ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // INST ::= ECRIRE EXP POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = new Ecrire(e, eleft + 1) ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // INST ::= DECL_VAR POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait e = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = e; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // INST ::= AFFECT POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait e = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = e; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // INST ::= LIRE IDF POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = new Lire(ileft,i); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // EXP ::= CSTENTIERE 
            {
              Expression RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String c = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new ConstanteEntiere(c, cleft + 1) ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // EXP ::= IDF 
            {
              Expression RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new IDF(ileft, i); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // EXP ::= EXP PLUS EXP 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Addition(e1left,e1,e2); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // EXP ::= EXP MOINS EXP 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Soustraction(e1left,e1,e2);
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // EXP ::= EXP DIV EXP 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Division(e1left,e1,e2);
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // EXP ::= EXP MULT EXP 
            {
              Expression RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		Expression e1 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression e2 = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Multiplication(e1left,e1,e2);
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // EXP ::= MOINS EXP 
            {
              Expression RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression c = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new ConstanteEntiere("-"+c, cleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // EXP ::= PAROUV EXP PARFER 
            {
              Expression RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = e ;
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // DECL_VAR ::= ENTIER IDF 
            {
              ArbreAbstrait RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Declaration(ileft,i);
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL_VAR",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // AFFECT ::= IDF EGALE EXP 
            {
              ArbreAbstrait RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Affectation(ileft,i,e); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("AFFECT",4, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$AnalyseurSyntaxique$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$do_action(
    int                        CUP$AnalyseurSyntaxique$act_num,
    java_cup.runtime.lr_parser CUP$AnalyseurSyntaxique$parser,
    java.util.Stack            CUP$AnalyseurSyntaxique$stack,
    int                        CUP$AnalyseurSyntaxique$top)
    throws java.lang.Exception
    {
              return CUP$AnalyseurSyntaxique$do_action_part00000000(
                               CUP$AnalyseurSyntaxique$act_num,
                               CUP$AnalyseurSyntaxique$parser,
                               CUP$AnalyseurSyntaxique$stack,
                               CUP$AnalyseurSyntaxique$top);
    }
}

}
