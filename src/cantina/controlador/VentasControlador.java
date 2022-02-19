/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.VentasDAO;
import cantina.modelo.Ventas;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class VentasControlador {
    public List<Ventas> listar(){
        return new VentasDAO().listar();
    }
    public Ventas buscar(Ventas v){
        return new VentasDAO().buscar(v);
    }
    public void insertar(Ventas v){
        new VentasDAO().insertar(v);
    }
    public void modificar(Ventas v){
        new VentasDAO().modificar(v);
    }
    public void eliminarLogico(Ventas v){
        new VentasDAO().eliminarLogico(v);
    }
    public void eliminar(Ventas v){
        new VentasDAO().eliminar(v);
    }
}
