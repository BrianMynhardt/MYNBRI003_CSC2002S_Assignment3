# MakeFile
# Brian Mynhardt


JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= Tree.class LauncherParallel.class LauncherSequential.class \
	  Sequentialtrees.class ParallelTrees.class SumArray.class 
	 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

runSequential:
	java -cp bin LauncherSequential 600_input.txt SequentialOutput.txt
runParallel:
	java -cp bin LauncherParallel 600_input.txt ParallelOutput.txt
runDocuments:
	javadoc ./src/*.java -d ./docs
