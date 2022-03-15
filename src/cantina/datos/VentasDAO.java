/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.datos;

import cantina.controlador.ControladorAbstract;
import cantina.modelo.Usuarios;
import cantina.modelo.Ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class VentasDAO extends ControladorAbstract{
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("CANTINA-ANGLO-SISTEMA-VENTASPU");
    EntityManager em = emf.createEntityManager();
    public List<Ventas> listar(){
        TypedQuery query = em.createNamedQuery("Ventas.findAll", Ventas.class);
        List<Ventas> res = query.getResultList();
        return res;
    }
    
    public Ventas buscar(Integer id){
        Ventas v = em.find(Ventas.class, id);
        return v;
    }
    
    public Integer buscarMaxId(){
        TypedQuery<Integer> sql = em.createNamedQuery("Ventas.findMaxId", Integer.class);
        Integer ven = sql.getSingleResult();
        return ven;
    }
    
    public Integer TotalVentasPArqueo(){
        TypedQuery<Long> query = em.createNamedQuery("Ventas.TotalVentasPCierre", Long.class);
        Integer totalventas = Math.toIntExact(query.getSingleResult());
        return totalventas;
    }
    
    public Integer SumTotalVenArqueo(Date fechaInicio, Date fechaFin){
        TypedQuery<Long> query = em.createNamedQuery("Ventas.SumVentasXArqueo", Long.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        Integer cero = 0;
        Long sumlong = query.getSingleResult()==null? cero.longValue() :query.getSingleResult() ;
        System.out.println("Hasta aca todo bien SumLong = "+sumlong);
        Integer sumVen = Math.toIntExact(sumlong);
        return sumVen;
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
    
    public void cargar_tabla_venta(JTable table){
        String [] titulos = {"ID","FECHA","USUARIO","TOTAL", "MONTO DE AJUSTE"};
        DefaultTableModel model = new DefaultTableModel(null, titulos){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        try{
            TypedQuery<Ventas> sql = em.createNamedQuery("Ventas.cargarTabla", Ventas.class);
            List<Ventas> datos = sql.getResultList();
            String [] datosVenta = new String[5];
            for(Ventas vta : datos){
                datosVenta[0] = vta.getIdventa()+"";
                datosVenta[1] = prettyFechaHoraCorto(vta.getFecha());
                Usuarios us = vta.getFkUsuario();
                datosVenta[2] = us.getNombre();
                datosVenta[3] = vta.getTotal()+"";
                datosVenta[4] = Ajuste(vta.getAjuste());
                model.addRow(datosVenta);
            }
            table.setModel(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString(),"Error en Carga de tabla Ventas",0);
        }
    }
    
    private String Ajuste (boolean ajuste){
        String var = null;
        if (ajuste){
            var = "SI";
        }else{
            var = "NO";
        }
        return var;
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
