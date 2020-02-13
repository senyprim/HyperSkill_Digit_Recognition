package recognition;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder stringBuilder=new StringBuilder();
        System.out.println("Input grid:");

        for(int i=0;i<5;i++)
            stringBuilder.append(scanner.nextLine());
        int[] values = new int[stringBuilder.length()];

        for(int i=0;i<values.length;i++)
            values[i]=stringBuilder.charAt(i)=='X'?1:0;

        NeiroDigit5x3 net = new NeiroDigit5x3();
        net.setValues(values);

        System.out.printf("This number is %d",net.getAnswer());

    }
    
}
