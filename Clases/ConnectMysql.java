/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.sql.*;

/**
 *
 * @author Cesar
 */
public class ConnectMysql {
    private Connection conn; /// variable para almacenar la conexion
    private String driver = "com.mysql.jdbc.Driver"; /// driver para correr mysql
    private String bd = "teatrohanabi"; /// nombre de base de datos
    private String urlMysql = "jdbc:mysql://localhost:3306/"+bd; /// url estructura para que se conecte a mysql
    private String user = "root"; /// usuario de mysql 
    private String password = ""; /// password de mysql
    
    /*
     * Function para conectarse a la base de datos
    */
    private void Connect(){
        this.conn = null; /// lo iguala a null para que sea un borron y cuenta nueva
        try{
            Class.forName(this.driver); /// inicializas los drivers para conectarse a mysql
            this.conn = DriverManager.getConnection(this.urlMysql, this.user, this.password);  /// conexion a mysql
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error "+ e.getMessage());
        }
    }
    
    /*
     * Desconecta la base de datos
    */
    private void Disconnect(){
        this.conn = null; /// igualar a null para cerrar conexion
    }
    
    /*
     * Realiza la consulta a la base de datos
     * String @_query_, la consulta
    */
    public ResultSet QuerySelect(String _query_) throws Exception{
        try{
            this.Connect(); /// conecto la base de datos
            PreparedStatement query = this.conn.prepareStatement(_query_);  /// realizo la consulta
            this.Disconnect();  /// desconecto la base de datos
            return query.executeQuery(); /// retorno el resultado de la consulta
        }catch(Exception e){ 
            this.Disconnect();  /// si sucede algun error lo desconecta 
            System.out.println(e); 
        }
        return null;
    }
    
    /*
     * Realiza la consulta a la base de datos
     * String @_query_, la consulta
    */
    public int QueryUpdate(String _query_) throws Exception{
        try{
            this.Connect(); /// conecto la base de datos
            PreparedStatement query = this.conn.prepareStatement(_query_);  /// realizo la consulta
            this.Disconnect();  /// desconecto la base de datos
            return query.executeUpdate(); /// retorno el resultado de la consulta
        }catch(Exception e){ 
            this.Disconnect();  /// si sucede algun error lo desconecta 
            System.out.println(e); 
        }
        return -1;
    }
}
