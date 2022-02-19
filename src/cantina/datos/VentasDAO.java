/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Ventas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class VentasDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Ventas> listar(){
        TypedQuery query = em.createNamedQuery("Ventas.findAll", Ventas.class);
        List<Ventas> res = query.getResultList();
        return res;
    }
    
    public Ventas buscar(Ventas v){
        v = em.find(Ventas.class, v.getIdventa());
        return v;
    }
    
    public void insertar(Ventas v){
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
    }
    
    public void modificar(Ventas v){
        em.getTransaction().begin();
        em.merge(v);
        em.getTransaction().commit();
    }
    
    public void eliminarLogico(Ventas v){
        em.getTransaction().begin();
        v= em.find(Ventas.class, v.getIdventa());
        v.setEstado(false);
        em.merge(v);
        em.getTransaction().commit();
    }
    
    public void eliminar(Ventas v){
        em.getTransaction().begin();
        v= em.find(Ventas.class, v.getIdventa());
        em.remove(v);
        em.getTransaction().commit();
    }
}
