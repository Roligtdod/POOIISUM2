package app;


import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;
import model.estadoPedidos;
import interfaz.GUI;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        System.out.println("\n============================================\n");
        Pedido p = new Pedido(1, "Calle 1","Encomienda", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p);
        Pedido p2 = new Pedido(2, "Calle 2","Express", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p2);
        Pedido p3 = new Pedido(3, "Calle 3","Comida", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p3);
        Pedido p4 = new Pedido(4, "Calle 4","Comida", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p4);
        Pedido p5 = new Pedido(5, "Calle 5","Express", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p5);

        System.out.println("\n============================================\n");

        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI(zonaDeCarga);

            gui.mostrarVentana();
        });

    }
}
