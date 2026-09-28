package interfaz;

import DAO.EntregaDAO;
import DAO.RepartidorDAO;
import conexion.Conexion;
import model.Entrega;
import model.ZonaDeCarga;
import model.estadoPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GUI {
    private JButton mostrarButton;
    private JButton iniciarButton;
    private JTextArea area_texto;
    private JTextField direc_txt;
    private JPanel VentanaRegistroPedidos;
    private JPanel VentanaListaPedidos;
    private JComboBox cbox_tipo;
    private JTabbedPane VentanaPrincipal;
    private JPanel panel_iniciar;
    private JButton registrarButton;
    private JTable table1;
    private JPanel PanelRP;
    private JScrollPane StarP;
    private JButton repButton;
    private JTextField txtNR;
    private JButton buttonLista;
    private JButton buttonEntrega;

    private final ZonaDeCarga zonaDeCarga;
    private ExecutorService ex;
    private DefaultTableModel modeloTabla;


    public GUI() {

        zonaDeCarga = new ZonaDeCarga();

        ex = Executors.newFixedThreadPool(3);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Estado");
        table1.setModel(modeloTabla);

        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarPedido();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarBBDD();
            }
        }); //ok

        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area_texto.setText("");
                //Repartidor r1 = new Repartidor(zonaDeCarga, "Franco", GUI.this);
                //Repartidor r2 = new Repartidor(zonaDeCarga, "Nicolas", GUI.this);
                //Repartidor r3 = new Repartidor(zonaDeCarga, "Claudia", GUI.this);
                //ex.execute(r1);
                //ex.execute(r2);
                //ex.execute(r3);
                area_texto.append("=====================================\n"+"Se han iniciado los repartidores\n" +
                        "Favor espere mientras se despachan los pedidos" +"\n"
                        + "====================================="+"\n");


            }
        });

        repButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarRepartidor();
            }
        }); //OK

        buttonLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RepartidorDAO r = new RepartidorDAO();
                r.listarTodos();
            }
        });

        buttonEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EntregaDAO entregaDAO = new EntregaDAO();
                entregaDAO.crearYGuardarEntrega();
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

    public void mostrarBBDD(){
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Dirección", "Tipo", "Estado"},0
                );
        table1.setModel(modeloTabla);

        String sql = "SELECT id, direccion, tipo, estado FROM pedido";

        try(Connection conex = Conexion.obtenerConexion();
            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet st = ps.executeQuery();){
            boolean hayDatos = false;
            while(st.next()){
                hayDatos = true;
                Object[] fila ={
                        st.getInt("id"),
                        st.getString("direccion"),
                        st.getString("tipo"),
                        st.getString("estado")
                };
                modeloTabla.addRow(fila);
            }
            if(!hayDatos){
                JOptionPane.showMessageDialog(null, "No hay datos en la base de datos");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos");
        }

    }

    public void registrarRepartidor(){
        String nombre = txtNR.getText();
        System.out.println(nombre);
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            return;
        }

        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";

        try(
                Connection conex = Conexion.obtenerConexion();
                PreparedStatement ps = conex.prepareStatement(sql);
        ){
            ps.setString(1, nombre);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se ha registrado el repartidor");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el repartidor");
        }
        txtNR.setText("");

    }

    public void registrarPedido(){

        String direccion = direc_txt.getText();
        String tipo = cbox_tipo.getSelectedItem().toString();
        String estado = estadoPedidos.PENDIENTE.toString();

        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        if(direccion.isEmpty() || tipo.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
            return;
        }

        try(
                Connection conex = Conexion.obtenerConexion();
                PreparedStatement ps = conex.prepareStatement(sql);

        ){
            ps.setString(1, direccion);
            ps.setString(2, tipo);
            ps.setString(3, estado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha registrado el pedido");

        }catch (SQLException e) {
            JOptionPane .showMessageDialog(null, "Datos invalidos");
        }catch (Exception e) {
            JOptionPane .showMessageDialog(null, "Error al registrar el pedido");
        }

    }

}
