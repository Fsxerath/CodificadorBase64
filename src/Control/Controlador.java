package Control;

import Modelo.Archivo;
import Modelo.Codificador;
import Modelo.Decodificador;
import Vista.Principal;
import Vista.Ventanas;

import javax.swing.*;

public class Controlador {
    private Principal frmPrincipal;
    private Codificador objC;
    private Decodificador objD;
    private Ventanas v;

    public Controlador() {
        v = new Ventanas();
    }

    public void iniciar(){
        Archivo file = new Archivo();
        int opc = v.pedirEntero("Escoja una opción:\n1. Codificar\n2.Decodificar\n3. Salir");

        switch (opc){
            case 1:
                Codificador objC = new Codificador();
                objC.setTxtAscii(v.pedirString("Escriba el mensaje a codificar"));
                v.mostrar(objC.base64(objC.getTxtAscii()));
                file.createFile(objC.getBASE64());
                iniciar();
            case 2:
                Decodificador objD = new Decodificador();
                objD.setBase64(file.readFile());
                v.mostrar(objD.Ascii());
                iniciar();
            case 3:
                System.exit(0);
        }


    }
    public void iniciar2(){
        Decodificador objD = new Decodificador();
        objD.setBase64("aCBx");
        objD.Ascii();
    }
}
