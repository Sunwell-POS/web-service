/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * SalesInvoiceLine.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity 
@UuidGenerator(name="UUID_salesInvoiceLine")
@Table(name = "sales_invoice_lines")
@NamedQueries({
    @NamedQuery(name = "SalesInvoiceLine.findAll", query = "SELECT s FROM SalesInvoiceLine s")
    , @NamedQuery(name = "SalesInvoiceLine.findBySystemId", query = "SELECT s FROM SalesInvoiceLine s WHERE s.systemId = :systemId")
    , @NamedQuery(name = "SalesInvoiceLine.findByPrice", query = "SELECT s FROM SalesInvoiceLine s WHERE s.price = :price")
    , @NamedQuery(name = "SalesInvoiceLine.findByMetric", query = "SELECT s FROM SalesInvoiceLine s WHERE s.metric = :metric")
    , @NamedQuery(name = "SalesInvoiceLine.findByHasdiscount", query = "SELECT s FROM SalesInvoiceLine s WHERE s.hasdiscount = :hasdiscount")
    , @NamedQuery(name = "SalesInvoiceLine.findByDiscValue", query = "SELECT s FROM SalesInvoiceLine s WHERE s.discValue = :discValue")
    , @NamedQuery(name = "SalesInvoiceLine.findByDiscType", query = "SELECT s FROM SalesInvoiceLine s WHERE s.discType = :discType")
    , @NamedQuery(name = "SalesInvoiceLine.findByQty", query = "SELECT s FROM SalesInvoiceLine s WHERE s.qty = :qty")
    , @NamedQuery(name = "SalesInvoiceLine.findByCreatedAt", query = "SELECT s FROM SalesInvoiceLine s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "SalesInvoiceLine.findByUpdatedAt", query = "SELECT s FROM SalesInvoiceLine s WHERE s.updatedAt = :updatedAt")
    , @NamedQuery(name = "SalesInvoiceLine.findBySysDeleted", query = "SELECT s FROM SalesInvoiceLine s WHERE s.sysDeleted = :sysDeleted")})
public class SalesInvoiceLine implements Serializable 
{

    private static final long serialVersionUID = 1L;
    
    public static final int DISC_TYPE_PERCENTAGE = 0;
    public static final int DISC_TYPE_MONEY = 1;
    
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_salesInvoiceLine")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "price")
    private Double price ;
    @Basic(optional = false)
    @Column(name = "metric")
    private String metric;
    @Basic(optional = false)
    @Column(name = "hasdiscount")
    private Boolean hasdiscount = false;
    @Basic(optional = false)
    @Column(name = "discValue")
    private Double discValue = 0.0;
    @Basic(optional = false)
    @Column(name = "discType")
    private Integer discType = 1;
    @Column(name = "qty")
    private Double qty;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private Boolean sysDeleted = false;
    @JoinColumn(name = "parentId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private SalesInvoice parent;
    @JoinColumn(name = "productId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;

    public SalesInvoiceLine ()
    {
    }

    public SalesInvoiceLine (String systemId)
    {
        this.systemId = systemId;
    }

    public SalesInvoiceLine (String systemId, Double price, String metric, Boolean hasdiscount, Double discValue, Integer discType, Boolean sysdeleted)
    {
        this.systemId = systemId;
        this.price = price;
        this.metric = metric;
        this.hasdiscount = hasdiscount;
        this.discValue = discValue;
        this.discType = discType;
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

    public Double getPrice ()
    {
        return price;
    }

    public void setPrice (Double price)
    {
        this.price = price;
    }

    public String getMetric ()
    {
        return metric;
    }

    public void setMetric (String metric)
    {
        this.metric = metric;
    }

    public Boolean getHasdiscount ()
    {
        return hasdiscount;
    }

    public void setHasdiscount (Boolean hasdiscount)
    {
        this.hasdiscount = hasdiscount;
    }

    public Double getDiscValue ()
    {
        return discValue;
    }

    public void setDiscValue (Double discValue)
    {
        this.discValue = discValue;
    }

    public Integer getDiscType ()
    {
        return discType;
    }

    public void setDiscType (Integer discType)
    {
        this.discType = discType;
    }

    public Double getQty ()
    {
        return qty;
    }

    public void setQty (Double qty)
    {
        this.qty = qty;
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

    public void setSysDeleted (Boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

    public SalesInvoice getParent ()
    {
        return parent;
    }

    public void setParent (SalesInvoice parentId)
    {
        this.parent = parentId;
    }

    public Product getProduct ()
    {
        return product;
    }

    public void setProduct (Product productId)
    {
        this.product = productId;
    }

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
        if (!(object instanceof SalesInvoiceLine)) {
            return false;
        }
        SalesInvoiceLine other = (SalesInvoiceLine) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.SalesInvoiceLine[ systemId=" + systemId + " ]";
    }

}
