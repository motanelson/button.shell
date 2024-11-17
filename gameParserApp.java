//javac -cp .:antlr-4.9.2-complete.jar *.java
//java -cp .:antlr-4.9.2-complete.jar gameParserApp

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.Scanner;

public class gameParserApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("OBJECTS can be create 'CASTLE x,y' 'HOUSE x,y' 'MAN x,y' emply promp exit\n");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            if (input.trim().isEmpty()) {
                System.out.println("end game.");
                break;
            }

            try {
                // Cria um CharStream a partir da entrada do usuário
                CharStream charStream = CharStreams.fromString(input);

                // Inicializa o lexer e o parser com o CharStream
                gameLexer lexer = new gameLexer(charStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                gameParser parser = new gameParser(tokens);

                // Inicia o parser a partir da regra de entrada do arquivo gameParser
                ParseTree tree = parser.program();  // Altere "program" para a regra de entrada correta

                // Exibe a árvore sintática
                //System.out.println("----------------------------------- ");

                // Exibe os tokens da linha de entrada
                System.out.println("Tokens:");
                tokens.fill();
                for (Token token : tokens.getTokens()) {
                    if (token.getText().indexOf("CASTLE")>-1)System.out.printf("CREATING A CASTLE \n");
                    if (token.getText().indexOf("HOUSE")>-1)System.out.printf("CREATING A HOUSE \n");
                    if (token.getText().indexOf("MAN")>-1)System.out.printf("CREATING A MAN \n");
                }

            } catch (Exception e) {
                System.out.println("Erro ao analisar a linha: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}

