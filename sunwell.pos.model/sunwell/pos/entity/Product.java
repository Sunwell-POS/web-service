/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Product.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "products")
@UuidGenerator(name="UUID_product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findBySystemId", query = "SELECT p FROM Product p WHERE p.systemId = :systemId")
    , @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE :category MEMBER OF p.categories")
    , @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name")
    , @NamedQuery(name = "Product.findByMetric", query = "SELECT p FROM Product p WHERE p.metric = :metric")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
    , @NamedQuery(name = "Product.findByHasstock", query = "SELECT p FROM Product p WHERE p.hasstock = :hasstock")
    , @NamedQuery(name = "Product.findByStockMin", query = "SELECT p FROM Product p WHERE p.stockMin = :stockMin")
    , @NamedQuery(name = "Product.findByHasdiscount", query = "SELECT p FROM Product p WHERE p.hasdiscount = :hasdiscount")
    , @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status")
    , @NamedQuery(name = "Product.findByTenant", query = "SELECT p FROM Product p WHERE p.tenant = :tenant")
    , @NamedQuery(name = "Product.findByBarcode", query = "SELECT p FROM Product p WHERE p.barcode = :barcode")
    , @NamedQuery(name = "Product.findByCreatedAt", query = "SELECT p FROM Product p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Product.findByUpdatedAt", query = "SELECT p FROM Product p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Product.findBySysDeleted", query = "SELECT p FROM Product p WHERE p.sysDeleted = :sysDeleted")})
public class Product implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator="UUID_product")
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "metric")
    private String metric;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private Double price;
    @Basic(optional = false)
    @Column(name = "hasstock")
    private boolean hasstock;
    @Column(name = "stockMin")
    private Double stockMin;
    @Basic(optional = false)
    @Column(name = "hasdiscount")
    private boolean hasdiscount;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "img")
    private String img;
    @Column(name = "barcode")
    private String barcode;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
//    private List<IncomingGood> incomingGoodsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
//    private List<OnHandStocks> onHandStocksList;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;
    
    @ManyToMany
//    @JoinTable(name="prod_multi_categories")
    @JoinTable(
      name="prod_multi_categories",
      joinColumns=@JoinColumn(name="productId", referencedColumnName="systemId"),
      inverseJoinColumns=@JoinColumn(name="prodCategoryId", referencedColumnName="systemId"))
    private List<ProdCategory> categories;

    public Product ()
    {
    }

    public Product (String systemId)
    {
        this.systemId = systemId;
    }

    public Product (String systemId, String name, String metric, Double price, boolean hasstock, boolean hasdiscount, boolean status, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.metric = metric;
        this.price = price;
        this.hasstock = hasstock;
        this.hasdiscount = hasdiscount;
        this.status = status;
        this.sysDeleted = sysdeleted;
    }

    public String getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getMetric ()
    {
        return metric;
    }

    public void setMetric (String metric)
    {
        this.metric = metric;
    }

    public Double getPrice ()
    {
        return price;
    }

    public void setPrice (Double price)
    {
        this.price = price;
    }

    public boolean getHasStock ()
    {
        return hasstock;
    }

    public void setHasStock (boolean hasstock)
    {
        this.hasstock = hasstock;
    }

    public Double getStockMin ()
    {
        return stockMin;
    }

    public void setStockMin (Double stockMin)
    {
        this.stockMin = stockMin;
    }

    public boolean getHasDiscount ()
    {
        return hasdiscount;
    }

    public void setHasDiscount (boolean hasdiscount)
    {
        this.hasdiscount = hasdiscount;
    }

    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
    }

    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getBarcode ()
    {
        return barcode;
    }

    public void setBarcode (String barcode)
    {
        this.barcode = barcode;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public Date getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

//    public List<IncomingGood> getIncomingGoodsList ()
//    {
//        return incomingGoodsList;
//    }
//
//    public void setIncomingGoodsList (List<IncomingGood> incomingGoodsList)
//    {
//        this.incomingGoodsList = incomingGoodsList;
//    }
//
//    public List<OnHandStocks> getOnHandStocksList ()
//    {
//        return onHandStocksList;
//    }
//
//    public void setOnHandStocksList (List<OnHandStocks> onHandStocksList)
//    {
//        this.onHandStocksList = onHandStocksList;
//    }

    public User getSysCreator ()
    {
        return sysCreator;
    }

    public void setSysCreator (User syscreator)
    {
        this.sysCreator = syscreator;
    }

    public User getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    public void setSysLastUpdater (User syslastupdater)
    {
        this.sysLastUpdater = syslastupdater;
    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenantId)
    {
        this.tenant = tenantId;
    }
    
    /**
     * @return the categories
     */
    public List<ProdCategory> getCategories ()
    {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories (List<ProdCategory> categories)
    {
        this.categories = categories;
    }

//    public List<SalesInvoiceLines> getSalesInvoiceLinesList ()
//    {
//        return salesInvoiceLinesList;
//    }
//
//    public void setSalesInvoiceLinesList (List<SalesInvoiceLines> salesInvoiceLinesList)
//    {
//        this.salesInvoiceLinesList = salesInvoiceLinesList;
//    }
//
//    public List<ProdMultiCategory> getProdMultiCategoriesList ()
//    {
//        return prodMultiCategoriesList;
//    }
//
//    public void setProdMultiCategoriesList (List<ProdMultiCategory> prodMultiCategoriesList)
//    {
//        this.prodMultiCategoriesList = prodMultiCategoriesList;
//    }
//
//    public List<OutcomingGood> getOutcomingGoodsList ()
//    {
//        return outcomingGoodsList;
//    }
//
//    public void setOutcomingGoodsList (List<OutcomingGood> outcomingGoodsList)
//    {
//        this.outcomingGoodsList = outcomingGoodsList;
//    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (systemId != null ? systemId.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.Product[ systemId=" + systemId + " ]";
    }

}
