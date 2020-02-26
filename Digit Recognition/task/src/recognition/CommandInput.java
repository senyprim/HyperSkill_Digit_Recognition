package recognition;

public class CommandInput implements ICommand {
    CommandShell shell;
    public CommandInput(CommandShell commandShell) {
        this.shell=commandShell;
    }

    @Override
    public void execute() {
        double[] input=new double[shell.countLine*shell.countByLine];
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Input grid:");
        for(int i=0;i<this.shell.countLine;i++)
            stringBuilder.append(this.shell.scanner.nextLine());
        for(int i=0;i<input.length;i++){
            input[i]=stringBuilder.charAt(i)=='X'?1:0;
        }
        this.shell.net.calculate(input);

        System.out.printf("This number is %d\n",this.shell.net.getMaxOutputIndex());

    }
}
