package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/speedfast?createDatabaseIfNotExist=true";
    private static String user = "root";
    private static String password = "";
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static Connection obtenerConexion() throws Exception{
        return DriverManager.getConnection(url, user, password);

    }

    public static void inicializarTabla(){

        String sqlRepartidor = """
                CREATE TABLE IF NOT EXISTS repartidor (
                    id INT AUTO_INCREMENT PRIMARY KEY ,
                    nombre VARCHAR(100) NOT NULL)
                """;

        String sqlPedido = """
                CREATE TABLE IF NOT EXISTS pedido (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    direccion VARCHAR(150) NOT NULL,
                    tipo VARCHAR(30) NOT NULL,      -- COMIDA | ENCOMIENDA | EXPRESS
                    estado VARCHAR(20) NOT NULL)     -- PENDIENTE | EN_REPARTO | ENTREGADO)
                """;

        String sqlEntrega = """
                CREATE TABLE IF NOT EXISTS entrega (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_pedido INT NOT NULL,
                    id_repartidor INT NOT NULL,
                    fecha DATE NOT NULL,
                    hora TIME NOT NULL,
                    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
                    FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)
               )
               """;

        try{
            DriverManager.getConnection(url, user, password).createStatement().executeUpdate(sqlRepartidor);
            DriverManager.getConnection(url, user, password).createStatement().executeUpdate(sqlPedido);
            DriverManager.getConnection(url, user, password).createStatement().executeUpdate(sqlEntrega);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    /**                List<Pedido> pedidos = zonaDeCarga.listaPedidos();

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
*/


    /**                if(id_txt.getText().isEmpty() || direc_txt.getText().isEmpty()){ // Registrar pedido
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
     }*/


    /**    @Override
    public void run() {
    Pedido pedido;
    while((pedido = zonaDeCarga.retirarPedido()) != null){ //Asigna valor a "pedido" y compara que tenga algo disponible, en caso de que no envia mensaje de despacho finalizado de abajo
    // como retirarPedido elimina con poll el valor siempre seguira el que viene en la lista
    pedido.setEstado(estadoPedidos.EN_REPARTO);
    gui.mostrarTexto(nombre + " está repartiendo el pedido #" + pedido.getId() +"\n");

    try {
    Thread.sleep(5000); // puede cambiarlo si le parece mucho
    } catch (InterruptedException e) {
    throw new RuntimeException(e);
    }
    pedido.setEstado(estadoPedidos.ENTREGADO);
    gui.mostrarTexto(nombre + " Termino la entrega del pedido #" + pedido.getId() +"\n");
    try { // Este ultimo Try es para que sea mas facil de leer en consola los resultados,
    // si lo eliminamos funciona todo igual, pero asi no pasa todo rapido PD: <- no se porque al escribir "todo" se pone de otro color, asumo que una palabra reservada para comentarios ajja
    Thread.sleep(500);
    } catch (InterruptedException e) {
    throw new RuntimeException(e);
    }
    }

    try{
    Thread.sleep(10); //lo mismo pequeño delay para que no se crucen los textos
    }catch (InterruptedException e){
    throw new RuntimeException(e);
    }
    gui.mostrarTexto(" No quedan más despachos disponibles para asignar a repartidor " + nombre + "\n"); */ //run de repartidor que saque por lo pedido en el trabajo


     }
