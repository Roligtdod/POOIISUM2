package model;


public class Repartidor implements Runnable {
    private final ZonaDeCarga zonaDeCarga;
    private final String nombre;

    public Repartidor(ZonaDeCarga zonaDeCarga, String nombre){
        this.zonaDeCarga = zonaDeCarga;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Pedido pedido;
        while((pedido = zonaDeCarga.retirarPedido()) != null){ //Asigna valor a "pedido" y compara que tenga algo disponible, en caso de que no envia mensaje de despacho finalizado de abajo
            // como retirarPedido elimina con poll el valor siempre seguira el que viene en la lista
            pedido.setEstado(estadoPedidos.EN_REPARTO);
            zonaDeCarga.mostrarPedidos("Retiro del pedido",
                    "Repartidor " + nombre + "\n" +
                            "Numero del pedido #" + pedido.getId() + "\n" +
                            "Direccion de entrega " + pedido.getDireccion() + "\n"
                            +"Estado del pedido " + pedido.getEstado()); //este metodo está explicado en ZonaDeCarga
            try {
                Thread.sleep(5000); // puede cambiarlo si le parece mucho
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pedido.setEstado(estadoPedidos.ENTREGADO);
            System.out.println(nombre + " finalizo la entrega de :" + pedido); // profe, aca podria haber usado zonaDeCarga.MostrarPedidos() como antes pero preferi usar el toString porque esta en la pauta
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
        System.out.println("\n---------------------------------------------");
        System.out.println(nombre + " Finaliza de despachar debido a que no quedan pedidos pendientes");
        System.out.println("\n---------------------------------------------\n");
    }


}
