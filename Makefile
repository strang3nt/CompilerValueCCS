.PHONY: compile run test docs clean

compile:
	- cd CompilerValueCCS/src/main/scala/parser/ && antlr4 -package main.scala.parser -visitor 'Ccsvp.g4' -o ".antlr"
	- mill CompilerValueCCS.compile
	- mill CompilerValueCCS.reformat

run:
	mill CompilerValueCCS.run

test:
	mill CompilerValueCCS.test

docs:
	cd documentation; \
	pandoc --defaults=default.yaml --standalone

clean:
	- rm *.pdf
	- rm -r out
