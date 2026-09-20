package model;


import interfaz.GUI;

public class Repartidor implements Runnable{
    private  ZonaDeCarga zonaDeCarga;
    private  String nombre;
    private GUI gui;


    public Repartidor(ZonaDeCarga zonaDeCarga, String nombre, GUI gui){
        this.zonaDeCarga = zonaDeCarga;
        this.nombre = nombre;
        this.gui = gui;
    }




    @Override
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
        gui.mostrarTexto(" No quedan más despachos disponibles para asignar a repartidor " + nombre + "\n");
    }

    public String cadenaTexto(String nombre, String contenido){
        return nombre;

    }

}
