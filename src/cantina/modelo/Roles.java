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
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findIdByRol", query = "SELECT r.idrole FROM Roles r WHERE r.rol = :rol")
    , @NamedQuery(name = "Roles.findRolByEstadoTrue", query = "SELECT r.rol FROM Roles r WHERE r.estado = 1")
    , @NamedQuery(name = "Roles.findNombreById", query = "SELECT r.rol FROM Roles r WHERE r.idrole = :idrol")
    , @NamedQuery(name = "Roles.findByIdrole", query = "SELECT r FROM Roles r WHERE r.idrole = :idrole")
    , @NamedQuery(name = "Roles.findByRol", query = "SELECT r FROM Roles r WHERE r.rol = :rol")
    , @NamedQuery(name = "Roles.findByDesc", query = "SELECT r FROM Roles r WHERE r.desc = :desc")
    , @NamedQuery(name = "Roles.findByEstado", query = "SELECT r FROM Roles r WHERE r.estado = :estado")
    , @NamedQuery(name = "Roles.findByDateRol", query = "SELECT r FROM Roles r WHERE r.dateRol = :dateRol")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrole")
    private Integer idrole;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @Column(name = "desc")
    private String desc;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "date_rol")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRol;

    public Roles() {
    }

    public Roles(Integer idrole) {
        this.idrole = idrole;
    }

    public Roles(Integer idrole, String rol, boolean estado) {
        this.idrole = idrole;
        this.rol = rol;
        this.estado = estado;
    }

    public Roles(Integer idrole, String rol, String desc, boolean estado, Date dateRol) {
        this.idrole = idrole;
        this.rol = rol;
        this.desc = desc;
        this.estado = estado;
        this.dateRol = dateRol;
    }
    
    

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getDateRol() {
        return dateRol;
    }

    public void setDateRol(Date dateRol) {
        this.dateRol = dateRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrole != null ? idrole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idrole == null && other.idrole != null) || (this.idrole != null && !this.idrole.equals(other.idrole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Roles{" + "idrole=" + idrole + ", rol=" + rol + ", desc=" + desc + ", estado=" + estado + ", dateRol=" + dateRol + '}';
    }

    
    
}
