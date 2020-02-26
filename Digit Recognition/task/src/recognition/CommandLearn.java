package recognition;

public class CommandLearn implements ICommand {
    CommandShell shell;
    public CommandLearn(CommandShell shell){
        this.shell=shell;
        Neiron[] neirons = new Neiron[10];
        for (int i = 0; i < neirons.length; i++)
            neirons[i] = new Neiron(new double[15], 0);
        shell.net= new OneLayerNet(neirons);
    }
    public void execute(){
        double[][] inputs =
                {
                new double[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}, // 0
                new double[]{0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, // 1
                new double[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, // 2
                new double[]{1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 3
                new double[]{1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1}, // 4
                new double[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 5
                new double[]{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // 6
                new double[]{1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // 7
                new double[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // 8
                new double[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 9
                };

        double[][] idealOutputs =
                {
                new double[]{1,0,0,0,0,0,0,0,0,0}, // 0
                new double[]{0,1,0,0,0,0,0,0,0,0}, // 1
                new double[]{0,0,1,0,0,0,0,0,0,0}, // 2
                new double[]{0,0,0,1,0,0,0,0,0,0}, // 3
                new double[]{0,0,0,0,1,0,0,0,0,0}, // 4
                new double[]{0,0,0,0,0,1,0,0,0,0}, // 5
                new double[]{0,0,0,0,0,0,1,0,0,0}, // 6
                new double[]{0,0,0,0,0,0,0,1,0,0}, // 7
                new double[]{0,0,0,0,0,0,0,0,1,0}, // 8
                new double[]{0,0,0,0,0,0,0,0,0,1}, // 9
                };
        OneLayerLearning learn = new OneLayerLearning(this.shell.net,0.5);
        for(int n=0;n<100;n++) {
            for (int example = 0; example < inputs.length; example++) {
                    learn.learnOne(inputs[example], idealOutputs[example]);
            }
        }
        shell.net.save("net.txt");
        shell.isLearned=true;
    }
}

