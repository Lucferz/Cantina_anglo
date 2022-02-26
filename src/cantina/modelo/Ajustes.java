/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "ajustes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ajustes.findAll", query = "SELECT a FROM Ajustes a")
    , @NamedQuery(name = "Ajustes.findByIdAjuste", query = "SELECT a FROM Ajustes a WHERE a.idAjuste = :idAjuste")
    , @NamedQuery(name = "Ajustes.findByTipoAjuste", query = "SELECT a FROM Ajustes a WHERE a.tipoAjuste = :tipoAjuste")
    , @NamedQuery(name = "Ajustes.findByDateAjuste", query = "SELECT a FROM Ajustes a WHERE a.dateAjuste = :dateAjuste")})
public class Ajustes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAjuste")
    private Integer idAjuste;
    @Column(name = "tipo_ajuste")
    private String tipoAjuste;
    @Column(name = "date_ajuste")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjuste;
    @OneToMany(mappedBy = "fkAjuste")
    private List<Arqueoscaja> arqueoscajaList;

    public Ajustes() {
    }

    public Ajustes(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    public Ajustes(Integer idAjuste, String tipoAjuste, Date dateAjuste) {
        this.idAjuste = idAjuste;
        this.tipoAjuste = tipoAjuste;
        this.dateAjuste = dateAjuste;
    }
    

    public Integer getIdAjuste() {
        return idAjuste;
    }

    public void setIdAjuste(Integer idAjuste) {
        this.idAjuste = idAjuste;
    }

    public String getTipoAjuste() {
        return tipoAjuste;
    }

    public void setTipoAjuste(String tipoAjuste) {
        this.tipoAjuste = tipoAjuste;
    }

    public Date getDateAjuste() {
        return dateAjuste;
    }

    public void setDateAjuste(Date dateAjuste) {
        this.dateAjuste = dateAjuste;
    }

    @XmlTransient
    public List<Arqueoscaja> getArqueoscajaList() {
        return arqueoscajaList;
    }

    public void setArqueoscajaList(List<Arqueoscaja> arqueoscajaList) {
        this.arqueoscajaList = arqueoscajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAjuste != null ? idAjuste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajustes)) {
            return false;
        }
        Ajustes other = (Ajustes) object;
        if ((this.idAjuste == null && other.idAjuste != null) || (this.idAjuste != null && !this.idAjuste.equals(other.idAjuste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ajustes{" + "idAjuste=" + idAjuste + ", tipoAjuste=" + tipoAjuste + ", dateAjuste=" + dateAjuste + '}';
    }

    
    
}
