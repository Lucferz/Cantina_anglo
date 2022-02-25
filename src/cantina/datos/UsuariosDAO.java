/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucas
 */
public class UsuariosDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Usuarios> listar(){
        TypedQuery query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
        List<Usuarios> res = query.getResultList();
        return res;
    }
    
    public Usuarios buscarId (Usuarios u){
        u = em.find(Usuarios.class, u.getIdusuario());
        return u;
    }
    
    public Usuarios buscarNombre (Usuarios u){
        u = em.find(Usuarios.class, u.getNombre());
        return u;
    }
     public Usuarios buscarRol (Usuarios u){
        u = em.find(Usuarios.class, u.getFkRoles());
        return u;
    }
    
    public void insertar(Usuarios u){
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public void modificar (Usuarios u){
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
    }
    
    public void eliminarLogico(Usuarios u){
        em.getTransaction().begin();
        u= em.find(Usuarios.class, u.getIdusuario());
        u.setEstado(false);
        em.merge(u);
        em.getTransaction().begin();
    }
    
    public void eliminar (Usuarios u){
        em.getTransaction().begin();
        u = em.find(Usuarios.class, u.getIdusuario());
        em.remove(u);
        em.getTransaction().commit();
    }
}
