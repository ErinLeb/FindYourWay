#ajouter #!/bin.sh ?

compile (){
    
    echo "Compiling..."
    
    find src/main/java -name "*.java" > sources.txt
    javac -d bin @sources.txt
    rm sources.txt
    
    echo "Done!"
    
}

run (){
    
    echo "Launching..."
    
    java -cp bin/ Main
    
}

compile && run