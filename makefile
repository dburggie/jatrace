JC=javac
JOPT=-d classes -classpath ./src
ST=./src/threeD
STB=$(ST)/bodies
STS=$(ST)/skies
CT=./classes/threeD
CTB=$(CT)/bodies
CTS=$(CT)/skies
CLASSES= $(CT)/Vect.class $(CT)/Ray.class $(CT)/Color.class $(CTS)/Bluesky.class $(CTB)/Sphere.class
TEST=./test/
JAR=./raytrace.jar

all: $(CLASSES)

$(CT)/Vect.class: $(ST)/Vect.java
	$(JC) $(JOPT) $(ST)/Vect.java

$(CT)/Ray.class: $(ST)/Ray.java $(CT)/Vect.class
	$(JC) $(JOPT) $(ST)/Ray.java

$(CT)/Color.class: $(ST)/Color.java
	$(JC) $(JOPT) $(ST)/Color.java

$(CTB)/Sphere.class: $(STB)/Sphere.java
	$(JC) $(JOPT) $(STB)/Sphere.java

$(CTS)/Bluesky.class: $(STS)/Bluesky.java
	$(JC) $(JOPT) $(STS)/Bluesky.java

clean:
	rm $(CLASSES)
