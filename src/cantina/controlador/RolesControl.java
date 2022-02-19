/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.RolesDAO;
import cantina.modelo.Roles;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class RolesControl {
    public List<Roles> listar(){
        return new RolesDAO().listar();
    }
    
    public Roles buscar(Roles r){
        return new RolesDAO().buscar(r);
    }
    
    public void insertar(Roles r){
        new RolesDAO().insertar(r);
    }
    
    public void modificar(Roles r){
       new RolesDAO().modificar(r);
    }
    
    public void eliminarLogico(Roles r){
        new RolesDAO().eliminarLogico(r);
    }
    
    public void eliminar(Roles r){
        new RolesDAO().eliminar(r);
    }
}
