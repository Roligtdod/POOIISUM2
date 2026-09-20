package interfaz;

import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;
import model.estadoPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GUI {
    private JButton mostrarButton;
    private JButton iniciarButton;
    private JTextArea area_texto;
    private JTextField id_txt;
    private JTextField direc_txt;
    private JPanel VentanaRegistroPedidos;
    private JPanel VentanaListaPedidos;
    private JComboBox cbox_tipo;
    private JTabbedPane VentanaPrincipal;
    private JPanel panel_iniciar;
    private JButton registrarButton;
    private JTable table1;

    private final ZonaDeCarga zonaDeCarga;
    private ExecutorService ex;
    private DefaultTableModel modeloTabla;


    public GUI(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        ex = Executors.newFixedThreadPool(3);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Estado");
        table1.setModel(modeloTabla);


        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id_txt.getText().isEmpty() || direc_txt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
                    return;
                }
                try{
                    int id = Integer.parseInt(id_txt.getText());
                    String direccion = direc_txt.getText();
                    String tipo = cbox_tipo.getSelectedItem().toString();
                    Pedido pedido = new Pedido(Integer.parseInt(id_txt.getText()), direccion, tipo, estadoPedidos.PENDIENTE);
                    zonaDeCarga.agregarPedido(pedido);
                    JOptionPane.showMessageDialog(null, "Se ha registrado el pedido");
                    id_txt.setText("");
                    direc_txt.setText("");
                    cbox_tipo.setSelectedIndex(0);

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero de ID");
                }

            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Pedido> pedidos = zonaDeCarga.listaPedidos();

                modeloTabla = new DefaultTableModel(
                        new Object[]{"ID", "Dirección", "Tipo", "Estado"},0
                );
                table1.setModel(modeloTabla);

                for (Pedido pedido : pedidos) {
                    Object[] fila ={
                            pedido.getId(),
                            pedido.getDireccion(),
                            pedido.getTipo(),
                            pedido.getEstado()
                    };
                    modeloTabla.addRow(fila);
                }

                if (pedidos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay pedidos en la cola");
                }


            }
        });

        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area_texto.setText("");
                Repartidor r1 = new Repartidor(zonaDeCarga, "Franco", GUI.this);
                Repartidor r2 = new Repartidor(zonaDeCarga, "Nicolas", GUI.this);
                Repartidor r3 = new Repartidor(zonaDeCarga, "Claudia", GUI.this);
                ex.execute(r1);
                ex.execute(r2);
                ex.execute(r3);
                area_texto.append("=====================================\n"+"Se han iniciado los repartidores\n" +
                        "Favor espere mientras se despachan los pedidos" +"\n"
                        + "====================================="+"\n");


            }
        });
    }

    public void mostrarVentana(){
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(VentanaPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mostrarTexto(String texto){
        SwingUtilities.invokeLater((Runnable) () -> area_texto.append(texto + "\n"));
    }

}
