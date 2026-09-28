package model;

import java.sql.Date;
import java.sql.Time;

public class Entrega {
    private int id_entrega;
    private int id_pedido;
    private int id_repartidor;
    private Date fecha;
    private Time hora;


    public Entrega(int id_repartidor, int id_pedido, Date fecha, Time hora){
        this.id_pedido = id_pedido;
        this.id_repartidor = id_repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Entrega(int id_entrega, int id_pedido, int id_repartidor, Date fecha, Time hora){
        this.id_entrega = id_entrega;
        this.id_pedido = id_pedido;
        this.id_repartidor = id_repartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId_entrega() {
        return id_entrega;
    }

    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId_repartidor() {
        return id_repartidor;
    }

    public void setId_repartidor(int id_repartidor) {
        this.id_repartidor = id_repartidor;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}
