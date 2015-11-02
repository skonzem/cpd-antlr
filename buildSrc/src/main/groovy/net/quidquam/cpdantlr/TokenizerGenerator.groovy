package net.quidquam.cpdantlr;

import java.io.File;

public class TokenizerGenerator {
    public static void generate(File lexer, File outputDir) {
        String languageName = lexer.name.replace('Lexer.java', '')

        // Most of the lexers end up in the default package,
        // so we have to use reflection to access them
        new File(outputDir, "${languageName}Tokenizer.java").
            write("""package net.quidquam.cpdantlr.language;

import net.quidquam.cpdantlr.AbstractANTLRTokenizer;
import org.antlr.v4.runtime.Lexer;

public class ${languageName}Tokenizer extends AbstractANTLRTokenizer {
    public ${languageName}Tokenizer() {
        try {
            lexer = (Lexer) Class.forName("${languageName}Lexer").newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
""")
    }
}
