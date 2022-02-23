/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.ArticulosDAO;
import cantina.modelo.Articulos;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Lucas
 */
public class ArticulosControl {
    public List<Articulos> listar(){
        return new ArticulosDAO().listar();
    }
    public void cargar_tabla_arti(JTable table){
        new ArticulosDAO().cargar_tabla_arti(table);
    }
    public Articulos buscarId (Articulos a){
        return new ArticulosDAO().buscarId(a);
    }
    public Articulos buscarCod (Articulos a){
        return new ArticulosDAO().buscarCod(a);
    }
    
    public void insertar (Articulos a){
        new ArticulosDAO().insertar(a);
    }
    
    public void modificar (Articulos a){
        new ArticulosDAO().modificar(a);
    }
    
    public void eliminarId (Articulos a){
        new ArticulosDAO().eliminarId(a);
    }
    public void eliminarCod (Articulos a){
        new ArticulosDAO().eliminarCod(a);
    }
    public void eliminarIdLogico(Integer id){
        new ArticulosDAO().eliminarIdLogico(id);
    }
    
    public void eliminarCodLogico(Articulos a){
        new ArticulosDAO().eliminarCodLogico(a);
    }
}
