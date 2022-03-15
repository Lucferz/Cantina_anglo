/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name="Ventas.cargarTabla", query = "SELECT v FROM Ventas v WHERE v.estado = 1 ORDER BY v.idventa desc")
    , @NamedQuery(name = "Ventas.TotalVentasPCierre", query = "SELECT COUNT(v.idventa) from Ventas v WHERE v.fecha > (SELECT aq.fechaInicio FROM Arqueoscaja aq WHERE aq.idArqueo = (SELECT MAX(aq.idArqueo) FROM Arqueoscaja aq))")
    , @NamedQuery(name = "Ventas.findMaxId", query = "SELECT MAX(v.idventa) FROM Ventas v")
    , @NamedQuery(name = "Ventas.SumVentasXArqueo", query = "SELECT SUM(v.total) FROM Ventas v WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin")
    , @NamedQuery(name = "Ventas.findByIdventa", query = "SELECT v FROM Ventas v WHERE v.idventa = :idventa")
    , @NamedQuery(name = "Ventas.findByIdCaja", query = "SELECT v FROM Ventas v WHERE v.idCaja = :idCaja")
    , @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Ventas.findByTotal", query = "SELECT v FROM Ventas v WHERE v.total = :total")
    , @NamedQuery(name = "Ventas.findByEstado", query = "SELECT v FROM Ventas v WHERE v.estado = :estado")
    , @NamedQuery(name = "Ventas.findByAjuste", query = "SELECT v FROM Ventas v WHERE v.ajuste = :ajuste")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idventa")
    private Integer idventa;
    @Basic(optional = false)
    @Column(name = "id_caja")
    private int idCaja;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "total")
    private Integer total;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "ajuste")
    private Boolean ajuste;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuarios fkUsuario;

    public Ventas() {
    }

    public Ventas(Integer idventa) {
        this.idventa = idventa;
    }

    public Ventas(Integer idventa, int idCaja, boolean estado) {
        this.idventa = idventa;
        this.idCaja = idCaja;
        this.estado = estado;
    }

    public Ventas(Integer idventa, int idCaja, Date fecha, Integer total, boolean estado, Boolean ajuste, Usuarios fkUsuario) {
        this.idventa = idventa;
        this.idCaja = idCaja;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.ajuste = ajuste;
        this.fkUsuario = fkUsuario;
    }
    

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Boolean getAjuste() {
        return ajuste;
    }

    public void setAjuste(Boolean ajuste) {
        this.ajuste = ajuste;
    }

    public Usuarios getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuarios fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventa != null ? idventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idventa == null && other.idventa != null) || (this.idventa != null && !this.idventa.equals(other.idventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ventas{" + "idventa=" + idventa + ", idCaja=" + idCaja + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + ", ajuste=" + ajuste + ", fkUsuario=" + fkUsuario + '}';
    }

    
    
}
