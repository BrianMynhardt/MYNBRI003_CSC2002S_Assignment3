# MakeFile
# Brian Mynhardt
# 26 February 2018

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= H_node.class H_map.class \
	 HashAssignment.class insertOPcount.class getOPcount.class
	 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

run1:
	java -cp bin HashAssignment
run2:
	java -cp bin insertOPcount > results/insertCounts.txt
run3:
	java -cp bin getOPcount > results/getCounts.txt
runDocuments:
	javadoc ./src/*.java -d ./docs
