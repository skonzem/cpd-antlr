package net.quidquam.cpdantlr;

import java.io.File;

public class LanguageGenerator {
    public static void generate(File lexer, File outputDir) {
        String languageName = lexer.name.replace('Lexer.java', '')
        String languageTerseName = languageName.toLowerCase()
        String languageFullName = languageFullName(languageName)
        String extensions = fileExtensions(languageName)

        // Most of the lexers end up in the default package,
        // so we have to use reflection to access them
        new File(outputDir, "${languageName}Language.java").
            write("""package net.quidquam.cpdantlr.language;

import net.sourceforge.pmd.cpd.AbstractLanguage;
import net.sourceforge.pmd.cpd.Language;

public class ${languageName}Language extends AbstractLanguage implements Language {
    public ${languageName}Language(){
        super( "${languageFullName}", "${languageTerseName}", new ${languageName}Tokenizer(), ${extensions} );
    }
}
""")
    }

    public static String languageFullName(String s) {
        switch(s) {
            case 'Abnf': return 'Augmented BNF'
            case 'ANTLRv4': return 'ANTLRv4'
            case 'arithmetic': return 'arithmetic'
            case 'ASM': return 'ASM'
            case 'asm6502': return '6502 Assember'
            case 'ASN': return 'ASN'
            case 'ATL': return 'ATL'
            case 'jvmBasic': return 'jvmBASIC'
            case 'bnf': return 'BNF'
            case 'C': return 'C'
            case 'calculator': return 'calculator'
            case 'CLIF': return 'CLIF'
            case 'Clojure': return 'Clojure'
            case 'Cobol85': return 'Cobol85'
            case 'Cobol85Preprocessor': return 'Cobol85Preprocessor'
            case 'CPP14': return 'C++14'
            case 'creole': return 'Creole'
            case 'CSharp4': return 'C#'
            case 'CSV': return 'CSV'
            case 'DOT': return 'DOT'
            case 'Erlang': return 'Erlang'
            case 'fasta': return 'FASTA'
            case 'FOL': return 'First order logic'
            case 'gff3': return 'Generic Feature Format 3'
            case 'GraphQL': return 'GraphQL'
            case 'HTML': return 'HTML'
            case 'ICalendar': return 'iCalendar'
            case 'IDL': return 'Interface Definition Language 3.5'
            case 'IRI': return 'Internationalized Resource Identifiers'
            case 'Java': return 'Java'
            case 'Java8': return 'Java 8'
            case 'JSON': return 'JSON'
            case 'Less': return 'Less'
            case 'logo': return 'logo'
            case 'Lua': return 'Lua'
            case 'memcached_protocol': return 'Memcached Protocol'
            case 'MPS': return 'Mathematical Programming System'
            case 'mumath': return 'muMATH'
            case 'mumps': return 'MUMPS'
            case 'MySQL': return 'MySQL'
            case 'ObjC': return 'ObjC'
            case 'pascal': return 'Pascal'
            case 'PCRE': return 'PCRE'
            case 'PeopleCode': return 'PeopleCode'
            case 'PGN': return 'Portable Game Notation'
            case 'propcalc': return 'Propositional Calculus'
            case 'python3': return 'Python 3'
            case 'R': return 'R'
            case 'redcode': return 'RedCode'
            case 'Scala': return 'Scala'
            case 'Scss': return 'Scss'
            case 'Smalltalk': return 'Smalltalk'
            case 'Sparql': return 'SPARQL'
            case 'SQLite': return 'SQLite'
            case 'StackTrace': return 'StackTrace'
            case 'STG': return 'STG'
            case 'ST': return 'ST'
            case 'SUOKIF': return 'SUOKIF'
            case 'tnsnames': return 'tnsnames'
            case 'tnt': return 'Typographical Number Theory'
            case 'TURTLE': return 'TURTLE'
            case 'UCBLogo': return 'UCB Logo'
            case 'VisualBasic6': return 'VisualBasic 6'
            case 'Verilog2001': return 'Verilog 2001'
            case 'vhdl': return 'VHDL'
            case 'WebIDL': return 'WebIDL'
            case 'XML': return 'XML'
            case 'xpath': return 'xpath'
            default: return s
        }
    }

    public static String fileExtensions(String s) {
        switch(s) {
            case 'Abnf': return '".abnf"'
            case 'ANTLRv4': return '".g4"'
            case 'arithmetic': return '".txt"'
            case 'ASM': return '".asm"'
            case 'asm6502': return '".txt"'
            case 'ASN': return '".asn"'
            case 'ATL': return '".atl"'
            case 'jvmBasic': return '".bas"'
            case 'bnf': return '".bnf"'
            case 'C': return '".c", ".h"'
            case 'calculator': return '".txt"'
            case 'CLIF': return '".clif"'
            case 'Clojure': return '".clj", ".cljs", ".cljc", ".edn"'
            case 'Cobol85': return '".cbl", ".cob", ".cpy"'
            case 'Cobol85Preprocessor': return '".cbl", ".cob", ".cpy"'
            case 'CPP14': return '".cc", ".cpp", ".cxx", ".C", ".c++", ".h", ".hh"'
            case 'creole': return '".txt"'
            case 'CSharp4': return '".csharp4"'
            case 'CSV': return '".csv"'
            case 'DOT': return '".dot"'
            case 'ECMAscript': return '".es", ".js"'
            case 'Erlang': return '".erl"'
            case 'fasta': return '".faa", ".ffn", ".fna"'
            case 'FOL': return '".fol"'
            case 'gff3': return '".gff"'
            case 'GraphQL': return '".graphql"'
            case 'HTML': return '".html", ".htm"'
            case 'ICalendar': return '".ical", ".ics", ".ifb", ".icalendar"'
            case 'IDL': return '".idl"'
            case 'IRI': return '".iri"'
            case 'Java': return '".java"'
            case 'Java8': return '".java"'
            case 'JSON': return '".json"'
            case 'Less': return '".less"'
            case 'logo': return '".txt"'
            case 'Lua': return '".lua"'
            case 'memcached_protocol': return '".memcached_protocol"'
            case 'MPS': return '".mps"'
            case 'mumath': return '".mumath"'
            case 'mumps': return '".m"'
            case 'MySQL': return '".sql"'
            case 'ObjC': return '".h", ".m", ".mm", ".C"'
            case 'pascal': return '".pas", ".pp", ".inc"'
            case 'PCRE': return '".pl"'
            case 'PeopleCode': return '".pcode"'
            case 'PGN': return '".pgn"'
            case 'propcalc': return '".txt"'
            case 'python3': return '".py"'
            case 'R': return '".r", ".s"'
            case 'redcode': return '".txt"'
            case 'Scala': return '".scala"'
            case 'Scss': return '".scss"'
            case 'Smalltalk': return '".st"'
            case 'Sparql': return '".txt"'
            case 'SQLite': return '".sql"'
            case 'StackTrace': return '".stacktrace"'
            case 'STG': return '".st"'
            case 'ST': return '".st"'
            case 'SUOKIF': return '".txt"'
            case 'tnsnames': return '".ora"'
            case 'tnt': return '".txt"'
            case 'TURTLE': return '".ttl"'
            case 'UCBLogo': return '".txt"'
            case 'VisualBasic6': return '".vb", ".vbs", ".wsf", ".jsl", ".cs"'
            case 'Verilog2001': return '".v"'
            case 'vhdl': return '".vhd", ".vhdl"'
            case 'WebIDL': return '".webidl"'
            case 'XML': return '".xml", ".xsl", ".xslt"'
            case 'xpath': return '".txt"'
            default: return '".${s}"'
        }
    }
}
