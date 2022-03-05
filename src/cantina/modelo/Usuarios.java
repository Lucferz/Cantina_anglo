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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findAllEstadoTrue", query = "SELECT u FROM Usuarios u WHERE u.estado = 1")
    , @NamedQuery(name = "Usuarios.findByIdusuario", query = "SELECT u FROM Usuarios u WHERE u.idusuario = :idusuario")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado")
    , @NamedQuery(name = "Usuarios.findByPass", query = "SELECT u FROM Usuarios u WHERE u.pass = :pass")
    , @NamedQuery(name = "Usuarios.findByFkRoles", query = "SELECT u FROM Usuarios u WHERE u.fkRoles = :fkRoles")
    , @NamedQuery(name = "Usuarios.findByDateUser", query = "SELECT u FROM Usuarios u WHERE u.dateUser = :dateUser")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "fk_roles")
    private int fkRoles;
    @Column(name = "date_user")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUser;

    public Usuarios() {
    }

    public Usuarios(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuarios(Integer idusuario, String nombre, boolean estado, int fkRoles) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.estado = estado;
        this.fkRoles = fkRoles;
    }

    public Usuarios(Integer idusuario, String nombre, boolean estado, String pass, int fkRoles, Date dateUser) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.estado = estado;
        this.pass = pass;
        this.fkRoles = fkRoles;
        this.dateUser = dateUser;
    }
    

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getFkRoles() {
        return fkRoles;
    }

    public void setFkRoles(int fkRoles) {
        this.fkRoles = fkRoles;
    }

    public Date getDateUser() {
        return dateUser;
    }

    public void setDateUser(Date dateUser) {
        this.dateUser = dateUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "idusuario=" + idusuario + ", nombre=" + nombre + ", estado=" + estado + ", pass=" + pass + ", fkRoles=" + fkRoles + ", dateUser=" + dateUser + '}';
    }

    
    
}
