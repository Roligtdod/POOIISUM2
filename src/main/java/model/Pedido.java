package model;

public class Pedido implements Comparable<Pedido> {
    private int id;
    private String direccion;
    private estadoPedidos estado;

    public Pedido(int id, String direccion, estadoPedidos estado) {
        this.id = id;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }


    public estadoPedidos getEstado() {
        return estado;
    }

    public void setEstado(estadoPedidos estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }


    @Override
    public String toString() {
        return
                "\nNumero del pedido #" + id +
                "\nDireccion de entrega: " + direccion +
                "\nEstado del pedido: " + estado+"\n************************************\n";
    }


    @Override
    public int compareTo(Pedido o) {
        int comparacionPorEstado = Integer.compare(this.estado.ordinal(), o.estado.ordinal());

        if (comparacionPorEstado != 0) {
            return comparacionPorEstado;
        }

        return Integer.compare(this.id, o.id);
    }
}
