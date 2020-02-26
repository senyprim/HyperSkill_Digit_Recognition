package recognition;

import java.io.File;
import java.util.Scanner;

public class CommandShell {
    int countLine=5;
    int countByLine=3;
    Scanner scanner;
    OneLayerNet net;
    boolean isExit=false;
    boolean isLearned=false;
    CommandShell(Scanner scanner){
        this.scanner=scanner;
        new CommandLearn(this).execute();
    }
    public void run(){
            System.out.print("1. Learn the network\n" +
                    "2. Guess a number\n" +
                    "Your choice: ");
            int chooseItem = Integer.parseInt(scanner.nextLine());
            switch (chooseItem) {
                case 1:
                    new CommandLearn(this).execute();
                    break;
                case 2:
                    new CommandInput(this).execute();
                    break;
                case 3:
                    new CommandExit(this).execute();
                    break;
                default:
                    throw new IllegalArgumentException("Wrong command");
            }
    }
}
