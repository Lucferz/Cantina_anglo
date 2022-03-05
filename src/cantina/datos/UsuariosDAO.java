/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.controlador.RolesControl;
import cantina.modelo.Roles;
import cantina.modelo.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class UsuariosDAO {
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    RolesControl rc = new RolesControl();
    
    public List<Usuarios> listar(){
        TypedQuery query = em.createNamedQuery("Usuarios.findAll", Usuarios.class);
        List<Usuarios> res = query.getResultList();
        return res;
    }
    
    public void cargar_tabla_user(JTable table){
        DefaultTableModel model = new DefaultTableModel(null,new String [] {
                "ID", "NOMBRE", "CONTRASEÑA", "ROL", "ESTADO", "Fecha_Alta"
            }){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(25);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
        }
        try{
           TypedQuery<Usuarios> sql =em.createNamedQuery("Usuarios.findAllEstadoTrue",Usuarios.class);
           List<Usuarios> datos = sql.getResultList();
           String [] datosUser = new String[6];
          for(Usuarios tba : datos){
              datosUser[0]=tba.getIdusuario()+"";
              datosUser[1]=tba.getNombre()+"";
              datosUser[2]=tba.getPass()+"";
              datosUser[3]=rc.buscarRolPorId(tba.getFkRoles());
              datosUser[4]=setEstado(tba.getEstado());
              datosUser[5]=tba.getDateUser()+"";
              model.addRow(datosUser);
           }
          table.setModel(model);
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
       }
    }
    public Usuarios buscarId (Usuarios u){
        u = em.find(Usuarios.class, u.getIdusuario());
        return u;
    }
    
    public Usuarios buscarIdINT (Integer id){
        Usuarios u = em.find(Usuarios.class, id);
        return u;
    }
    
    public Usuarios buscarPorNombre (String nom){
        TypedQuery<Usuarios> sql = em.createNamedQuery("Usuarios.findByNombre",Usuarios.class);
        sql.setParameter("nombre", nom);
        Usuarios u = sql.getSingleResult();
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
    
    private String setEstado(Boolean estado){
        String est=null;
        if(estado){
            est = "Activo";
        }else{
            est = "Inactivo";
        }
        return est;
    }
}
