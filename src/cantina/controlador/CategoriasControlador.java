/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.CategoriasDAO;
import cantina.modelo.Categorias;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class CategoriasControlador {
    public List<Categorias> listar(){
        return new CategoriasDAO().listar();
    }
    public Categorias buscar (Categorias c){
        return new CategoriasDAO().buscar(c);
    }
    public void insertar (Categorias c){
        new CategoriasDAO().insertar(c);
    }
    
    public void modificar (Categorias c){
        new CategoriasDAO().modificar(c);
    }
    
    public void eliminar (Categorias c){
        new CategoriasDAO().eliminar(c); 
    }
}
