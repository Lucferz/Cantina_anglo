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
@Table(name = "arqueoscaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arqueoscaja.findAll", query = "SELECT a FROM Arqueoscaja a")
    , @NamedQuery(name = "Arqueoscaja.findByIdArqueo", query = "SELECT a FROM Arqueoscaja a WHERE a.idArqueo = :idArqueo")
    , @NamedQuery(name = "Arqueoscaja.findByFkVendedor", query = "SELECT a FROM Arqueoscaja a WHERE a.fkVendedor = :fkVendedor")
    , @NamedQuery(name = "Arqueoscaja.findByFkAdmin", query = "SELECT a FROM Arqueoscaja a WHERE a.fkAdmin = :fkAdmin")
    , @NamedQuery(name = "Arqueoscaja.findByFecha", query = "SELECT a FROM Arqueoscaja a WHERE a.fecha = :fecha")})
public class Arqueoscaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_arqueo")
    private Integer idArqueo;
    @Column(name = "fk_vendedor")
    private Integer fkVendedor;
    @Column(name = "fk_admin")
    private Integer fkAdmin;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "fk_ajuste", referencedColumnName = "idAjuste")
    @ManyToOne
    private Ajustes fkAjuste;

    public Arqueoscaja() {
    }

    public Arqueoscaja(Integer idArqueo) {
        this.idArqueo = idArqueo;
    }

    public Integer getIdArqueo() {
        return idArqueo;
    }

    public void setIdArqueo(Integer idArqueo) {
        this.idArqueo = idArqueo;
    }

    public Integer getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(Integer fkVendedor) {
        this.fkVendedor = fkVendedor;
    }

    public Integer getFkAdmin() {
        return fkAdmin;
    }

    public void setFkAdmin(Integer fkAdmin) {
        this.fkAdmin = fkAdmin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ajustes getFkAjuste() {
        return fkAjuste;
    }

    public void setFkAjuste(Ajustes fkAjuste) {
        this.fkAjuste = fkAjuste;
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
        return "cantina.modelo.Arqueoscaja[ idArqueo=" + idArqueo + " ]";
    }
    
}
