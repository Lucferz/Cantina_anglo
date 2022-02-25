/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Articulos;
import cantina.modelo.Categorias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class ArticulosDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Articulos> listar(){
        TypedQuery<Articulos> query = em.createNamedQuery("Articulos.findAll", Articulos.class);
        List<Articulos> res = query.getResultList();
        return res;
    }
    public void cargar_tabla_arti(JTable table){
        String [] titulos = {"ID","CODIGO", "DESCRIPCION","STOCK","PRECIO_COSTO","PRECIO_VENTA", "CATEGORIA"};
       DefaultTableModel model = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
           
       };
       try{
           TypedQuery<Articulos> sql =em.createNamedQuery("Articulos.findEstadoTrue",Articulos.class);
           List<Articulos> datos = sql.getResultList();
           String [] datosArti = new String[7];
          for(Articulos tba : datos){
              datosArti[0]=tba.getIdarticulo()+"";
              datosArti[1]=tba.getCodigo()+"";
              datosArti[2]=tba.getDescripcion()+"";
              datosArti[3]=tba.getStock()+"";
              datosArti[4]=tba.getCosto()+"";
              datosArti[5]=tba.getPrecioVenta()+"";
              Categorias cat = tba.getFkCategorias();
              datosArti[6]= cat.getNombre();
              model.addRow(datosArti);
           }
          table.setModel(model);
       }catch(Exception e){
           
       }
   }
        
    public Articulos buscarId (Integer id){
        Articulos a =em.find(Articulos.class, id);
        return a;
    }
    public Articulos buscarCod (String cod){
        TypedQuery<Articulos> sql = em.createNamedQuery("Articulos.findByCodigo", Articulos.class);
        sql.setParameter("codigo", cod);
        Articulos res = sql.getSingleResult();
        return res;
    }
    
    public void insertar (Articulos a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
    public void modificar (Articulos a){
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }
    
    public void eliminarId (Articulos a){
        em.getTransaction().begin();
        a = em.find(Articulos.class, a.getIdarticulo());
        em.remove(a);
        em.getTransaction().commit();
    }
    public void eliminarCod (Articulos a){
        em.getTransaction().begin();
        a = em.find(Articulos.class, a.getCodigo());
        em.remove(a);
        em.getTransaction().commit();
    }
    
    public void eliminarIdLogico(Integer id){
        em.getTransaction().begin();
        Articulos a = em.find(Articulos.class, id);
        a.setEstado(false);
        em.merge(a);
        em.getTransaction().commit();
    }
    
    public void eliminarCodLogico(Articulos a){
        em.getTransaction().begin();
        a = em.find(Articulos.class, a.getCodigo());
        a.setEstado(Boolean.FALSE);
        em.merge(a);
        em.getTransaction().commit();
    }
}