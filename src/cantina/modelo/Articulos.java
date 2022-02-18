/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "articulos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a")
    , @NamedQuery(name = "Articulos.findByIdarticulo", query = "SELECT a FROM Articulos a WHERE a.idarticulo = :idarticulo")
    , @NamedQuery(name = "Articulos.findByCodigo", query = "SELECT a FROM Articulos a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Articulos.findByNombre", query = "SELECT a FROM Articulos a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Articulos.findByPrecioVenta", query = "SELECT a FROM Articulos a WHERE a.precioVenta = :precioVenta")
    , @NamedQuery(name = "Articulos.findByCosto", query = "SELECT a FROM Articulos a WHERE a.costo = :costo")
    , @NamedQuery(name = "Articulos.findByStock", query = "SELECT a FROM Articulos a WHERE a.stock = :stock")
    , @NamedQuery(name = "Articulos.findByDescripcion", query = "SELECT a FROM Articulos a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Articulos.findByEstado", query = "SELECT a FROM Articulos a WHERE a.estado = :estado")})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticulo")
    private Integer idarticulo;
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "precio_venta")
    private int precioVenta;
    @Column(name = "costo")
    private Integer costo;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArticulos")
    private List<DetalleVenta> detalleVentaList;
    @JoinColumn(name = "fk_categorias", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categorias fkCategorias;

    public Articulos() {
    }

    public Articulos(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Articulos(Integer idarticulo, String codigo, String nombre, int precioVenta, Integer costo, Integer stock, String descripcion, Boolean estado, List<DetalleVenta> detalleVentaList, Categorias fkCategorias) {
        this.idarticulo = idarticulo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.costo = costo;
        this.stock = stock;
        this.descripcion = descripcion;
        this.estado = estado;
        this.detalleVentaList = detalleVentaList;
        this.fkCategorias = fkCategorias;
    }
    
    

    public Articulos(Integer idarticulo, String nombre, int precioVenta) {
        this.idarticulo = idarticulo;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
    }

    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public Categorias getFkCategorias() {
        return fkCategorias;
    }

    public void setFkCategorias(Categorias fkCategorias) {
        this.fkCategorias = fkCategorias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticulo != null ? idarticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.idarticulo == null && other.idarticulo != null) || (this.idarticulo != null && !this.idarticulo.equals(other.idarticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Articulos{" + "idarticulo=" + idarticulo + ", codigo=" + codigo + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", costo=" + costo + ", stock=" + stock + ", descripcion=" + descripcion + ", estado=" + estado + ", detalleVentaList=" + detalleVentaList + ", fkCategorias=" + fkCategorias + '}';
    }

        
    
}
