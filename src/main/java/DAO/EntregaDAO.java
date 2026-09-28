package DAO;

import conexion.Conexion;
import model.Entrega;

import javax.swing.*;
import java.sql.*;

public class EntregaDAO {

    public void crearYGuardarEntrega() {
        String sqlPedido = """
                SELECT id
                FROM pedido
                WHERE estado = 'PENDIENTE'
                ORDER BY id
                LIMIT 1
                """;

        String sqlRepartidor = """
                SELECT id
                FROM repartidor
                ORDER BY id
                LIMIT 1
                """;

        try (
                Connection conex = Conexion.obtenerConexion();
                PreparedStatement psPedido = conex.prepareStatement(sqlPedido);
                PreparedStatement psRepartidor = conex.prepareStatement(sqlRepartidor);
                ResultSet rsPedido = psPedido.executeQuery();
                ResultSet rsRepartidor = psRepartidor.executeQuery()
        ) {
            if (!rsPedido.next()) {
                JOptionPane.showMessageDialog(null, "No hay pedidos pendientes");
                return;
            }

            if (!rsRepartidor.next()) {
                JOptionPane.showMessageDialog(null, "No hay repartidores registrados");
                return;
            }

            int id_pedido = rsPedido.getInt("id");
            int id_repartidor = rsRepartidor.getInt("id");

            Date fechaActual = new Date(System.currentTimeMillis());
            Time horaActual = new Time(System.currentTimeMillis());

            Entrega entrega = new Entrega(0, id_pedido, id_repartidor, fechaActual, horaActual);

            guardar(entrega);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al crear la entrega: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la entrega: " + e.getMessage());
        }
    }

    public void guardar(Entrega entrega) {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (
                Connection conex = Conexion.obtenerConexion();
                PreparedStatement ps = conex.prepareStatement(sql)
        ) {
            ps.setInt(1, entrega.getId_pedido());
            ps.setInt(2, entrega.getId_repartidor());
            ps.setDate(3, entrega.getFecha());
            ps.setTime(4, entrega.getHora());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Entrega registrada correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error SQL al registrar la entrega: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la entrega: " + e.getMessage());
        }
    }

}
