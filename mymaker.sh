JAVAC="javac -d classes -cp test:src"
SRC="src/jatrace"

if [ -e classes ]
then
	echo
	echo "CLEANING UP PREVIOUS BUILD..."
	echo
	rm -r classes
	if [[ $1 == "clean" ]]
	then
		exit
	fi
fi

echo
echo "BUILDING PROJECT..."
echo

mkdir -p classes
$JAVAC $SRC/*.java $SRC/bodies/*.java $SRC/skies/*.java
$JAVAC test/*.java



