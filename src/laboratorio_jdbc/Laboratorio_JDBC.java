package laboratorio_jdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yessid Murcia
 */
public class Laboratorio_JDBC {

    public static void main(String[] args) {
        conection();
    }

    public static void conection() {
        try {

            //Comprobacion de Driver
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver Cargado!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Driver no Cargado!");
            }

            // Establecemos la conexión con la base de datos.
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/prueba", "root", "");
            System.out.println("Conexion a la base de datos Exitosa!");

            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from persona");

            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            String todos = "";

            System.out.println("\nDatos:");
            while (rs.next()) {
                //impresión por pantalla
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3));
                //acumulador del JOP
                todos += (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDate(3) + "\n");
            }
            //imprimimos todos los resultados en un JOP
            JOptionPane.showMessageDialog(null, "Datos: \n" + todos);

            // Cerramos la conexion a la base de datos.
            conexion.close();
            System.out.println("Conexion cerrada!");

        } catch (SQLException ex) {
            
            Logger.getLogger(Laboratorio_JDBC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fallo con la base de datos...!");
        }
    }
}
