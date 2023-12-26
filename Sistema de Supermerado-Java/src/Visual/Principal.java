package Visual;

import Datos.productos;
import Menu.Nuevo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {

    JPanel panelPrincipal;
    double Precio = 0.0;
    productos p = new productos();
    JLabel precio;
    JLabel Nombre, Apellido, Direccion, Cedula;
    String[] columna = {"Codigo", " Producto", "Precio"};
    DefaultTableModel modelo = new DefaultTableModel(columna, 0);
    
    public Principal() {
        setTitle("Sistema De Supermercado");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        run();
    }

    private void run() {
        Paneles();
        Etiquetas();
        Menu();
        Tabla();
        Precio();
        IVA();
        Busqueda();
        Cantidades();
    }

    public void Paneles() {
        panelPrincipal = new JPanel();

        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.gray);
        panelPrincipal.setOpaque(true);
        this.getContentPane().add(panelPrincipal);
    }

    private void Etiquetas() {
        JLabel Titulo = new JLabel("Consulta de Precios y Venta y Stock");
        Titulo.setFont(new Font("calibri", 1, 30));
        Titulo.setForeground(Color.black);
        Titulo.setBounds(350, 30, 500, 30);
        panelPrincipal.add(Titulo);
        
        JButton pagar = new JButton("Pagar");
        
        pagar.setBounds(1000,200,100,30);
        panelPrincipal.add(pagar);
        pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "Quieres pagar la cantidad de: "
                                + Precio+" $", "", JOptionPane.YES_OPTION);
                
                if(opcion== 0){
                    JOptionPane.showMessageDialog(null, "Compra Exitosa");
                    System.exit(0);
                }
            }
        });
    }

    private void Menu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        JMenu opciones = new JMenu("Opciones");
        menu.add(opciones);

        JMenuItem Nuevo = new JMenuItem("Nuevo      F1");
        opciones.add(Nuevo);

        JMenuItem Cargar = new JMenuItem("Cargar     F2");
        opciones.add(Cargar);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    Nuevo nuevo = new Nuevo();
                    nuevo.setVisible(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    System.out.println("cargar");
                }
            }
        });
    }

    private void Tabla() {

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(0, 250, 1200, 350);
        panelPrincipal.add(scroll);

        for (int i = 0; i < productos.Productos.length; i++) {
            String[] aux = productos.Productos[i].split(",");
            modelo.addRow(aux);
        }
        tabla.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                Precio += Double.parseDouble((String) tabla.getValueAt(e.getClickCount(), 2));
                precio.removeAll();
                precio.setText(Double.toString(Precio) + " $");
                precio.revalidate();
                precio.repaint();
            }
        });
    }

    private void Precio() {
        precio = new JLabel(Double.toString(Precio) + " $", 0);
        precio.setFont(new Font("calibri", 1, 30));
        precio.setBackground(Color.blue);
        precio.setOpaque(true);
        precio.setForeground(Color.white);
        precio.setBounds(1000, 600, 200, 40);
        panelPrincipal.add(precio);
    }

    private void IVA() {

        JRadioButton iva = new JRadioButton("Con IVA");
        iva.setBackground(Color.gray);
        iva.setOpaque(true);
        iva.setFont(new Font("Calibri", 1, 20));
        iva.setBounds(900, 150, 100, 20);
        panelPrincipal.add(iva);

        iva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iva.isSelected()) {
                    Iva IVA = new Iva();

                    Precio += IVA.IVA(1);
                    precio.removeAll();
                    precio.setText(Double.toString(Precio) + " $");
                    precio.revalidate();
                    precio.repaint();
                }
            }
        });
        JRadioButton siniva = new JRadioButton("Sin IVA");
        siniva.setBackground(Color.gray);
        siniva.setOpaque(true);
        siniva.setFont(new Font("Calibri", 1, 20));
        siniva.setBounds(1000, 150, 100, 20);
        panelPrincipal.add(siniva);
        siniva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (siniva.isSelected()) {
                    Iva iva = new Iva();
                    Precio += iva.IVA(0);
                    precio.removeAll();
                    precio.setText(Double.toString(Precio) + " $");
                    precio.revalidate();
                    precio.repaint();
                }
            }
        });

        ButtonGroup grupo1 = new ButtonGroup();
        grupo1.add(iva);
        grupo1.add(siniva);

    }

    private void Busqueda() {
        JTextField buscar = new JTextField();

        buscar.setBounds(900, 90, 150, 20);
        panelPrincipal.add(buscar);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(1070, 80, 100, 30);
        panelPrincipal.add(botonBuscar);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = buscar.getText();
                for (int i = 0; i < productos.Productos.length; i++) {
                    String[] aux = productos.Productos[i].split(",");
                    String producto = aux[1];
                    String pre = aux[2];
                    if (texto.equals(producto)) {
                        buscar.setText("");
                        int opcion = JOptionPane.showConfirmDialog(null, "Producto Disponible, "
                                + "¿Quieres Agregarlo", "", JOptionPane.YES_OPTION);

                        if (opcion == 0) {
                            Precio += Double.parseDouble(pre);
                            precio.removeAll();
                            precio.setText(Double.toString(Precio) + " $");
                            precio.revalidate();
                            precio.repaint();
                        }
                    }
                }
            }
        });
    }

    private void Cantidades() {
        JLabel nombre = new JLabel("Cantidad: ");

        nombre.setBounds(950, 50, 100, 20);
        nombre.setFont(new Font("Calibri", 1, 20));
        panelPrincipal.add(nombre);

        JSpinner cantidad = new JSpinner();

        cantidad.setBounds(1070, 50, 50, 20);
        panelPrincipal.add(cantidad);
    }
}
