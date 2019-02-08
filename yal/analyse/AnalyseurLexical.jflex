package Compilation.yal.analyse ;


import Compilation.*;
import java.util.*;
import java_cup.runtime.*;
import Compilation.yal.exceptions.*;
import Compilation.yal.arbre.ArbreAbstrait;
import Compilation.yal.arbre.BlocDInstructions;
import Compilation.yal.arbre.expressions.ConstanteEntiere;
import Compilation.yal.arbre.expressions.Expression;
import Compilation.yal.arbre.instructions.Affectation;
import Compilation.yal.arbre.instructions.Declaration;
import Compilation.yal.arbre.instructions.Ecrire;
import Compilation.yal.arbre.expressions.IDF;
import Compilation.yal.arbre.instructions.Lire;
import Compilation.yal.exceptions.AnalyseSyntaxiqueException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+
guillemet = [\"]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

commentaireSlashSlash = [/][/].*

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }
"entier"                { return symbol(CodesLexicaux.ENTIER);}

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"lire"               { return symbol(CodesLexicaux.LIRE); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"="                     {   return symbol(CodesLexicaux.EGALE);}

{commentaireSlashSlash} { }

"("                     {   return symbol(CodesLexicaux.PAROUV);}
")"                     {   return symbol(CodesLexicaux.PARFER);}


"+"                     { return symbol(CodesLexicaux.PLUS);}
"-"                     { return symbol(CodesLexicaux.MOINS);}
"/"                     { return symbol(CodesLexicaux.DIV);}
"*"                     { return symbol(CodesLexicaux.MULT);}






{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

