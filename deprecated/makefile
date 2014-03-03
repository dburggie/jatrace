JC=javac
JOPT=-d classes -classpath ./src
TOPT=-d classes -classpath ./src:./test

SJ=./src/jatrace
SJB=$(SJ)/bodies
SJS=$(SJ)/skies

CJ=./classes/jatrace
CJB=$(CJ)/bodies
CJS=$(CJ)/skies

CLASSES_CJ= $(CJ)/Body.class $(CJ)/Camera.class $(CJ)/Color.class $(CJ)/linkedBody.class $(CJ)/myImage.class $(CJ)/myInterface.class $(CJ)/Ray.class $(CJ)/Sky.class $(CJ)/Tracer.class $(CJ)/Vector.class $(CJ)/World.class 
CLASSES_CJS= $(CJS)/Bluesky.class $(CJS)/Horizon.class $(CJS)/White.class
CLASSES_CJB= $(CJB)/BasicBody.class $(CJB)/Sphere.class $(CJB)/Plane.class $(CJB)/CheckPlane.class

TD=./test
CT=./classes
TESTSOURCES= $(TD)/testTracer.java $(TD)/poster.java $(TD)/windowtest.java $(TD)/room.java $(TD)/construct.java
TESTCLASSES= $(CT)/testTracer.class $(CT)/poster.class $(CT)/windowtest.class $(CT)/room.class $(CT)/construct.class

JAR=./raytrace.jar

all: $(CLASSES_CJ) $(CLASSES_CJB) $(CLASSES_CJS)

##### jatrace #####

$(CJ)/Vector.class: $(SJ)/Vector.java
	$(JC) $(JOPT) $(SJ)/Vector.java

$(CJ)/Ray.class: $(SJ)/Ray.java $(CJ)/Vector.class
	$(JC) $(JOPT) $(SJ)/Ray.java

$(CJ)/Color.class: $(SJ)/Color.java
	$(JC) $(JOPT) $(SJ)/Color.java

$(CJ)/Body.class: $(SJ)/Body.java
	$(JC) $(JOPT) $(SJ)/Body.java

$(CJ)/Sky.class: $(SJ)/Sky.java
	$(JC) $(JOPT) $(SJ)/Sky.java

$(CJ)/Camera.class: $(SJ)/Camera.java $(CJ)/Vector.class
	$(JC) $(JOPT) $(SJ)/Camera.java

$(CJ)/myImage.class: $(SJ)/myImage.java $(CJ)/Color.class
	$(JC) $(JOPT) $(SJ)/myImage.java 

$(CJ)/myInterface.class: $(SJ)/myInterface.java $(CJ)/Vector.class $(CJ)/Ray.class $(CJ)/Color.class
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
	
$(CJB)/Plane.class: $(SJB)/Plane.java $(CJB)/BasicBody.class
	$(JC) $(JOPT) $(SJB)/Plane.java

$(CJB)/CheckPlane.class: $(SJB)/CheckPlane.java $(CJB)/Plane.class
	$(JC) $(JOPT) $(SJB)/CheckPlane.java



##### Skies #####
$(CJS)/Bluesky.class: $(SJS)/Bluesky.java $(CJ)/Sky.class
	$(JC) $(JOPT) $(SJS)/Bluesky.java

$(CJS)/Horizon.class: $(SJS)/Horizon.java $(CJ)/Sky.class
	$(JC) $(JOPT) $(SJS)/Horizon.java

$(CJS)/White.class: $(SJS)/White.java
	$(JC) $(JOPT) $(SJS)/White.java

test: $(TESTCLASSES)

$(TESTCLASSES): $(TESTSOURCES)
	$(JC) $(TOPT) $(TESTSOURCES)

clean:
	rm $(CJ)/*.class
	rm $(CJB)/*.class
	rm $(CJS)/*.class
	rm classes/*.class



