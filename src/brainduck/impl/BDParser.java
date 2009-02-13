
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Thu Feb 12 11:38:49 CET 2009
//----------------------------------------------------

package brainduck.impl;

import java_cup.runtime.Symbol;
import plugins.compiler.IMessageReporter;
import plugins.compiler.IToken;
import brainduck.tree.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Thu Feb 12 11:38:49 CET 2009
  */
public class BDParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public BDParser() {super();}

  /** Constructor which sets the default scanner. */
  public BDParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public BDParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\017\000\002\002\004\000\002\002\003\000\002\002" +
    "\005\000\002\003\004\000\002\003\003\000\002\005\003" +
    "\000\002\005\002\000\002\004\003\000\002\004\003\000" +
    "\002\004\003\000\002\004\003\000\002\004\003\000\002" +
    "\004\003\000\002\004\003\000\002\004\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\022\000\030\002\ufffb\004\015\005\017\006\016\007" +
    "\007\010\012\011\013\012\010\013\020\014\ufffb\015\014" +
    "\001\002\000\006\002\000\014\000\001\002\000\006\002" +
    "\023\014\022\001\002\000\006\002\ufffd\014\ufffd\001\002" +
    "\000\010\002\ufff7\014\ufff7\015\ufff7\001\002\000\010\002" +
    "\ufff4\014\ufff4\015\ufff4\001\002\000\010\002\ufffb\014\ufffb" +
    "\015\014\001\002\000\010\002\ufff6\014\ufff6\015\ufff6\001" +
    "\002\000\010\002\ufff5\014\ufff5\015\ufff5\001\002\000\006" +
    "\002\ufffc\014\ufffc\001\002\000\010\002\ufffa\014\ufffa\015" +
    "\ufffa\001\002\000\010\002\ufff8\014\ufff8\015\ufff8\001\002" +
    "\000\010\002\ufff9\014\ufff9\015\ufff9\001\002\000\010\002" +
    "\ufff3\014\ufff3\015\ufff3\001\002\000\006\002\ufffe\014\ufffe" +
    "\001\002\000\030\002\ufffb\004\015\005\017\006\016\007" +
    "\007\010\012\011\013\012\010\013\020\014\ufffb\015\014" +
    "\001\002\000\004\002\001\001\002\000\006\002\uffff\014" +
    "\uffff\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\022\000\012\002\004\003\003\004\010\005\005\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\005\020\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\010\003\023\004\010" +
    "\005\005\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$BDParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$BDParser$actions(this);
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
    return action_obj.CUP$BDParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** User initialization code. */
  public void user_init() throws java.lang.Exception
    {
 errorCount = 0; 
    }


    private IMessageReporter reporter = null;
    public IToken lastToken;
    public int errorCount = 0;
    
    public BDParser(java_cup.runtime.Scanner s, IMessageReporter reporter) {
        this(s);
        this.reporter = reporter;
    }
    	
    public void syntax_error(Symbol current) {
        errorCount++;
        report_error("Syntax error: ",current);
    }

    public void unrecovered_syntax_error(Symbol current) {
        errorCount++;
        report_error("Fatal syntax error: ", current);
        done_parsing();
    }

    public void report_error(String message, Symbol current) {
        String mes;

        IToken t = (IToken)current;
        mes = message + t.getErrorString() + " ('"+t.getText()+"')";

        reporter.report(t.getLine()+1, t.getColumn(),mes, IMessageReporter.TYPE_ERROR);
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$BDParser$actions {
  private final BDParser parser;

  /** Constructor */
  CUP$BDParser$actions(BDParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$BDParser$do_action(
    int                        CUP$BDParser$act_num,
    java_cup.runtime.lr_parser CUP$BDParser$parser,
    java.util.Stack            CUP$BDParser$stack,
    int                        CUP$BDParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$BDParser$result;

      /* select the action based on the action number */
      switch (CUP$BDParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Statement ::= ENDL 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.ENDL); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Statement ::= LOOP 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.LOOP); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Statement ::= LOAD 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.LOAD); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Statement ::= PRINT 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.PRINT); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Statement ::= DECV 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.DECV); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Statement ::= INCV 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.INCV); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Statement ::= DEC 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.DEC); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Statement ::= INC 
            {
              Statement RESULT =null;
		 RESULT = new Statement(Statement.INC); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Comment ::= 
            {
              Object RESULT =null;

              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Comment",3, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Comment ::= TCOMMENT 
            {
              Object RESULT =null;

              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Comment",3, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Row ::= Comment 
            {
              Row RESULT =null;
		 RESULT = null; 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Row",1, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Row ::= Statement Comment 
            {
              Row RESULT =null;
		int stmtleft = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).left;
		int stmtright = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).right;
		Statement stmt = (Statement)((java_cup.runtime.Symbol) CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).value;
		 RESULT = new Row(stmt); 
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Row",1, ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Program ::= Program EOL Row 
            {
              Program RESULT =null;
		int programleft = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-2)).left;
		int programright = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-2)).right;
		Program program = (Program)((java_cup.runtime.Symbol) CUP$BDParser$stack.elementAt(CUP$BDParser$top-2)).value;
		int rowleft = ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()).left;
		int rowright = ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()).right;
		Row row = (Row)((java_cup.runtime.Symbol) CUP$BDParser$stack.peek()).value;
		
                if (row != null) program.addRow(row);
                RESULT = program;
            
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Program",0, ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-2)), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Program ::= Row 
            {
              Program RESULT =null;
		int rowleft = ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()).left;
		int rowright = ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()).right;
		Row row = (Row)((java_cup.runtime.Symbol) CUP$BDParser$stack.peek()).value;
		
                Program program = new Program();
                if (row != null) program.addRow(row);
                RESULT = program;
            
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("Program",0, ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          return CUP$BDParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).right;
		Program start_val = (Program)((java_cup.runtime.Symbol) CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)).value;
		RESULT = start_val;
              CUP$BDParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$BDParser$stack.elementAt(CUP$BDParser$top-1)), ((java_cup.runtime.Symbol)CUP$BDParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$BDParser$parser.done_parsing();
          return CUP$BDParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

