package sample;

import java.util.ArrayList;

/**
 * Created by Matball on 2017-07-04.
 */
public class BinaryOktet {
    public  ArrayList<ArrayList<Boolean>> value = new ArrayList<>();
    int octetTable[]=new int[4];
    String binarysentence="";

    public BinaryOktet(String Oktets){
        String []octet;
        octet=Oktets.split("\\.");
        for(int i=0;i<4;i++){
            octetTable[i]=Integer.parseInt(octet[i]);
        }


        for(int i=0;i<4;i++) {
            value.add(new ArrayList<>());
            int sum=0;
            for(int j=7;j>=0;j--){
                if(sum+power(2,j)<=octetTable[i]) {
                    value.get(i).add(true);
                    sum=sum+power(2,j);
                }else {
                    value.get(i).add(false);
                }
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<=7;j++) {
                if(value.get(i).get(j)==true)
                    binarysentence = binarysentence + "1";
                else
                    binarysentence = binarysentence + "0";
            }
        }
    }

    private int power(int base,int exponent){

        if(exponent!=0){
            return base*power(base,exponent-1);
        }else return 1;
    }

    public ArrayList<ArrayList<Boolean>> getValue() {
        return value;
    }

    public String getBinarysentence() {
        return binarysentence;
    }

    public int numberOfOnes(){
        //System.out.println(binarysentence);
        int howMuch=0;
        for(int i=0;i<32;i++){
            //System.out.println(howMuch);
            if(binarysentence.substring(i,i+1).equals("1")){
                howMuch++;
            }
            else{;}
        }
        System.out.println(howMuch);
        return howMuch;
    }
}
