package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datos {

    String nombre, apellido, cedula, direccion;
    File datos;
    String texto,aux = "";

    public void setDatos(String nombre, String apellido, String cedula, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public void crear() {
        datos = new File("Clientes.txt");
    }

    public void cargar() {
        try {
            FileWriter escribir = new FileWriter(datos);
            escribir.write(nombre + "," + apellido + "," + cedula + "," + direccion);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leer() throws IOException{
        try {
            FileReader leer = new FileReader(datos);
            BufferedReader lectura = new BufferedReader(leer);
                texto = lectura.readLine();
            
            
            while(texto != null){
                aux += texto;
                texto = lectura.readLine();
            }
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
