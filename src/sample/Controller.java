package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Controller {
    @FXML //metoda wstrzykiwania
    private Label AddressIPLabel;

    @FXML
    private Label SubnetMaskLabel;

    @FXML
    private TextField AddressIPCodeLabel;

    @FXML
    private TextField SubnetMaskCodeLabel;

    @FXML
    private Button FindButton;

    @FXML
    private Label NetworkAddressLabel;

    @FXML
    private Label BroadcastAddressLabel;

    @FXML
    private Label NetworkAddressCodeLabel;

    @FXML
    private Label BroadcastAddressCodeLabel;

    @FXML
    private Label AddressIPFirstHostLabel;

    @FXML
    private Label AddressIPLastHostLabel;

    @FXML
    private Label AddressIPFirstHostCodeLabel;

    @FXML
    private Label AddressIPLastHostCodeLabel;

    @FXML
    private Label NumberOfHostLabel;

    @FXML
    private Label NumberOfHostCodeLabel;

    private String AddressIPString;
    private String SubnetMaskString;
    private String[] AddressIPStringTable;
    private String[] SubnetMaskStringTable;
    private String addressIPBinarySentence;
    private String subnetMaskBinarySentence;
    private String notSubnetMaskBinarySentence;
    private String networkAddressBinarySentence;
    private String broadcastAddressBinarySentence;

    private BinaryOktet subnetMaskBinaryOktet;
    private BinaryOktet addressIPBinaryOktet;

    private DecimalOktet networkAddressDecimalOktet;
    private DecimalOktet broadcastAddressDecimalOktet;



    public Controller(){
        System.out.println("Dziendobry");
    }

    @FXML // metoda wywoływana zaraz po konstruktorze Controllera
    void initialize(){

    }

    @FXML//dobry zwyczaj - to jest metoda zadeklarowaną w pliku FXML
    public void onActionFindButton(){
       CalculateParameters();
    }


    public void CalculateParameters(){
        System.out.println("To jest metoda onActionFindButton");
        //AddressIPStringTable=AddressIPCodeLabel.getText().split("\\.");
        //SubnetMaskStringTable=SubnetMaskCodeLabel.getText().split("\\."); // UWAGA kropka jest specjalnym znakiem
        //System.out.println(AddressIPStringTable[0]+AddressIPStringTable[1]+AddressIPStringTable[2]+AddressIPStringTable[3]);//+" "+SubnetMaskString);
        try {
            addressIPBinaryOktet= new BinaryOktet(AddressIPCodeLabel.getText());
            addressIPBinarySentence  = addressIPBinaryOktet.getBinarysentence();
            System.out.println("---"+addressIPBinarySentence);

        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            subnetMaskBinaryOktet= new BinaryOktet(SubnetMaskCodeLabel.getText());
            subnetMaskBinarySentence = subnetMaskBinaryOktet.getBinarysentence();
            System.out.println("---"+subnetMaskBinarySentence);
        }catch (Exception e) {
            e.printStackTrace();
        }

        networkAddressBinarySentence=gateAnd(addressIPBinarySentence,subnetMaskBinarySentence);
        //System.out.println(addressIPBinarySentence);
        //System.out.println(subnetMaskBinarySentence);
        //System.out.println(networkAddressBinarySentence);

        notSubnetMaskBinarySentence=gateNot(subnetMaskBinarySentence);
        //System.out.println(subnetMaskBinarySentence);
        //System.out.println(notSubnetMaskBinarySentence);
        broadcastAddressBinarySentence=gateOr(notSubnetMaskBinarySentence,networkAddressBinarySentence);
        //System.out.println(broadcastAddressBinarySentence);

        convertBinaryToDecimal();
        setNewCode();
    }

    public String gateAnd(String addressIPSentence,String subnetMaskSentence){
        String andGate="";
        for(int i=0;i<=31;i++){
            if(addressIPSentence.substring(i,i+1).equals("1") && subnetMaskSentence.substring(i,i+1).equals("1")){
                andGate=andGate+"1";
            }else{
                andGate=andGate+"0";
            }
        }
        return andGate;
    }

    public String gateNot(String sub){
        String notGate="";
        for(int i=0;i<=31;i++){
            if(sub.substring(i,i+1).equals("1")){
                notGate=notGate+"0";
            }else{
                notGate=notGate+"1";
            }
        }
        return notGate;
    }

    public String gateOr(String sub1,String sub2){
        String gateOr="";

        for(int i=0;i<=31;i++){
            if(sub1.substring(i,i+1).equals("1") || sub2.substring(i,i+1).equals("1")){
                gateOr=gateOr+"1";
            }
            else{
                gateOr=gateOr+"0";
            }
        }
        return gateOr;
    }

    public void convertBinaryToDecimal(){
        networkAddressDecimalOktet=new DecimalOktet(networkAddressBinarySentence);
        broadcastAddressDecimalOktet=new DecimalOktet(broadcastAddressBinarySentence);

        networkAddressDecimalOktet.printdecimalOktetString();
        broadcastAddressDecimalOktet.printdecimalOktetString();
    }
    @FXML
    public void setNewCode(){
        NetworkAddressCodeLabel.setText(networkAddressDecimalOktet.getDecimalOktetString());
        BroadcastAddressCodeLabel.setText(broadcastAddressDecimalOktet.getDecimalOktetString());
        AddressIPFirstHostCodeLabel.setText(networkAddressDecimalOktet.increaseLastOktet());
        AddressIPLastHostCodeLabel.setText(broadcastAddressDecimalOktet.decreaseLastOktet());
        int numberOfHost=broadcastAddressDecimalOktet.getLastOktet()-networkAddressDecimalOktet.getLastOktet();
        String stringNumberOfHost=""+(power(2,32-subnetMaskBinaryOktet.numberOfOnes())-2);
        NumberOfHostCodeLabel.setText(stringNumberOfHost);
    }

    private int power(int base,int exponent){
        if(exponent!=0){
            return base*power(base,exponent-1);
        }else return 1;
    }



}
