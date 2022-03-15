/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.controlador.UsuariosControl;
import cantina.modelo.Arqueoscaja;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
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
public class ArqueoscajaDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    UsuariosControl uc = new UsuariosControl();

    public List<Arqueoscaja> listar() {
        TypedQuery<Arqueoscaja> query = em.createNamedQuery("Arqueoscaja.findAll", Arqueoscaja.class);
        List<Arqueoscaja> res = query.getResultList();
        return res;
    }

    public void cargarTabArqueo(JTable table) {
        String[] titulos = {"ID", "USUARIO", "FECHA DE APERTURA", "MONTO INICIAL", "FECHA DE CIERRE", "MONTO FINAL", "TOTAL DE VENTAS", "CONFIRMADO","ADMIN"};
        DefaultTableModel model = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };
        try {
            TypedQuery<Arqueoscaja> sql = em.createNamedQuery("Arqueoscaja.findAllOrderDesc", Arqueoscaja.class);
            List<Arqueoscaja> datos = sql.getResultList();
            String[] datosArq = new String[9];
            for (Arqueoscaja tba : datos) {
                datosArq[0] = tba.getIdArqueo()+"";
                Integer idUsu = tba.getFkUsuario();
                datosArq[1] = uc.buscarIdINT(idUsu).getNombre();
                datosArq[2] = prettyFechaHoraCorto(tba.getFechaInicio());
                datosArq[3] = tba.getMontoInicial()+"";
                datosArq[4] = tba.getFechaFin() != null? prettyFechaHoraCorto(tba.getFechaFin()) : tba.getFechaFin()+"";
                datosArq[5] = tba.getMontoFinal()+"";
                datosArq[6] = tba.getTotalVentas()+"";
                datosArq[7] = estaConfirmado(tba.getConfirmado());
                datosArq[8] = nomAdmin(tba.getFkAdmin());
                model.addRow(datosArq);
            }
            table.setModel(model);
        } catch (Exception e) {

        }
    }

    public Arqueoscaja UltimoElemento() {
        TypedQuery<Arqueoscaja> query = em.createNamedQuery("Arqueoscaja.findbyMaxId", Arqueoscaja.class);
        Arqueoscaja aqcaja = query.getSingleResult();
        return aqcaja;
    }

    public void insertar(Arqueoscaja a) {
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    public void modificar(Arqueoscaja a) {
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }

    public void eliminarId(Arqueoscaja a) {
        em.getTransaction().begin();
        a = em.find(Arqueoscaja.class, a.getIdArqueo());
        em.remove(a);
        em.getTransaction().commit();
    }

    public void eliminarIdLogico(Integer id) {
        em.getTransaction().begin();
        Arqueoscaja a = em.find(Arqueoscaja.class, id);
        a.setEstado(false);
        em.merge(a);
        em.getTransaction().commit();
    }
    
    private String estaConfirmado(Boolean confirmado){
        String ret = "";
        if(confirmado){
            ret = "Confirmado por:";
        }else{
            ret = "No Confirmado";
        }
        return ret;
    }
    
    private String nomAdmin (Integer idAdmin){
        String nomAdmin = "null";
        if (idAdmin != null){
            nomAdmin =  uc.buscarIdINT(idAdmin).getNombre();
        }
        return nomAdmin;
    }
    //Formato---> 9/03/2022 a las 12:39:07
    private String prettyFechaHoraCorto(Date uglyFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String fechaRetorno = "";
        LocalDateTime localdatet = uglyFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        fechaRetorno = localdatet.format(formatter) + " a las " + localdatet.toLocalTime();
        return fechaRetorno;
    }
}
