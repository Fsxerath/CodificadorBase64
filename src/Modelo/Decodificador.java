package Modelo;

import java.util.Arrays;

public class Decodificador {
    private String base64;
    private String[] binaries;
    private String ascii;

    public Decodificador(){
        base64 = "";
        binaries = null;
        ascii = "";
    }

    public String Ascii(){
        String mensaje = "Receptor (Rx)\n1. De Base64 a Decimal\n";
        char[] chars = getBase64().replace("=","").toCharArray(); //Cadena base64 a un array de char, quitando los iguales
        int [] equivB64 = equivalenciaBase64(chars); //

        for(int i = 0; i < equivB64.length; i++){
            mensaje += equivB64[i] + " ";
        }

        mensaje += "\n2. De Decimal a Binario de 6 bits\n";

        String [] bin6 = toBin6(equivB64);

        for(int i = 0; i < bin6.length; i++){
            mensaje += bin6[i] + " ";
        }
        mensaje += "\n3. Juntar los bits y separarlos en 8 bits\n";

        String [] bin8 = toBin8(bin6);

        for(int i = 0; i < bin8.length; i++){
            mensaje += bin8[i] + " ";
        }
        mensaje += "\n4. Completar 0's faltantes\n";

        bin8[bin8.length-1] = CompletarCeros(bin8[bin8.length-1]);

        for(int i = 0; i < bin8.length; i++){
            mensaje += bin8[i] + " ";
        }
        mensaje += "\n5. Mostrar mensaje decodificado en ASCII\n";

        ascii = equivalenciaAscii(bin8);
        System.out.println(ascii);
        mensaje += ascii;

        return mensaje;
    }

    public int[] equivalenciaBase64(char[] chars){
        String valores="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        int [] num = new int [chars.length];
        for(int i = 0; i < num.length; i++){
            num[i] = valores.indexOf(chars[i]); //Se almacena el numero del caracter en base64
        }

        return num;
    }
    //bx=
    //100100 111100
    //10010011 11000000
    public String[] toBin6(int [] equivB64){
        String [] bin6 = new String [equivB64.length];
        for(int i = 0; i < equivB64.length; i++){

            bin6[i] = Integer.toBinaryString(equivB64[i]); //almacena los binarios de 6 bits

            String initial = "";
            int ceros = 6-bin6[i].length(); //determina ceros faltantes
            for(int j = 0; j < ceros; j++){
                initial += "0"; //acumula los 0
            }
            bin6[i] = initial+bin6[i]; //los pone al lado izquierdo
        }

        return bin6;
    }
    public String[] toBin8(String [] bin6){
        String acumBin8 = "";
        for(int i = 0; i < bin6.length; i++){
            acumBin8 += bin6[i];
        }
        String bin8 = separateToBin8(acumBin8);
        return bin8.split(",");
    }

    public String separateToBin8(String acumBin8){
        int cont = 0;
        String aux = "";
        for(int i = 0; i < acumBin8.length(); i++){
            cont++;
            aux += acumBin8.charAt(i);
            if(cont == 8){
                aux += ",";
                cont = 0;
            }
        }
        return aux;
    }

    public String CompletarCeros(String x){
        int ceros = 8-x.length();
        for(int i = 1; i < ceros; i++){
            x += "0";
        }
        return x;
    }

    public String equivalenciaAscii(String [] bin8){
        String acum = "";
        char [] chars = new char[bin8.length];
        for(int i = 0; i < bin8.length; i++){
            chars[i] = (char) Integer.parseInt(bin8[i],2);
            acum += chars[i];
        }


        return acum;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String[] getBinaries() {
        return binaries;
    }

    public void setBinaries(String[] binaries) {
        this.binaries = binaries;
    }

    public String getAscii() {
        return ascii;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }
}
