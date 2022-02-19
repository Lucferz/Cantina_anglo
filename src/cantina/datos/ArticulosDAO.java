/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Articulos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
    
    public Articulos buscarId (Articulos a){
        a =em.find(Articulos.class, a.getIdarticulo());
        return a;
    }
    public Articulos buscarCod (Articulos a){
        a =em.find(Articulos.class, a.getCodigo());
        return a;
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
    
    public void eliminarIdLogico(Articulos a){
        em.getTransaction().begin();
        a = em.find(Articulos.class, a.getIdarticulo());
        a.setEstado(Boolean.FALSE);
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