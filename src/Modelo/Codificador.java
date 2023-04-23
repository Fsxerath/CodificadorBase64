package Modelo;

import javax.swing.*;
import java.util.Arrays;

public class Codificador {
    private String txtAscii;
    private String[] binaries;
    private String BASE64;
    private JtableData jt;

    public Codificador(String txtAscii, String[] binaries, String BASE64) {
        this.txtAscii = txtAscii;
        this.binaries = binaries;
        this.BASE64 = BASE64;
    }
    public Codificador() {
        this.txtAscii = "";
        this.binaries = null;
        this.BASE64 = BASE64;
        this.jt = null;
    }


    //Esto es para que mas o menos se guie a la hora de conectar esto con el frame
    public String base64(String x){
        String mensaje = "Transmisor (Trx)\n1. De ASCII a su equivalencia decimal:\n";

        int [] eq_ascii = EquivalenciaDecimal(x, x.toCharArray()); //Se convierte a decimal

        for(int i = 0; i < eq_ascii.length; i++){
            mensaje += String.valueOf(eq_ascii[i])+" ";
        }
        mensaje += "\n2. De Decimal a binario 8 bits\n";

        String [] binary = EquivalenciaBinaria(eq_ascii); // se convierte a binario

        for(int i = 0; i < binary.length; i++){
            mensaje += binary[i] + " ";
        }
        mensaje += "\n3. Hacer separación de a 6 bits\n";

        String [] Base64 = SepararEn6(Texto(binary)).split(","); //separa en 6 bits

        for(int i = 0; i < binary.length; i++){
            mensaje += Base64[i] + " ";
        }

        mensaje += "\n4. Poner 0's faltantes\n";
        String aux = Iguales(Base64[Base64.length-1]);

        Base64[Base64.length-1] = CompletarCeros(Base64[Base64.length-1]); //se completan los ceros.

        for(int i = 0; i < binary.length; i++){
            mensaje += Base64[i] + " ";
        }
        mensaje += "\n5. Poner su equivalencia binaria en Base64\n";

        String base64 = toBase64(toDecimal(Base64),aux);
        BASE64 = base64;

        mensaje += base64;

        return mensaje;
    }
    public int [] EquivalenciaDecimal(String x, char[] caracteres){
        int [] ascii = new int [x.length()]; //crea un array de enteros con el tamaño del mensaje
        for (int i = 0; i < x.length(); i++) {
            ascii[i] = caracteres[i]; //equivalencia entera ascii de cada caracter
        }
        System.out.println(Arrays.toString(ascii));
        return ascii; //retorna el array de enteros
    }
    public String[] EquivalenciaBinaria(int [] ascii){
        String [] binary = new String [ascii.length]; //crea arreglo de string igual de grande al numero de enteros
        for (int i = 0; i < ascii.length; i++) {
            binary[i] = Integer.toBinaryString(ascii[i]); //convierte de entero a binario en un string
        }
        System.out.println("Equivalencia ascii: " +Arrays.toString(binary));
        System.out.println("Binario: "+Texto(binary));
        return binary;
    }
    public String SepararEn6(String x) { //Separa cada 6 bits
        int cont=0;
        String aux="";
        for (int i=0; i < x.length(); i++) {
            cont++;
            aux+=x.charAt(i);
            if (cont == 6) {
                aux +=",";
                cont = 0;
            }
        }
        return aux;
    }
    public String Texto(String [] x) { // completa los 8 bits
        String aux = "";
        for (int i=0; i < x.length; i++) {
            if(x[i].length() < 8) {
                x[i] = "0"+x[i];
            }
            aux+=x[i];
        }
        return aux;
    }

    public String CompletarCeros(String x) { //Completa 0 para tener la cadena de 6 bits faltantes
        int ceros = 6-x.length(); //determina la cantidad de 0 faltantes
        for (int i=0; i < ceros; i++) {
            x+="0";
        }
        return x;

    }
    public String Iguales(String x) { //determina la cantidad de = a poner al final de la cadena
        int ceros = 6-x.length();//Determina el numero de 0 faltantes para los 6 bits
        String iguales="";
        for(int i = 0; i <  ceros/2; i++) {
            iguales+="=";
        }
        return iguales;
    }
    public int[] toDecimal(String [] x) {
        int [] valor = new int[x.length];
        for(int i = 0; i < x.length; i++) {
            valor[i] = Integer.parseInt(x[i],2); //Convierte a entero pero en base, el 2 indica que esta en binario
        }
        return valor;
    }
    public String toBase64(int [] x, String iguales) {
        String valores="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", aux="";
        for(int i=0; i < x.length; i++) {
            aux+=valores.charAt(x[i]);
        }
        return aux+iguales;
    }

    public String getTxtAscii() {
        return txtAscii;
    }

    public void setTxtAscii(String txtAscii) {
        this.txtAscii = txtAscii;
    }

    public String[] getBinaries() {
        return binaries;
    }

    public void setBinaries(String[] binaries) {
        this.binaries = binaries;
    }

    public JtableData getJt() {
        return jt;
    }

    public void setJt(JtableData jt) {
        this.jt = jt;
    }

    public String getBASE64() {
        return BASE64;
    }

    public void setBASE64(String BASE64) {
        this.BASE64 = BASE64;
    }
}
