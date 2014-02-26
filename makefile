JC=javac
JOPT=-d classes -classpath ./src
TOPT=-d ./classes -classpath ./src:./test

SJ=./src/jatrace
SJT=$(SJ)/threeD
SJTB=$(SJT)/bodies
SJTS=$(SJT)/skies

CJ=./classes/jatrace
CJT=$(CJ)/threeD
CJTB=$(CJT)/bodies
CJTS=$(CJT)/skies

CLASSES_CJ=$(CJ)/myImage.class $(CJ)/myInterface.class $(CJ)/linkedBody.class $(CJ)/World.class $(CJ)/Tracer.class
CLASSES_CJT= $(CJT)/Vect.class $(CJT)/Ray.class $(CJT)/Color.class $(CJT)/Camera.class
CLASSES_CJTS= $(CJTS)/Sky.class $(CJTS)/Bluesky.class
CLASSES_CJTB= $(CJTB)/BodyInterface.class $(CJTB)/Body.class $(CJTB)/Sphere.class

TD=./test
CT=./classes
TESTSOURCES= $(TD)/testTracer.java
TESTCLASSES= $(CT)/testTracer.class

JAR=./raytrace.jar

all: $(CLASSES_CJT) $(CLASSES_CJTB) $(CLASSES_CJTS)  $(CLASSES_CJ)

##### threeD #####
$(CJT)/Vect.class: $(SJT)/Vect.java
	$(JC) $(JOPT) $(SJT)/Vect.java

$(CJT)/Ray.class: $(SJT)/Ray.java $(CJT)/Vect.class
	$(JC) $(JOPT) $(SJT)/Ray.java

$(CJT)/Camera.class: $(SJT)/Camera.java $(CJT)/Vect.class
	$(JC) $(JOPT) $(SJT)/Camera.java

$(CJT)/Color.class: $(SJT)/Color.java
	$(JC) $(JOPT) $(SJT)/Color.java



##### bodies #####
$(CJTB)/BodyInterface.class: $(SJTB)/BodyInterface.java
	$(JC) $(JOPT) $(SJTB)/BodyInterface.java

$(CJTB)/Body.class: $(SJTB)/Body.java $(CJTB)/BodyInterface.class
	$(JC) $(JOPT) $(SJTB)/Body.java

$(CJTB)/Sphere.class: $(SJTB)/Sphere.java $(CJTB)/Body.class
	$(JC) $(JOPT) $(SJTB)/Sphere.java



##### Skies #####
$(CJTS)/Sky.class: $(SJTS)/Sky.java
	$(JC) $(JOPT) $(SJTS)/Sky.java

$(CJTS)/Bluesky.class: $(SJTS)/Bluesky.java $(CJTS)/Sky.class
	$(JC) $(JOPT) $(SJTS)/Bluesky.java



##### jatrace #####
$(CJ)/myImage.class: $(SJ)/myImage.java $(CJT)/Color.class
	$(JC) $(JOPT) $(SJ)/myImage.java 

$(CJ)/myInterface.class: $(SJ)/myInterface.java $(CJT)/Vect.class $(CJT)/Ray.class $(CJT)/Color.class
	$(JC) $(JOPT) $(SJ)/myInterface.java

$(CJ)/linkedBody.class: $(SJ)/linkedBody.java $(CJTB)/Body.class
	$(JC) $(JOPT) $(SJ)/linkedBody.java

$(CJ)/World.class: $(SJ)/World.java $(CJ)/linkedBody.class $(CJ)/myInterface.class
	$(JC) $(JOPT) $(SJ)/World.java

$(CJ)/Tracer.class: $(SJ)/Tracer.java $(CJ)/myImage.class $(CJ)/World.class $(CJT)/Camera.class $(CJT)/Color.class
	$(JC) $(JOPT) $(SJ)/Tracer.java

test: $(TESTCLASSES)

$(TESTCLASSES): $(TESTSOURCES)
	$(JC) $(TOPT) $(TESTSOURCES)

clean:
	rm -r classes/*
