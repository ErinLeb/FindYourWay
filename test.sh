#ajouter #!/bin.sh ?

compile (){
    
    echo "Compiling..."
    
    find src -name "*.java" > sources.txt
    javac --class-path="src:lib/junit-platform-console-standalone-1.9.2.jar" -d bin @sources.txt
    rm sources.txt
    
    echo "Done!"
    
}

run (){
    
    echo "Launching..."
    
    java -jar -Xmx2560M lib/junit-platform-console-standalone-1.9.2.jar --classpath=bin --scan-classpath --include-classname='.*';   
    
}

compile && run