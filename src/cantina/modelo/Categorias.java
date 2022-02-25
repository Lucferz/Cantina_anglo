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
import javax.persistence.CascadeType;
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
@Table(name = "categorias")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c")
    , @NamedQuery(name = "Categorias.findByIdcategoria", query = "SELECT c FROM Categorias c WHERE c.idcategoria = :idcategoria")
    , @NamedQuery(name = "Categorias.findByNombre", query = "SELECT c FROM Categorias c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Categorias.findNameByEstado", query = "SELECT c.nombre FROM Categorias c WHERE c.estado = 1")
    , @NamedQuery(name = "Categorias.findByEstado", query = "SELECT c FROM Categorias c WHERE c.estado = :estado")
    , @NamedQuery(name = "Categorias.findByDateCategorias", query = "SELECT c FROM Categorias c WHERE c.dateCategorias = :dateCategorias")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoria;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @Column(name = "date_categorias")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCategorias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCategorias")
    private List<Articulos> articulosList;

    public Categorias() {
    }

    public Categorias(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Categorias(Integer idcategoria, String nombre, boolean estado) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.estado = estado;
    }
    

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getDateCategorias() {
        return dateCategorias;
    }

    public void setDateCategorias(Date dateCategorias) {
        this.dateCategorias = dateCategorias;
    }

    @XmlTransient
    public List<Articulos> getArticulosList() {
        return articulosList;
    }

    public void setArticulosList(List<Articulos> articulosList) {
        this.articulosList = articulosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorias{" + "idcategoria=" + idcategoria + ", nombre=" + nombre + ", estado=" + estado + ", dateCategorias=" + dateCategorias + '}';
    }

    

    
    
}
