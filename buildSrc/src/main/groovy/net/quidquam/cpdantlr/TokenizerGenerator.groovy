package net.quidquam.cpdantlr;

import java.io.File;

public class TokenizerGenerator {
    public static void generate(File lexer, File outputDir) {
        String languageName = lexer.name.replace('Lexer.java', '')
        String packageName = languagePackageName(languageName);
        File outFile = new File(outputDir, "${languageName}Tokenizer.java")

        if(packageName == null) {
            outFile.write(reflectionTokenizer(languageName))
        } else {
            outFile.write(namedTokenizer(languageName, packageName))
        }
    }

    /** Lexers with a named package are the easiest to deal with */
    public static String namedTokenizer(String languageName, String packageName) {
        return """package net.quidquam.cpdantlr.language;

import net.quidquam.cpdantlr.AbstractANTLRTokenizer;
import org.antlr.v4.runtime.ANTLRInputStream;
import ${packageName}.${languageName}Lexer;

public class ${languageName}Tokenizer extends AbstractANTLRTokenizer {
    public ${languageName}Tokenizer() {
        ANTLRInputStream stream = new ANTLRInputStream("");
        lexer = new ${languageName}Lexer(stream);
    }
}
"""
    }

    /** Most of the lexers end up in the default package, so we have
     * to use reflection to access them */
    public static String reflectionTokenizer(String languageName) {
        return """package net.quidquam.cpdantlr.language;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.quidquam.cpdantlr.AbstractANTLRTokenizer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public class ${languageName}Tokenizer extends AbstractANTLRTokenizer {
    public ${languageName}Tokenizer() {
        try {
            ANTLRInputStream stream = new ANTLRInputStream("");
            Class<?> cl = Class.forName("${languageName}Lexer");
            Constructor<?> cons = cl.getConstructor(CharStream.class);
            lexer = (Lexer) cons.newInstance(stream);
        }
        catch (InstantiationException | IllegalAccessException |
                ClassNotFoundException | NoSuchMethodException | SecurityException |
                IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
"""
    }

    /** Package names for the generated grammars can be read from the pom.xml files
     * for each language, but there are so few that they're easy to hardcode. */
    public static String languagePackageName(String s) {
        switch(s) {
            case 'ANTLRv4': return 'org.antlr.parser.antlr4'
            case 'ASM': return 'com.Ostermiller.Syntax'
            case 'STG': return 'org.antlr.parser.st4'
            case 'ST': return 'org.antlr.parser.st4'
            default: return null
        }
    }
}
