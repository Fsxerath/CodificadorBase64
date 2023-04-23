package Modelo;

import Vista.Ventanas;

import javax.swing.*;
import java.io.*;

public class Archivo {
    private FileWriter fw;
    private FileReader fr;

    public Archivo(){
        fw = null;
        fr = null;
    }

    public void createFile(String msj){
        try {
            fw = new FileWriter("./mensaje.txt", false);
            fw.write(msj+"\n");
            fw.close();
            fileAlert("Archivo creado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String readFile(){
        String msj = "", line;
        try {
            if(fileExist(new File("./mensaje.txt"))){
                fr = new FileReader("./mensaje.txt");
                BufferedReader br = new BufferedReader(fr);
                msj += br.readLine();
                br.close();
                fr.close();
            }else{
                fileAlert("El archivo no existe");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return msj;
    }

    public void fileAlert(String msj){
        JOptionPane.showMessageDialog(null,msj);
    }

    public boolean fileExist(File f){
        return f.exists() && !f.isDirectory();
    }

    public FileWriter getFw() {
        return fw;
    }

    public void setFw(FileWriter fw) {
        this.fw = fw;
    }

    public FileReader getFr() {
        return fr;
    }

    public void setFr(FileReader fr) {
        this.fr = fr;
    }
}
