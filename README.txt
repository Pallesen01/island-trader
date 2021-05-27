Steps to build and run Island Trader from command line:

1. Ensure you are in the root directory (Should contain this README, src and game-parameters)

2. Run the following command to compile source code to resuling class files:
        javac -d bin -cp src src/main/Main.java

3. To start Island Trader using the GUI run:
        java -cp bin main.Main

    To start Island Trader using TextUI run:
        java -cp bin main.Main text