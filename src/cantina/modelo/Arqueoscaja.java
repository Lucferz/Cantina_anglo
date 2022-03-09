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
@Table(name = "arqueoscaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arqueoscaja.findAll", query = "SELECT a FROM Arqueoscaja a WHERE a.estado = true")
    , @NamedQuery(name = "Arqueoscaja.findAllOrderDesc", query = "SELECT a FROM Arqueoscaja a WHERE a.estado = true ORDER BY a.idArqueo desc")
    , @NamedQuery(name = "Arqueoscaja.findbyMaxId", query = "SELECT a FROM Arqueoscaja a WHERE a.idArqueo = (SELECT MAX(a.idArqueo) FROM Arqueoscaja a)")
    , @NamedQuery(name = "Arqueoscaja.findByIdArqueo", query = "SELECT a FROM Arqueoscaja a WHERE a.idArqueo = :idArqueo")
    , @NamedQuery(name = "Arqueoscaja.findByFkCaja", query = "SELECT a FROM Arqueoscaja a WHERE a.fkCaja = :fkCaja")
    , @NamedQuery(name = "Arqueoscaja.findByFkUsuario", query = "SELECT a FROM Arqueoscaja a WHERE a.fkUsuario = :fkUsuario")
    , @NamedQuery(name = "Arqueoscaja.findByFechaInicio", query = "SELECT a FROM Arqueoscaja a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Arqueoscaja.findByFechaFin", query = "SELECT a FROM Arqueoscaja a WHERE a.fechaFin = :fechaFin")
    , @NamedQuery(name = "Arqueoscaja.findByMontoInicial", query = "SELECT a FROM Arqueoscaja a WHERE a.montoInicial = :montoInicial")
    , @NamedQuery(name = "Arqueoscaja.findByMontoFinal", query = "SELECT a FROM Arqueoscaja a WHERE a.montoFinal = :montoFinal")
    , @NamedQuery(name = "Arqueoscaja.findByTotalVentas", query = "SELECT a FROM Arqueoscaja a WHERE a.totalVentas = :totalVentas")
    , @NamedQuery(name = "Arqueoscaja.findByConfirmado", query = "SELECT a FROM Arqueoscaja a WHERE a.confirmado = :confirmado")
    , @NamedQuery(name = "Arqueoscaja.findByEstado", query = "SELECT a FROM Arqueoscaja a WHERE a.estado = :estado")})
public class Arqueoscaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_arqueo")
    private Integer idArqueo;
    @Basic(optional = false)
    @Column(name = "fk_caja")
    private int fkCaja;
    @Column(name = "fk_usuario")
    private Integer fkUsuario;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "monto_inicial")
    private Integer montoInicial;
    @Column(name = "monto_final")
    private Integer montoFinal;
    @Column(name = "total_ventas")
    private Integer totalVentas;
    @Column(name = "Confirmado")
    private Boolean confirmado;
    @Column(name = "estado")
    private Boolean estado;

    public Arqueoscaja() {
    }

    public Arqueoscaja(Integer idArqueo) {
        this.idArqueo = idArqueo;
    }

    public Arqueoscaja(Integer idArqueo, int fkCaja) {
        this.idArqueo = idArqueo;
        this.fkCaja = fkCaja;
    }
    public Arqueoscaja(Integer idArqueo, int fkCaja, Integer fkUsuario, Date fechaInicio, Date fechaFin, Integer montoInicial, Integer montoFinal, Integer totalVentas, Boolean estado, Boolean confirmado) {
        this.idArqueo = idArqueo;
        this.fkCaja = fkCaja;
        this.fkUsuario = fkUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoInicial = montoInicial;
        this.montoFinal = montoFinal;
        this.totalVentas = totalVentas;
        this.estado = estado;
        this.confirmado = confirmado;
    }

    public Integer getIdArqueo() {
        return idArqueo;
    }

    public void setIdArqueo(Integer idArqueo) {
        this.idArqueo = idArqueo;
    }

    public int getFkCaja() {
        return fkCaja;
    }

    public void setFkCaja(int fkCaja) {
        this.fkCaja = fkCaja;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(Integer montoInicial) {
        this.montoInicial = montoInicial;
    }

    public Integer getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(Integer montoFinal) {
        this.montoFinal = montoFinal;
    }

    public Integer getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Integer totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArqueo != null ? idArqueo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arqueoscaja)) {
            return false;
        }
        Arqueoscaja other = (Arqueoscaja) object;
        if ((this.idArqueo == null && other.idArqueo != null) || (this.idArqueo != null && !this.idArqueo.equals(other.idArqueo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Arqueoscaja{" + "idArqueo=" + idArqueo + ", fkCaja=" + fkCaja + ", fkUsuario=" + fkUsuario + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", montoInicial=" + montoInicial + ", montoFinal=" + montoFinal + ", totalVentas=" + totalVentas + ", confirmado=" + confirmado + ", estado=" + estado + '}';
    }

    
    
}
