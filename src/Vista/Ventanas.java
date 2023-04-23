package Vista;

import javax.swing.*;

public class Ventanas {
    private String msj;

    public Ventanas(){
        msj = "";
    }

    public int pedirEntero(String m){
        return Integer.parseInt(JOptionPane.showInputDialog(m));
    }
    public void mostrar(String m){
        JOptionPane.showMessageDialog(null,m);
    }

    public String pedirString(String m){
        return String.valueOf(JOptionPane.showInputDialog(m));
    }
}
