package recognition;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

public class Neiron implements Serializable {
    private static final long serialVersionUID = -4724290109703797012L;
    public static double sigma(double val){return 1.0/(1.0+Math.exp(-val));}

    private int inputsCount;
    public int getInputsCount(){
        return inputsCount;
    }

    private double bias;
    public double getBias(){return bias;}
    public void setBias(double value){this.bias=value;}

    private double[] weights;
    public double getWeight(int index){
        return weights[index];
    }
    public void setWeight(int index, double value){this.weights[index]=value;}

    private double result;
    public double getResult(){
        return result;
    }

    public double calculate(double[] inputs){
        if (inputs.length!=this.inputsCount){
            throw new IllegalArgumentException("Wrong inputs count");
        }
        double sum=getBias();
        for(int i=0;i<weights.length;i++){
            sum+=getWeight(i)*inputs[i];
        }
        this.result=sigma(sum);
        return this.result;
    }

    public Neiron(int inputsCount){
        this.inputsCount=inputsCount;
        this.weights=new double[this.inputsCount];
        Random rand = new Random();
        this.bias=rand.nextDouble()*2-1;
        for(int i=0;i<this.inputsCount;i++)
            this.weights[i]=rand.nextDouble()*2-1;
    }

    public Neiron(double[] weights,double bias){
        this.inputsCount=weights.length;
        this.weights=weights;
        this.bias=bias;
    }

    public Neiron(String neiron){
        String[] data = neiron.split(";");
        this.bias=Double.parseDouble(data[0]);
        this.inputsCount=data.length-1;
        this.weights=new double[data.length-1];
        for(int i=1;i<data.length;i++){
            weights[i-1]=Double.parseDouble(data[i]);
        }
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBias());
        stringBuilder.append(";");
        for(int i=0;i<getInputsCount();i++){
            stringBuilder.append(getWeight(i));
            if (i<getInputsCount()-1)
                stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }
}