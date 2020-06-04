/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * IncomingGood.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
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
@UuidGenerator(name="UUID_incoming_good")
@Table(name = "incoming_goods")
@NamedQueries({
    @NamedQuery(name = "IncomingGood.findWithinDate", query = "SELECT i FROM IncomingGood i "
            + "WHERE i.product = :product AND i.product.tenant = :tenant AND i.warehouse = :warehouse  AND i.incomingDate >= :startDate AND i.incomingDate <= :endDate")
        , @NamedQuery(name = "IncomingGood.sumWithinDate", query = "SELECT SUM(i.qty) FROM IncomingGood i "
            + "WHERE i.product = :product AND i.product.tenant = :tenant AND i.warehouse = :warehouse  AND i.incomingDate >= :startDate AND i.incomingDate <= :endDate")
        , @NamedQuery(name = "IncomingGood.sumQtyBeforeDate", query = "SELECT SUM(i.qty) FROM IncomingGood i "
            + "WHERE i.product = :product AND i.product.tenant = :tenant AND i.warehouse = :warehouse  AND i.incomingDate < :incomingDate")
//    , @NamedQuery(name = "IncomingGood.findBeforeIncomingDate", query = "SELECT i FROM IncomingGood i "
//            + "WHERE i.product = :product AND i.warehouse = :warehouse  AND i.incomingDate < :incomingDate")
//    , @NamedQuery(name = "IncomingGood.findBySystemId", query = "SELECT i FROM IncomingGood i WHERE i.systemId = :systemId")
//    , @NamedQuery(name = "IncomingGood.findByIncomingDate", query = "SELECT i FROM IncomingGood i WHERE i.incomingDate = :incomingDate")
//    , @NamedQuery(name = "IncomingGood.findByQty", query = "SELECT i FROM IncomingGood i WHERE i.qty = :qty")
//    , @NamedQuery(name = "IncomingGood.findByUnitPrice", query = "SELECT i FROM IncomingGood i WHERE i.unitPrice = :unitPrice")
//    , @NamedQuery(name = "IncomingGood.findByRefType", query = "SELECT i FROM IncomingGood i WHERE i.refType = :refType")
//    , @NamedQuery(name = "IncomingGood.findByRefId", query = "SELECT i FROM IncomingGood i WHERE i.refId = :refId")
//    , @NamedQuery(name = "IncomingGood.findByMemo", query = "SELECT i FROM IncomingGood i WHERE i.memo = :memo")
//    , @NamedQuery(name = "IncomingGood.findByCreatedAt", query = "SELECT i FROM IncomingGood i WHERE i.createdAt = :createdAt")
//    , @NamedQuery(name = "IncomingGood.findByUpdatedAt", query = "SELECT i FROM IncomingGood i WHERE i.updatedAt = :updatedAt")
//    , @NamedQuery(name = "IncomingGood.findBySysDeleted", query = "SELECT i FROM IncomingGood i WHERE i.sysdeleted = :sysdeleted")
})
public class IncomingGood implements Serializable 
{
    
    public static final int REF_REGISTER_ITEM = 0;

    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_incoming_good")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "incomingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date incomingDate;
    @Basic(optional = false)
    @Column(name = "qty")
    private Double qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "unitPrice")
    private Double unitPrice;
    @Basic(optional = false)
    @Column(name = "refType")
    private Integer refType;
    @Column(name = "refId")
    private String refId;
    @Column(name = "memo")
    private String memo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional=false)
    @Column(name = "sysdeleted")
    private Boolean sysDeleted;
    @JoinColumn(name = "productId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
    @JoinColumn(name = "warehouseId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    public IncomingGood ()
    {
    }

    public IncomingGood (String systemId)
    {
        this.systemId = systemId;
    }

    public IncomingGood (String systemId, Date incomingDate, double qty, Double unitPrice, int refType, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.incomingDate = incomingDate;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.refType = refType;
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

    public Date getIncomingDate ()
    {
        return incomingDate;
    }

    public void setIncomingDate (Date incomingDate)
    {
        this.incomingDate = incomingDate;
    }

    public Double getQty ()
    {
        return qty;
    }

    public void setQty (Double qty)
    {
        this.qty = qty;
    }

    public Double getUnitPrice ()
    {
        return unitPrice;
    }

    public void setUnitPrice (Double unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public Integer getRefType ()
    {
        return refType;
    }

    public void setRefType (Integer refType)
    {
        this.refType = refType;
    }

    public String getRefId ()
    {
        return refId;
    }

    public void setRefId (String refId)
    {
        this.refId = refId;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
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

    public Boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (Boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
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

    public Warehouse getWarehouse ()
    {
        return warehouse;
    }

    public void setWarehouse (Warehouse warehouseId)
    {
        this.warehouse = warehouseId;
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
        if (!(object instanceof IncomingGood)) {
            return false;
        }
        IncomingGood other = (IncomingGood) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.IncomingGood[ systemId=" + systemId + " ]";
    }

}
