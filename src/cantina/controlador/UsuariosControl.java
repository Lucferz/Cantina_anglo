/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.UsuariosDAO;
import cantina.modelo.Usuarios;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class UsuariosControl {
    
    public List<Usuarios> listar(){
        return new UsuariosDAO().listar();
    }
    
    public Usuarios buscarId (Usuarios u){
        return new UsuariosDAO().buscarId(u);
    }
    
    public Usuarios buscarPorNombre (String nom){
        return new UsuariosDAO().buscarPorNombre(nom);
    }
    
    public Usuarios buscarIdINT (Integer id){
        return new UsuariosDAO().buscarIdINT(id);
    }
    
    public void insertar(Usuarios u){
        new UsuariosDAO().insertar(u);
    }
    
    public void modificar (Usuarios u){
        new UsuariosDAO().modificar(u);
    }
    
    public void eliminarLogico(Usuarios u){
        new UsuariosDAO().eliminarLogico(u);
    }
    
    public void eliminar (Usuarios u){
        new UsuariosDAO().eliminar(u);
    }
    
}
