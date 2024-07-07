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
public class ObraTeatro extends ConnectMysql{
    public int ID = -1;
    public String Nombre = null;
    public String Genero;
    public String Duracion;
    public int Num_Lugares;
    public String Fecha_Hora;
        
    public ObraTeatro(){
        
    }
    
    public ObraTeatro(String Nombre){
        this.Nombre = Nombre;
    }
    
    public ObraTeatro(int ID){
        this.ID = ID;
    }
    
    public ObraTeatro(int ID, String Nombre, String Genero,
            String Duracion, int Num_Lugares, String Fecha_Hora
    ){
        this.ID = ID;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Duracion = Duracion;
        this.Num_Lugares = Num_Lugares;
        this.Fecha_Hora = Fecha_Hora;
    }
    
    public ObraTeatro(String Nombre, String Genero,
            String Duracion, int Num_Lugares, String Fecha_Hora
    ){
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Duracion = Duracion;
        this.Num_Lugares = Num_Lugares;
        this.Fecha_Hora = Fecha_Hora;
    }
    
    public void SearchObras() throws SQLException, Exception{
        ResultSet result = this.QuerySelect("SELECT * FROM obras_teatro WHERE "+(this.ID == -1? "Nombre = '"+this.Nombre+"'" : "ID = "+this.ID));
        while(result.next()){
            this.ID = Integer.parseInt(result.getString("ID"));
            this.Nombre = result.getString("Nombre");
            this.Genero = result.getString("Genero");
            this.Duracion = result.getString("Duracion");
            this.Num_Lugares = Integer.parseInt(result.getString("Num_Lugares"));
            this.Fecha_Hora = result.getString("Fecha_Hora");
        }
    } 
    
    public ArrayList<ObraTeatro> ListObraTeatro() throws SQLException, Exception{
        ArrayList<ObraTeatro> _return_ = new ArrayList();
        ResultSet result = this.QuerySelect("SELECT * FROM obras_teatro ");
        while(result.next()){
            _return_.add(new ObraTeatro(
                    Integer.parseInt(result.getString("ID")),
                    result.getString("Nombre"),
                    result.getString("Genero"),
                    result.getString("Duracion"),
                    Integer.parseInt(result.getString("Num_Lugares")),
                    result.getString("Fecha_Hora")
            ));
        }
        return _return_;
    } 
    
    public int saveData() throws Exception {
        return this.QueryUpdate("INSERT INTO obras_teatro "
                + "VALUES(null,'"+this.Nombre+"','"+this.Genero+"','"+this.Duracion+"',"
                        +this.Num_Lugares+",'"+this.Duracion+"')");
    } 
    
    public int updateData() throws Exception {
        return this.QueryUpdate("UPDATE obras_teatro SET "
                + " Nombre = '"+this.Nombre+"', Genero = '"+this.Genero+"', Duracion = '"+this.Duracion+"',"
                + " Num_Lugares = "+this.Num_Lugares+", Fecha_Hora = '"+ this.Duracion +"' WHERE "
                + "ID = "+ this.ID);
    } 
    
    public int deleteData() throws Exception {
        return this.QueryUpdate("DELETE FROM obras_teatro WHERE "
                + "ID = "+ this.ID);
    }
    
    public int get_ID(){
        return this.ID;
    }
    public String get_Nombre(){
        return this.Nombre;
    }
    public String get_Genero(){
        return this.Genero;
    }
    public String get_Duracion(){
        return this.Duracion;
    }
    public int get_Num_Lugares(){
        return this.Num_Lugares;
    }
    public String get_Fecha_Hora(){
        return this.Fecha_Hora;
    }
}
