package recognition;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class OneLayerNet implements Serializable {
    private static final long serialVersionUID = -4724290109703797012L;
    private int inputsCount;
    public int getInputsCount() {
        return inputsCount;
    }
    private int neironsCount;
    public int getNeironsCount() {
        return neironsCount;
    }

    private double[] inputs;

    private double[]outputs;
    public  double getOutput(int outputIndex){return outputs[outputIndex];}

    private Neiron[] neirons;
    public  Neiron getNeiron(int neironIndex){return neirons[neironIndex];}

    public OneLayerNet(int inputsCount, int neironsCount){
        this.inputsCount=inputsCount;
        this.neironsCount=neironsCount;
        this.neirons=new Neiron[neironsCount];
        for(int neironIndex=0;neironIndex<neironsCount;neironIndex++){
            neirons[neironIndex]=new Neiron(inputsCount);
        }
    }
    public OneLayerNet(Neiron[] layer){
        this.inputsCount=layer[0].getInputsCount();
        for(Neiron neiron:layer) {
            if (neiron.getInputsCount() != this.inputsCount)
                throw new IllegalArgumentException("Wrong inputs count");
        }
        this.neirons=layer;
        this.neironsCount=layer.length;
    }

    public OneLayerNet(String layer){
        String[] data = layer.split("\n");
        this.neironsCount=data.length;
        this.neirons=new Neiron[data.length];
        for(int i=0;i<data.length;i++){
            neirons[i]=new Neiron(data[i]);
        }
        this.inputsCount=this.neirons[0].getInputsCount();
    }

    public double[] calculate(double[] inputs){
        double[] outputs=new double[neironsCount];
        for(int neironIndex=0;neironIndex<neironsCount;neironIndex++){
            outputs[neironIndex]=neirons[neironIndex].calculate(inputs);
        }
        this.outputs=outputs;
        return this.outputs;
    }

    public static OneLayerNet load(String filename) {
        OneLayerNet net;
        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(filename))) {
            net = (OneLayerNet) read.readObject();
            return net;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void save(String filename){
        try(ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(filename))){
            write.writeObject(this);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveToString(String filename){
        try(FileWriter writer=new FileWriter(filename,StandardCharsets.UTF_8)){
            writer.write(this.toString());
        }
        catch (Exception e){
            System.out.printf("Error save to string: %s",e.getMessage());
        }
    }

    public static OneLayerNet loadFromString(String filename)
    {
        try
        {
            return new OneLayerNet(Files.readString(Paths.get("filename")));
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public int getMaxOutputIndex(){
        int maxIndex=0;
        for(int i=0;i<outputs.length;i++) {
            if (i == 0 || outputs[maxIndex] < outputs[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<getNeironsCount();i++){
            stringBuilder.append(getNeiron(i).toString());
            if (i!=getInputsCount()-1)
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}