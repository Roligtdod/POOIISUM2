package model;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ZonaDeCarga {
    private final LinkedBlockingQueue<Pedido> colaPedidos;
    private final ReentrantLock lock;

    public ZonaDeCarga(){
        colaPedidos = new LinkedBlockingQueue<>(); //Cola de prioridad, al tener todos los objetos la misma prioridad (0) usa sistema FIFO (Fist in, first out), cambie de priority a linked por lo mismo c:
        lock = new ReentrantLock();
    }




    public synchronized void agregarPedido(Pedido p){
        lock.lock();
        try{
            colaPedidos.add(p);
            System.out.println("Se agrego el pedido " + p.getId() + " a la cola"); //Agrega pedido a colaPedido, para poder ser trabajado posterioemnte
        }
        finally{
            lock.unlock();
        }
    }

    public synchronized Pedido retirarPedido(){
        lock.lock();
        try{
            return colaPedidos.poll(); //toma el primer valor en la lista y posteriormente lo elimina, al tener un lock no permite que otro hilo retire el mismo pedido al mismo tiempo generando conflicto

        }finally{
            lock.unlock();
        }
    }


    public static synchronized void mostrarPedidos(String titulo, String texto){ //Investigue porque al momento de ejecutar todo al mismo tiempo salia texto de forama algo desordenada, con esto logre dejarlo ordenado
        System.out.println();
        System.out.println("===========================================");
        System.out.println("          "+titulo);
        System.out.println("===========================================");
        System.out.println(texto);
        System.out.println("===========================================");

    }

}
