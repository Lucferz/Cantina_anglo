/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.DetalleVentaDAO;
import cantina.modelo.DetalleVenta;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class DetalleVentaControl {
    public List<DetalleVenta> listar(){
        return new DetalleVentaDAO().listar();
    }
    
    public DetalleVenta buscarId(DetalleVenta dv){
        return new DetalleVentaDAO().buscarId(dv);
    }
    public DetalleVenta buscarFkVen(DetalleVenta dv){
        return new DetalleVentaDAO().buscarFkVen(dv);
    }
            
    public DetalleVenta buscarFkArt(DetalleVenta dv){
        return new DetalleVentaDAO().buscarFkArt(dv);
    }
    
    public void insertar(DetalleVenta dv){
        new DetalleVentaDAO().insertar(dv);
    }
    
    public void modificar(DetalleVenta dv){
        new DetalleVentaDAO().modificar(dv);
    }
    
    public void eliminarLogico(DetalleVenta dv){
        new DetalleVentaDAO().eliminarLogico(dv);
    }
    
    public void eliminar(DetalleVenta dv){
        new DetalleVentaDAO().eliminar(dv);
    }
}
