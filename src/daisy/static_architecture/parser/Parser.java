package daisy.static_architecture.parser;

import daisy.static_architecture.Instruction;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;

/**
 *
 * @author Wittman
 */
public class Parser {
    private List<Instruction> instructions;
    private Symbol symbol;
    private AsmLexer lexer;
    
    public Parser() {
        instructions = new ArrayList<>();
    }
    
    public void parse(InputStream file) throws IOException{
        
        lexer = new AsmLexer(file);
        
        symbol = lexer.next_token();
        while (symbol.sym != 0){
            Instruction ints = parseInstruction();
            symbol = lexer.next_token();
        }
            
    }
    
    
    private Instruction parseInstruction() throws IOException{
        Instruction inst = new Instruction();
        if(symbol.sym == sym.IDENT){
            
        }
        symbol = lexer.next_token();
        
        
        return inst;
            
    }

    public static void main(String[] args) {
        
        
        
        
    }
}
