package net.quidquam.cpdantlr;

import java.io.IOException;
import java.util.List;
import net.sourceforge.pmd.cpd.SourceCode;
import net.sourceforge.pmd.cpd.TokenEntry;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.cpd.Tokens;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractANTLRTokenizer implements Tokenizer {
    protected Lexer lexer;

    @Override
    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) throws IOException {
        List<String> codeLines = sourceCode.getCode();
        String code = StringUtils.join(codeLines, "\n");

        ANTLRInputStream stream = new ANTLRInputStream(code);
        lexer.setInputStream(stream);
        BufferedTokenStream buffer = new BufferedTokenStream(lexer);

        for(Token token : buffer.getTokens()) {
            tokenEntries.add(new TokenEntry(token.getText(), sourceCode.getFileName(), token.getLine()));
        }

        tokenEntries.add(TokenEntry.getEOF());
    }
}
