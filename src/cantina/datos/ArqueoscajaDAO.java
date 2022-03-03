/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Arqueoscaja;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class ArqueoscajaDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Arqueoscaja> listar(){
        TypedQuery<Arqueoscaja> query = em.createNamedQuery("Arqueoscaja.findAll", Arqueoscaja.class);
        List<Arqueoscaja> res = query.getResultList();
        return res;
    }
    
    public Arqueoscaja UltimoElemento (){
        TypedQuery<Arqueoscaja> query = em.createNamedQuery("Arqueoscaja.findbyMaxId", Arqueoscaja.class);
        Arqueoscaja aqcaja = query.getSingleResult();
        return aqcaja;
    }
    
    public void insertar (Arqueoscaja a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
    public void modificar (Arqueoscaja a){
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }
    
    public void eliminarId (Arqueoscaja a){
        em.getTransaction().begin();
        a = em.find(Arqueoscaja.class, a.getIdArqueo());
        em.remove(a);
        em.getTransaction().commit();
    }
    
    public void eliminarIdLogico(Integer id){
        em.getTransaction().begin();
        Arqueoscaja a = em.find(Arqueoscaja.class, id);
        a.setEstado(false);
        em.merge(a);
        em.getTransaction().commit();
    }
}
