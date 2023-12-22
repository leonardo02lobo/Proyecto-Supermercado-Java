package Menu;

import Archivos.Datos;
import Visual.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Nuevo extends JFrame {
    public static boolean band = false;

    public Nuevo() {
        setTitle("Nuevo Usuario");
        setSize(500, 500);
        setLocationRelativeTo(null);
        run();
    }

    private void run() {
        panel();
        Etiquetas();
        Cajas();
        Botones();
    }

    private void panel() {
        ConstNuevo.panel = new JPanel();
        ConstNuevo.panel.setLayout(null);
        this.getContentPane().add(ConstNuevo.panel);
    }

    private void Etiquetas() {

        JLabel nombre = new JLabel("Nombres ");
        nombre.setBounds(30, 50, 150, 20);
        ConstNuevo.panel.add(nombre);

        JLabel apellidos = new JLabel("Apellidos ");
        apellidos.setBounds(30, 100, 150, 20);
        ConstNuevo.panel.add(apellidos);

        JLabel cedula = new JLabel("Cedula ");
        cedula.setBounds(30, 150, 150, 20);
        ConstNuevo.panel.add(cedula);

        JLabel direccion = new JLabel("Direccion ");
        direccion.setBounds(30, 200, 150, 20);
        ConstNuevo.panel.add(direccion);
    }

    private void Cajas() {

        ConstNuevo.nombre = new JTextField();
        ConstNuevo.nombre.setBounds(120, 50, 150, 20);
        ConstNuevo.panel.add(ConstNuevo.nombre);

        ConstNuevo.apellido = new JTextField();
        ConstNuevo.apellido.setBounds(120, 100, 150, 20);
        ConstNuevo.panel.add(ConstNuevo.apellido);

        ConstNuevo.cedula = new JTextField();
        ConstNuevo.cedula.setBounds(120, 150, 150, 20);
        ConstNuevo.panel.add(ConstNuevo.cedula);

        ConstNuevo.direccion = new JTextField();
        ConstNuevo.direccion.setBounds(120, 200, 150, 20);
        ConstNuevo.panel.add(ConstNuevo.direccion);
    }

    private void Botones() {

        JButton guardar = new JButton("Guardar");
        guardar.setBounds(80, 250, 150, 30);
        ConstNuevo.panel.add(guardar);

        JButton limpiar = new JButton("Limpiar");
        limpiar.setBounds(270, 250, 150, 30);
        ConstNuevo.panel.add(limpiar);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConstNuevo.nombre.getText().equals("") && ConstNuevo.apellido.getText().equals("")
                        && ConstNuevo.cedula.getText().equals("") && ConstNuevo.direccion.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Faltan datos");
                } else {
                    try {
                        Datos datos = new Datos();
                        datos.setDatos(ConstNuevo.nombre.getText(), ConstNuevo.apellido.getText(), ConstNuevo.cedula.getText(), ConstNuevo.direccion.getText());
                        datos.crear();
                        datos.cargar();
                        datos.leer();
                        dispose();
                        limpiarDatos();
                        band = true;
                    } catch (IOException ex) {
                        Logger.getLogger(Nuevo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarDatos();
            }
        });
    }

    private void limpiarDatos() {
        ConstNuevo.nombre.setText("");
        ConstNuevo.apellido.setText("");
        ConstNuevo.direccion.setText("");
        ConstNuevo.cedula.setText("");
    }
}
