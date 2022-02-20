/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Categorias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class CategoriasDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Categorias> listar(){
        TypedQuery<Categorias> query = em.createNamedQuery("Categorias.findAll",Categorias.class);
        List<Categorias> res = query.getResultList();
        return res;
    }
    
    public Categorias buscar (Categorias c){
        c= em.find(Categorias.class, c.getIdcategoria());
        return c;
    }
    public void insertar (Categorias c){
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    public void modificar (Categorias c){
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }
    
    public void eliminar (Categorias c){
        em.getTransaction().begin();
        c = em.find(Categorias.class, c.getIdcategoria());
        em.remove(c);
        em.getTransaction().commit();
    }
}
