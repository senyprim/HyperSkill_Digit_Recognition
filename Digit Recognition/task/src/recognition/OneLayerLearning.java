package recognition;

public class OneLayerLearning {
    private recognition.OneLayerNet net;
    private double rate;


    public OneLayerLearning(OneLayerNet net, double rate){
        this.net=net;
        this.rate=rate;
    }

    public double learnOne(double[] inputs,double[] idealOutputs){
        double sumError=0;
        double[] outputs = net.calculate(inputs);
        for(int neironIndex=0;neironIndex<net.getNeironsCount();neironIndex++){
            double delta= idealOutputs[neironIndex]-outputs[neironIndex];
            if (delta==0) continue;
            Neiron neiron=net.getNeiron(neironIndex);
            for(int weightIndex=0;weightIndex<neiron.getInputsCount();weightIndex++){
                double oldWeight=neiron.getWeight(weightIndex);
                double newWeight=oldWeight+this.rate*inputs[weightIndex]*delta;
                neiron.setWeight(weightIndex,newWeight);
            }
            neiron.setBias(neiron.getBias()+this.rate*delta);
            sumError+=Math.abs(delta);
        }
        return sumError;
    }
}
