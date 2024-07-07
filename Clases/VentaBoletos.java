/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class VentaBoletos extends ConnectMysql {
    private int id_boleto;
    private String fecha_registro;
    private String id_obra;
    private String posicion_asiento;
    private boolean vip;
    private float total = 100;
    private String nombre_cliente;
    private boolean estatus_boleto;
    private String telefono;
    private int edad_cliente;
    
    public VentaBoletos(){
        
    }    
    
    public VentaBoletos(int id_boleto, String fecha_registro,
            String id_obra, String posicion_asiento, boolean vip, float total, String nombre_cliente, 
            boolean estatus_boleto, String telefono, int edad_cliente){
        this.id_boleto = id_boleto;
        this.fecha_registro = fecha_registro;
        this.id_obra = id_obra;
        this.posicion_asiento = posicion_asiento;
        this.vip = vip;
        this.total = total;
        this.nombre_cliente = nombre_cliente;
        this.estatus_boleto = estatus_boleto;
        this.telefono = telefono;
        this.edad_cliente = edad_cliente;
    }
    
    public VentaBoletos(String id_obra, String posicion_asiento, boolean vip
                            , String nombre_cliente, String telefono, int edad_cliente){
        this.id_obra = id_obra;
        this.posicion_asiento = posicion_asiento;
        this.vip = vip;
        this.nombre_cliente = nombre_cliente;
        this.telefono = telefono;
        this.edad_cliente = edad_cliente;
        this.total = vip? this.total + 50: this.total;
    }
    
    public int saveData() throws Exception {
        return this.QueryUpdate("INSERT INTO venta_boletos "
                + "VALUES(null,'"+this.id_obra+"',NOW(),'"+this.posicion_asiento+"',"
                        + ""+(this.vip? "1": "0")+",'"+this.total+"',1,'"+this.nombre_cliente+"','"+this.telefono+"','"+this.edad_cliente+"')");
    }
    
    public ArrayList<VentaBoletos> ListBoletos() throws SQLException, Exception{
        ArrayList<VentaBoletos> _return_ = new ArrayList();
        ResultSet result = this.QuerySelect("SELECT *, DATE(fecha_registro) as fecha_boletos FROM venta_boletos order by id_obra ");
        while(result.next()){
            _return_.add(new VentaBoletos(
                Integer.parseInt(result.getString("id_boleto")),
                result.getString("fecha_boletos"),
                result.getString("id_obra"),
                result.getString("posicion_asiento"),
                result.getString("vip").equals("1"),
                Float.parseFloat(result.getString("total")),
                result.getString("nombre_cliente"),
                result.getString("estatus_boleto").equals("1"),
                result.getString("telefono"),
                Integer.parseInt(result.getString("edad_cliente"))
            ));
        }
        return _return_;
    } 
    
    public boolean validAsiento(String asiento, String id_obra) throws Exception {
        int aux_cont = 0;
        ResultSet result = this.QuerySelect("SELECT * FROM venta_boletos WHERE "
                + " posicion_asiento = '"+asiento+"' AND id_obra = '"+id_obra+"'");
        if(result == null)
            return true;
        
        while(result.next())
            aux_cont++;
        return aux_cont >= 1;
    } 
    
    public int getid_boleto(){
        return this.id_boleto;
    }
    public String getfecha_registro(){
        return this.fecha_registro;
    }
    public String getid_obra(){
        return this.id_obra;
    }
    public String getposicion_asiento(){
        return this.posicion_asiento;
    }
    public boolean getvip(){
        return this.vip;
    }
    public float gettotal(){
        return this.total;
    }
    public String getnombre_cliente(){
        return this.nombre_cliente;
    }
    public boolean getestatus_boleto(){
        return this.estatus_boleto;
    }
    public String gettelefono(){
        return this.telefono;
    }
    public int getedad_cliente(){
        return this.edad_cliente;
    }

}

