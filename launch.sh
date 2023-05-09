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
    
    java -Xmx5120M -cp bin/ Main
    
}

compile && run