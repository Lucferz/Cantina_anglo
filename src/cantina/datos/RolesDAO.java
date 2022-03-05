/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.modelo.Roles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Lucas
 */
public class RolesDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    
    public List<Roles> listar(){
        TypedQuery query = em.createNamedQuery("Roles.findAll", Roles.class);
        List<Roles> res = query.getResultList();
        return res;
    }
    
    public Roles buscar(Roles r){
        r = em.find(Roles.class, r.getIdrole());
        return r;
    }
    public DefaultComboBoxModel Obt_Rol(){
        DefaultComboBoxModel Lista = new DefaultComboBoxModel();
        Lista.addElement("Seleccione una categoria");
        TypedQuery<Roles> query = em.createNamedQuery("Roles.findAllEstadoTrue", Roles.class);
        List<Roles> res = query.getResultList();
        for (int i=0; i< res.size();i++){
            Lista.addElement(res.get(i));
        }
        return Lista;
    }
    
    public String buscarRolPorId(Integer id){
        TypedQuery<String> sql = em.createNamedQuery("Roles.findNombreById", String.class);
        sql.setParameter("idrol", id);
        String rol = sql.getSingleResult();
        return rol;
    }
    
    public Roles buscarPorid(Integer id){
        TypedQuery<Roles> sql = em.createNamedQuery("Roles.findByIdrole", Roles.class);
        sql.setParameter("idrole", id);
        return sql.getSingleResult();
        
    }
    
    public void insertar(Roles r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }
    
    public void modificar(Roles r){
        em.getTransaction().begin();
        em.merge(r);
        em.getTransaction().commit();
    }
    
    public void eliminarLogico(Roles r){
        em.getTransaction().begin();
        r = em.find(Roles.class, r.getIdrole());
        r.setEstado(Boolean.FALSE);
        em.merge(r);
        em.getTransaction().commit();
    }
    
    public void eliminar(Roles r){
        em.getTransaction().begin();
        r = em.find(Roles.class, r.getIdrole());
        em.remove(r);
        em.getTransaction().commit();
    }
}
