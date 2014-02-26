JC=javac
JOPT=-d classes -classpath ./src
TOPT=-d classes -classpath ./src:./test

SJ=./src/jatrace
SJB=$(SJ)/bodies
SJS=$(SJ)/skies

CJ=./classes/jatrace
CJB=$(CJ)/bodies
CJS=$(CJ)/skies

CLASSES_CJ= $(CJ)/Body.class $(CJ)/Camera.class $(CJ)/Color.class $(CJ)/linkedBody.class $(CJ)/myImage.class $(CJ)/myInterface.class $(CJ)/Ray.class $(CJ)/Sky.class $(CJ)/Tracer.class $(CJ)/Vect.class $(CJ)/World.class 
CLASSES_CJS= $(CJS)/Bluesky.class
CLASSES_CJB= $(CJB)/BasicBody.class $(CJB)/Sphere.class

TD=./test
CT=./classes
TESTSOURCES= $(TD)/testTracer.java
TESTCLASSES= $(CT)/testTracer.class

JAR=./raytrace.jar

all: $(CLASSES_CJ) $(CLASSES_CJB) $(CLASSES_CJS)

##### jatrace #####

$(CJ)/Vect.class: $(SJ)/Vect.java
	$(JC) $(JOPT) $(SJ)/Vect.java

$(CJ)/Ray.class: $(SJ)/Ray.java $(CJ)/Vect.class
	$(JC) $(JOPT) $(SJ)/Ray.java

$(CJ)/Color.class: $(SJ)/Color.java
	$(JC) $(JOPT) $(SJ)/Color.java

$(CJ)/Body.class: $(SJ)/Body.java
	$(JC) $(JOPT) $(SJ)/Body.java

$(CJ)/Sky.class: $(SJ)/Sky.java
	$(JC) $(JOPT) $(SJ)/Sky.java

$(CJ)/Camera.class: $(SJ)/Camera.java $(CJ)/Vect.class
	$(JC) $(JOPT) $(SJ)/Camera.java

$(CJ)/myImage.class: $(SJ)/myImage.java $(CJ)/Color.class
	$(JC) $(JOPT) $(SJ)/myImage.java 

$(CJ)/myInterface.class: $(SJ)/myInterface.java $(CJ)/Vect.class $(CJ)/Ray.class $(CJ)/Color.class
	$(JC) $(JOPT) $(SJ)/myInterface.java

$(CJ)/linkedBody.class: $(SJ)/linkedBody.java $(CJ)/Body.class
	$(JC) $(JOPT) $(SJ)/linkedBody.java

$(CJ)/World.class: $(SJ)/World.java $(CJ)/Sky.class $(CJ)/linkedBody.class $(CJ)/myInterface.class
	$(JC) $(JOPT) $(SJ)/World.java

$(CJ)/Tracer.class: $(SJ)/Tracer.java $(CJ)/myImage.class $(CJ)/World.class $(CJ)/Camera.class $(CJ)/Color.class
	$(JC) $(JOPT) $(SJ)/Tracer.java



##### bodies #####
$(CJB)/BasicBody.class: $(SJB)/BasicBody.java $(CJ)/Body.class
	$(JC) $(JOPT) $(SJB)/Body.java

$(CJB)/Sphere.class: $(SJB)/Sphere.java $(CJB)/BasicBody.class
	$(JC) $(JOPT) $(SJB)/Sphere.java



##### Skies #####
$(CJS)/Bluesky.class: $(SJS)/Bluesky.java $(CJ)/Sky.class
	$(JC) $(JOPT) $(SJS)/Bluesky.java




test: $(TESTCLASSES)

$(TESTCLASSES): $(TESTSOURCES)
	$(JC) $(TOPT) $(TESTSOURCES)

clean:
	rm -r classes/*
