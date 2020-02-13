package recognition;

public class NeiroDigit5x3 {
    public final int dimension = 2;
    private final int[] lengths = {15,10};
    private final double[] bias = {-1,6,1,0,2,0,-1,3,-2,-1};
    private final double[][] weight={
            {+1, +1, +1, +1, -1, +1, +1, -1, +1, +1, -1, +1, +1, +1, +1}, // 0
            {-1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1}, // 1
            {+1, +1, +1, -1, -1, +1, +1, +1, +1, +1, -1, -1, +1, +1, +1}, // 2
            {+1, +1, +1, -1, -1, +1, +1, +1, +1, -1, -1, +1, +1, +1, +1}, // 3
            {+1, -1, +1, +1, -1, +1, +1, +1, +1, -1, -1, +1, -1, -1, +1}, // 4
            {+1, +1, +1, +1, -1, -1, +1, +1, +1, -1, -1, +1, +1, +1, +1}, // 5
            {+1, +1, +1, +1, -1, -1, +1, +1, +1, +1, -1, +1, +1, +1, +1}, // 6
            {+1, +1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1, -1, -1, +1}, // 7
            {+1, +1, +1, +1, -1, +1, +1, +1, +1, +1, -1, +1, +1, +1, +1}, // 8
            {+1, +1, +1, +1, -1, +1, +1, +1, +1, -1, -1, +1, +1, +1, +1}, // 9
    };

    private  int[] values;

    public void setValues(int[] values) {
        this.values = values;
    }

    public double getWeightByInOutIndex(int inIndex, int outIndex)
    {
        return weight[outIndex][inIndex];
    }

    public double getBias(int index){
        return bias[index];
    }

    public double getValue(int value){
        return (double) value>=1?1:0;
    }

    public double getValueByInIndex(int index){
        return getValue(values[index]);
    }

    public NeiroDigit5x3(){
        this.values=new int[15];
    }

    public double getOutValue(int index){
        double value=getBias(index);
        for (int i=0;i<lengths[0];i++){
            value+=getValueByInIndex(i)*getWeightByInOutIndex(i,index);
        }
        return value;
    }

    public double[] getOutValues(){
        double[] result = new double[lengths[1]];
        for(int i=0;i<result.length;i++){
            result[i]=getOutValue(i);
        }
        return result;
    }

    public int getAnswer(){
        double max=Double.MIN_VALUE;
        int maxIndex=0;
        for(int i=0;i<lengths[1];i++){
            double value=getOutValue(i);
            if (i==0||value>max){
                max=value;
                maxIndex=i;
            }
        }
        return maxIndex;
    }

}
