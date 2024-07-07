/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Cesar
 */
public class Usuarios extends ConnectMysql{
    public int id_usuarios;
    public String usuario;
    public String password;
    public String nombre;
    public String puesto;
    public Boolean estatus;
    
    public Usuarios(){
    }
    
    public Usuarios(String usuario){
        this.usuario = usuario;
    }
    
    public Usuarios(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }
    
    public Usuarios(int id_usuarios, String usuario, String password, 
            String nombre, String puesto, Boolean estatus){
        this.id_usuarios = id_usuarios;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.puesto = puesto;
        this.estatus = estatus;
    }
    
    public Usuarios(String usuario, String password, 
            String nombre, String puesto){
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.puesto = puesto;
    }
    
    public int saveData() throws Exception {
        return this.QueryUpdate("INSERT INTO usuarios "
                + "VALUES(null,'"+this.usuario+"','"+this.password+"','"+this.nombre+"',"
                        + "'"+this.puesto+"',1)");
    } 
    
    public boolean Login() throws Exception {
        int aux_cont = 0;
        ResultSet result = this.QuerySelect("SELECT * FROM usuarios WHERE "
                + "lower(usuario) = lower('"+this.usuario+"') AND lower(password)  = lower('"+this.password+"')");
        while(result.next()){
            this.id_usuarios = Integer.parseInt(result.getString("id_usuarios"));
            this.usuario = result.getString("usuario");
            this.password = result.getString("password");
            this.nombre = result.getString("nombre");
            this.puesto = result.getString("puesto");
            this.estatus = Integer.parseInt(result.getString("estatus")) == 1;
            aux_cont++;
        }
        return aux_cont >= 1;
    } 
    
    public ArrayList<Usuarios> ListUsuarios() throws SQLException, Exception{
        ArrayList<Usuarios> _return_ = new ArrayList();
        ResultSet result = this.QuerySelect("SELECT * FROM usuarios ");
        while(result.next()){
            _return_.add(new Usuarios(
                    Integer.parseInt(result.getString("id_usuarios")),
                    result.getString("usuario"),
                    result.getString("password"),
                    result.getString("nombre"),
                    result.getString("puesto"),
                    Integer.parseInt(result.getString("estatus")) == 1
            ));
        }
        return _return_;
    } 
    
    public int disabledUsuario() throws Exception {
        return this.QueryUpdate("UPDATE usuarios SET estatus = 0 WHERE "
                + " usuario = '"+this.usuario+"'");
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setUsuarios(String usuario){
        this.usuario = usuario;
    }    
    
    public int get_id_usuarios(){
        return this.id_usuarios;
    }
    public String get_usuario(){
        return this.usuario;
    }
    public String get_password(){
        return this.password;
    }
    public String get_nombre(){
        return this.nombre;
    }
    public String get_puesto(){
        return this.puesto;
    }
    public Boolean get_estatus(){
        return this.estatus;
    }
    
}
