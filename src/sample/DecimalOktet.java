package sample;

/**
 * Created by Matball on 2017-07-05.
 */
public class DecimalOktet {
    public int []value=new int[4];
    public String []binaryOktets=new String[4];
    public String decimalOktetString="";
    public String binaryOktetString_;

    public DecimalOktet(String binaryOktet){
        binaryOktetString_=binaryOktet;
        System.out.println(binaryOktet);
        for(int j=0;j<4;j++) {
            //System.out.println("-------");
            binaryOktets[j]=binaryOktet.substring(j*8,(j+1)*8);
            //System.out.println(binaryOktets[j]);
            binaryOktets[j] = new StringBuffer(binaryOktets[j]).reverse().toString();
            //System.out.println(binaryOktets[j]+"---");
        }




        for(int i=0;i<4;i++){
            int sum=0;
            //System.out.println(binaryOktets[i]);
            //System.out.println("--------");
            for(int j=0;j<=7;j++){
                //System.out.println("********");
                //System.out.println(power(2,j));
                //System.out.println("@@@@@@@@");
                //System.out.println(Integer.parseInt(binaryOktets[i].substring(j,j+1)));
                //System.out.println(power(2,j)*Integer.parseInt(binaryOktets[i].substring(j,j+1)));
                sum=sum+power(2,j)*Integer.parseInt(binaryOktets[i].substring(j,j+1));
            }
            value[i]=sum;
            //System.out.println(value[i]);
        }

        decimalOktetString="";
        for(int i=0;i<4;i++){
            decimalOktetString=decimalOktetString+value[i];
            if(i!=3){
                decimalOktetString=decimalOktetString+".";
            }
        }
    }

    private int power(int base,int exponent){
        if(exponent!=0){
            return base*power(base,exponent-1);
        }else return 1;
    }

    public String getDecimalOktetString() {
        return decimalOktetString;
    }

    public void printdecimalOktetString(){
        System.out.println(decimalOktetString);
    }

    public String increaseLastOktet(){
        String string="";
        for(int i=0;i<4;i++){
            if(i!=3) {
                string = string + value[i];
                string = string + ".";
            }
            else{
                string =string +(value[i]+1);
            }
        }
        return string;
    }

    public String decreaseLastOktet(){
        String string="";
        for(int i=0;i<4;i++){
            if(i!=3) {
                string = string + value[i];
                string = string + ".";
            }
            else{
                string =string +(value[i]-1);
            }
        }
        return string;
    }

    public int getLastOktet(){
        System.out.println(value[3]);
        return value[3];
    }


}
