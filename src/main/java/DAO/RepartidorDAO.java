package DAO;

import conexion.Conexion;
import model.Repartidor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepartidorDAO {


    public ArrayList listarTodos(){

        ArrayList<Repartidor> repartidores = new ArrayList<>();


        String sql = "SELECT * FROM REPARTIDOR";

        try(
                Connection conex = Conexion.obtenerConexion();
                PreparedStatement ps = conex.prepareStatement(sql);
                ResultSet st = ps.executeQuery();
        ){
            for ( ; st.next();){
                Repartidor r = new Repartidor(st.getString("nombre"), st.getInt("id"));
                repartidores.add(r);
            }
            System.out.println(repartidores);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error con SQL");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el repartidor");
        }


        return repartidores;

    } //RETORNA LISTA DE REPARTIDORES


}
