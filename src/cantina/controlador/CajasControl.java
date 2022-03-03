/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.CajasDAO;
import cantina.modelo.Cajas;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CajasControl {
    public Boolean buscarEstadoDeCaja (Integer id){
        return new CajasDAO().buscarEstadoDeCaja(id);
    }
    public List<Cajas> listar(){
        return new CajasDAO().listar();
    }
    public void insertar (Cajas a){
        new CajasDAO().insertar(a);
    }
    public void modificar (Cajas a){
        new CajasDAO().modificar(a);
    }
    public void eliminarId (Cajas a){
        new CajasDAO().eliminarId(a);
    }
    public void eliminarLogico(Integer id){
        new CajasDAO().eliminarIdLogico(id);
    }
}
