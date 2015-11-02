# cpd-antlr

An adapter to allow use of ANTLR v4 lexer grammars with PMD-CPD code duplication detection.

**Note:** This project and its author are not affiliated with the [PMD](https://pmd.github.io/) or [ANTLR](http://www.antlr.org/) projects.

## What is this?

CPD-ANTLR provides an adapter between two great projects.

* [PMD](https://pmd.github.io/) is a source code analyzer that, among other things, includes a [Copy/Paste Detector (CPD)](https://pmd.github.io/pmd-5.4.0/usage/cpd-usage.html). CPD is very fast and can be [easily extended to other languages](https://pmd.github.io/pmd-5.4.0/customizing/cpd-parser-howto.html).

* [ANTLR](http://www.antlr.org/) is a parser generator. Of particular note are the [sample grammars](https://github.com/antlr/grammars-v4) available for version 4, including many languages such as R, Erlang, and SQLite.

CPD-ANTLR downloads the ANTLR sample grammars, compiles them, and generates the necessary `Tokenizer` and `Language` classes to make the lexer classes available to CPD.

## Installation

To build, run `gradle build`. This builds a fat jar at `build/libs/cpd-antlr.jar` containing CPD-ANTLR as well as the compiled ANTLR grammars and all necessary dependencies. Then simply put this jar in your CLASSPATH environment variable and run CPD using the new language.

    export CLASSPATH=/path/to/cpd-antlr/build/libs/cpd-antlr.jar
    pmd-bin/bin/run.sh cpd --files /path/to/R/code/ --minimum-tokens 100 --language r

## Using your own ANTLR grammar

To use your own ANTLR grammar with CPD:

1. Create a Tokenizer class. Extend the [AbstractANTLRTokenizer](https://github.com/skonzem/cpd-antlr/blob/master/src/main/java/net/quidquam/cpdantlr/AbstractANTLRTokenizer.java) class and initialize the `lexer` variable with your own lexer in the constructor. An example of how to do this may be seen in [TokenizerGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/TokenizerGenerator.groovy).

2. Create a Language class. This class specifies the name of the language, the Tokenizer class to split the code up into tokens, and the file extensions to look for. See [LanguageGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/LanguageGenerator.groovy) for an example.

## Contributions

Pull requests are welcome. One area that could use improvement is the list of file extensions in [LanguageGenerator](https://github.com/skonzem/cpd-antlr/blob/master/buildSrc/src/main/groovy/net/quidquam/cpdantlr/LanguageGenerator.groovy).

## Licensing Notes

Note that the ANTLR sample grammars do not have a common license. Be careful when packaging and distributing that code. CPD-ANTLR is licensed under the Apache License, Version 2.0.
