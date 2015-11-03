# cpd-antlr

An adapter to allow use of ANTLR v4 lexer grammars with PMD-CPD code duplication detection.

**Note:** This project and its author are not affiliated with the [PMD](https://pmd.github.io/) or [ANTLR](http://www.antlr.org/) projects.

## What is this?

CPD-ANTLR provides an adapter between two great projects.

* [PMD](https://pmd.github.io/) is a source code analyzer that, among other things, includes a [Copy/Paste Detector (CPD)](https://pmd.github.io/pmd-5.4.0/usage/cpd-usage.html). CPD is very fast and can be [easily extended to other languages](https://pmd.github.io/pmd-5.4.0/customizing/cpd-parser-howto.html).

* [ANTLR](http://www.antlr.org/) is a parser generator. Of particular note are the [sample grammars](https://github.com/antlr/grammars-v4) available for version 4, including many languages such as R, Erlang, and SQLite.

CPD-ANTLR downloads the ANTLR sample grammars, compiles them, and generates the necessary `Tokenizer` and `Language` classes to make the lexer classes available to CPD.

## Usage

This code seems to be working now.

To build, run `gradle build`. This builds a fat jar at `build/libs/cpd-antlr.jar` containing CPD-ANTLR as well as the compiled ANTLR grammars and all necessary dependencies. Then simply put this jar in your CLASSPATH environment variable and run CPD using the new language.

    export CLASSPATH=/path/to/cpd-antlr/build/libs/cpd-antlr.jar
    pmd-bin/bin/run.sh cpd --files /path/to/R/code/ --minimum-tokens 100 --language r

If you view the CPD help with `pmd-bin/bin/run.sh cpd -h`, you'll see this delightful list of supported languages:

    Supported languages: [csharp4, scss, csv, arithmetic, vhdl, ecmascript, plsql,
    webidl, java, mumath, xml, atl, logo, html, mysql, bnf, cobol85, verilog2001,
    gff3, matlab, calculator, cpp14, cpp, python, sqlite, turtle, visualbasic6,
    stg, less, idl, mps, abnf, stacktrace, cobol85preprocessor, suokif, tnt,
    mumps, tnsnames, fol, jvmbasic, scala, clojure, jsp, pascal, dot, erlang,
    fasta, pcre, xpath, java8, pgn, fortran, icalendar, json, graphql, iri, st,
    c, clif, go, objectivec, antlrv4, asm6502, ruby, cs, memcached_protocol,
    redcode, r, propcalc, creole, lua, php, asm, peoplecode, sparql, asn,
    smalltalk, objc, ucblogo]

## Using your own ANTLR grammar

To use your own ANTLR grammar with CPD:

1. Create a Tokenizer class. Extend the [AbstractANTLRTokenizer](https://github.com/skonzem/cpd-antlr/blob/master/src/main/java/net/quidquam/cpdantlr/AbstractANTLRTokenizer.java) class and initialize the `lexer` variable with your own lexer in the constructor. An example of how to do this may be seen in [TokenizerGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/TokenizerGenerator.groovy).

2. Create a Language class. This class specifies the name of the language, the Tokenizer class to split the code up into tokens, and the file extensions to look for. See [LanguageGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/LanguageGenerator.groovy) for an example.

3. Make sure a line with your fully-qualified Language class name ends up in `META-INF/services/net.sourceforge.pmd.cpd.Language` in the compiled jar file. PMD uses [ServiceLoader](https://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html) to discover other langues from jars. The service list in this project is automatically generated within `build.gradle`.

For others who wish to add languages to PMD-CPD, an important tip is to make sure not to include the PMD code in your fat jar, as this may impair service loading.

## Contributions

Pull requests are welcome. One area that could use improvement is the list of file extensions in [LanguageGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/LanguageGenerator.groovy).

## Licensing Notes

Note that the ANTLR sample grammars do not have a common license. Be careful when packaging and distributing that code. CPD-ANTLR is licensed under the Apache License, Version 2.0.
