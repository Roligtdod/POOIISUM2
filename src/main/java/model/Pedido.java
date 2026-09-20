package model;

public class Pedido implements Comparable<Pedido> {
    private int id;
    private String direccion;
    private String tipo;
    private estadoPedidos estado;

    public Pedido(int id, String direccion,String tipo, estadoPedidos estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido (){

    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo=tipo;
    }

    public int getId() {
        return id;
    }
    public void SetId(int id){
        this.id = id;
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
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
