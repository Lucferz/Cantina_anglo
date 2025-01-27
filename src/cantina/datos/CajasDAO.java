/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Cajas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class CajasDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Cajas> listar(){
        TypedQuery<Cajas> query = em.createNamedQuery("Cajas.findAll", Cajas.class);
        List<Cajas> res = query.getResultList();
        return res;
    }
    public Boolean buscarEstadoDeCaja (Integer id){
        TypedQuery<Cajas> query = em.createNamedQuery("Cajas.findEstadoOfCaja",Cajas.class);
        query.setParameter("idcaja",id );
        Cajas estado = query.getSingleResult();
        return estado.getEstado();
    }
    
    public void insertar (Cajas a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
    public void modificar (Cajas a){
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }
    
    public void eliminarId (Cajas a){
        em.getTransaction().begin();
        a = em.find(Cajas.class, a.getIdCaja());
        em.remove(a);
        em.getTransaction().commit();
    }
    
    public void eliminarIdLogico(Integer id){
        em.getTransaction().begin();
        Cajas a = em.find(Cajas.class, id);
        a.setEstado(false);
        em.merge(a);
        em.getTransaction().commit();
    }
}
