/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")
    , @NamedQuery(name = "DetalleVenta.findByIddetalleVenta", query = "SELECT d FROM DetalleVenta d WHERE d.iddetalleVenta = :iddetalleVenta")
    , @NamedQuery(name = "DetalleVenta.findByCantidad", query = "SELECT d FROM DetalleVenta d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleVenta.findByPrecio", query = "SELECT d FROM DetalleVenta d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleVenta.findByDescuento", query = "SELECT d FROM DetalleVenta d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleVenta.findByEstado", query = "SELECT d FROM DetalleVenta d WHERE d.estado = :estado")})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_venta")
    private Integer iddetalleVenta;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Integer precio;
    @Column(name = "descuento")
    private Integer descuento;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "fk_articulos", referencedColumnName = "idarticulo")
    @ManyToOne(optional = false)
    private Articulos fkArticulos;
    @JoinColumn(name = "fk_venta", referencedColumnName = "idventa")
    @ManyToOne(optional = false)
    private Ventas fkVenta;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer iddetalleVenta) {
        this.iddetalleVenta = iddetalleVenta;
    }

    public DetalleVenta(Integer iddetalleVenta, Integer cantidad, Integer precio, Integer descuento, boolean estado, Articulos fkArticulos, Ventas fkVenta) {
        this.iddetalleVenta = iddetalleVenta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.estado = estado;
        this.fkArticulos = fkArticulos;
        this.fkVenta = fkVenta;
    }
    
    

    public DetalleVenta(Integer iddetalleVenta, boolean estado) {
        this.iddetalleVenta = iddetalleVenta;
        this.estado = estado;
    }

    public Integer getIddetalleVenta() {
        return iddetalleVenta;
    }

    public void setIddetalleVenta(Integer iddetalleVenta) {
        this.iddetalleVenta = iddetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Articulos getFkArticulos() {
        return fkArticulos;
    }

    public void setFkArticulos(Articulos fkArticulos) {
        this.fkArticulos = fkArticulos;
    }

    public Ventas getFkVenta() {
        return fkVenta;
    }

    public void setFkVenta(Ventas fkVenta) {
        this.fkVenta = fkVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleVenta != null ? iddetalleVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.iddetalleVenta == null && other.iddetalleVenta != null) || (this.iddetalleVenta != null && !this.iddetalleVenta.equals(other.iddetalleVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "iddetalleVenta=" + iddetalleVenta + ", cantidad=" + cantidad + ", precio=" + precio + ", descuento=" + descuento + ", estado=" + estado + ", fkArticulos=" + fkArticulos + ", fkVenta=" + fkVenta + '}';
    }

    
    
}
