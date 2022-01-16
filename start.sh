echo "
+----------------------------------------------------------------------
|                   冒险岛079 FOR CentOS/Ubuntu/Debian
+----------------------------------------------------------------------
"

# $JAVA_7_HOME/bin/javac  -Djava.ext.dirs=./lib/ -Xlint:unchecked  -encoding UTF-8 -d ./out @./config/source
# $JAVA_7_HOME/bin/jar -cvfm ./bin/maple.jar ./config/MANIFEST.MF -C ./out .

# $JAVA_7_HOME/bin/java -cp ./bin/maple.jar -server -DhomePath=./config/ -DscriptsPath=./scripts/ -DwzPath=./scripts/wz -Xms512m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m server.Start

$JAVA_7_HOME/bin/java -Dfile.encoding=GBK  -DhomePath=./config/ -DscriptsPath=./scripts/ -DwzPath=./scripts/wz -Xms512m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m -XX:MaxNewSize=512m -jar ./bin/maple.jar
