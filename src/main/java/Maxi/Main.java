package Maxi;

import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;
import model.estadoPedidos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        System.out.println("\n============================================\n");
        Pedido p = new Pedido(1, "Calle 1", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p);
        Pedido p2 = new Pedido(2, "Calle 2", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p2);
        Pedido p3 = new Pedido(3, "Calle 3", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p3);
        Pedido p4 = new Pedido(4, "Calle 4", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p4);
        Pedido p5 = new Pedido(5, "Calle 5", estadoPedidos.PENDIENTE);
        zonaDeCarga.agregarPedido(p5);

        System.out.println("\n============================================\n");

        Repartidor r1 = new Repartidor(zonaDeCarga, "Nico");
        Repartidor r2 = new Repartidor(zonaDeCarga, "Claudia");
        Repartidor r3 = new Repartidor(zonaDeCarga, "Franco");

        ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.execute(r1);
        ex.execute(r2);
        ex.execute(r3);

        ex.shutdown();




    }
}
