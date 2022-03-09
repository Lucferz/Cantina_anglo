/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.controlador;

import cantina.datos.ArqueoscajaDAO;
import cantina.modelo.Arqueoscaja;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Lucas
 */
public class ArqueoscajaControl {
    public List<Arqueoscaja> listar(){
        return new ArqueoscajaDAO().listar();
    }
    
    public void cargarTabArqueo(JTable table) {
        new ArqueoscajaDAO().cargarTabArqueo(table);
    }
    
    public Arqueoscaja UltimoElemento (){
        return new ArqueoscajaDAO().UltimoElemento();
    }
    
    public void insertar (Arqueoscaja a){
        new ArqueoscajaDAO().insertar(a);
    }
    
    public void modificar (Arqueoscaja a){
        new ArqueoscajaDAO().modificar(a);
    }
    
    public void eliminarId (Arqueoscaja a){
        new ArqueoscajaDAO().eliminarId(a);
    }
    
    public void eliminarIdLogico(Integer id){
        new ArqueoscajaDAO().eliminarIdLogico(id);
    }
}
