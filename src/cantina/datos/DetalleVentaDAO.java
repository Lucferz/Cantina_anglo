/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.DetalleVenta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class DetalleVentaDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<DetalleVenta> listar(){
        TypedQuery<DetalleVenta> query = em.createNamedQuery("DetalleVenta.findAll",DetalleVenta.class);
        List<DetalleVenta> res = query.getResultList();
        return res;
    }
    
    public DetalleVenta buscarId(DetalleVenta dv){
        dv = em.find(DetalleVenta.class, dv.getIddetalleVenta());
        return dv;
    }
    /*Estos buscar con el foreign key hay que probar, pq resulta que
    para java, cuando hago el foreign key y hace el modelado y mapeo
    trae el objeto, o sea la tabla relacionada, no el id que hace 
    referencia jajaj que loco, en fin, hay que ver como sale, pq mi idea
    aca es justamente que se pueda buscar por el id de venta y articulo
    en la tabla DetalleVenta, si esto no funciona, probar creando un
    NamedQuery en la clase DetalleVenta en el paquete modelo*/
    public DetalleVenta buscarFkVen(DetalleVenta dv){
        dv = em.find(DetalleVenta.class, dv.getFkVenta().getIdventa());
        return dv;
    }
            
    public DetalleVenta buscarFkArt(DetalleVenta dv){
        dv = em.find(DetalleVenta.class, dv.getFkArticulos().getIdarticulo());
        return dv;
    }
    
    public void insertar(DetalleVenta dv){
        em.getTransaction().begin();
        em.persist(dv);
        em.getTransaction().commit();
    }
    
    public void modificar(DetalleVenta dv){
        em.getTransaction().begin();
        em.merge(dv);
        em.getTransaction().commit();
    }
    public void eliminarLogico(DetalleVenta dv){
        em.getTransaction().begin();
        dv = em.find(DetalleVenta.class, dv.getIddetalleVenta());
        dv.setEstado(false);
        em.merge(dv);
        em.getTransaction().commit();  
        }
    public void eliminar(DetalleVenta dv){
        em.getTransaction().begin();
        dv = em.find(DetalleVenta.class, dv.getIddetalleVenta());
        em.remove(dv);
    }
    
}
