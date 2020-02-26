package recognition;

import java.io.FileWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandShell shell = new CommandShell(scanner);
        shell.run();
    }
    
}
