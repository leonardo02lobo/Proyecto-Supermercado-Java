package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productos {

    File productos;
    String texto, aux = "";
    int cont = 0; 
    public static String arreglo[];
    

    public void ProductosCargar() {
        productos = new File("Datos.txt");

        try {
            FileReader leer = new FileReader(productos);
            BufferedReader lectura = new BufferedReader(leer);

            texto = lectura.readLine();

            while (texto != null) {
                aux += texto;
                texto = lectura.readLine();
                cont++;
            }
            separar(aux);
        } catch (IOException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void separar(String aux) {
        String[] separar = null;
        for (int i = 0; i < cont; i++) {
            separar = aux.split("-");
        }
        separar2(separar);
    }

    private void separar2(String[] aux) {
        String[] aux2;
        
        for (int i = 0; i < aux.length; i++) {
            aux2 = aux[i].split(",");
            
            String codigo = aux2[0];
            String comida = aux2[1];
            String precio = aux2[2];
           
            arreglo = new String[]{codigo,comida,precio};    
        }
        
        
    }
}
