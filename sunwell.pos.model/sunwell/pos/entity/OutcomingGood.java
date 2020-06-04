/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * OutcomingGood.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
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
@Table(name = "outcoming_goods")
@UuidGenerator(name="UUID_outcoming_good")
@NamedQueries({
     @NamedQuery(name = "OutcomingGood.findWithinDate", query = "SELECT o FROM OutcomingGood o "
            + "WHERE o.product = :product AND o.product.tenant = :tenant AND o.warehouse = :warehouse  "
            + "AND o.outcomingDate >= :startDate AND o.outcomingDate <= :endDate")
    , @NamedQuery(name = "OutcomingGood.sumWithinDate", query = "SELECT SUM(o.qty) FROM OutcomingGood o "
            + "WHERE o.product = :product AND o.product.tenant = :tenant AND o.warehouse = :warehouse  "
            + "AND o.outcomingDate >= :startDate AND o.outcomingDate <= :endDate")
    , @NamedQuery(name = "OutcomingGood.sumQtyBeforeDate", query = "SELECT SUM(o.qty) FROM OutcomingGood o "
            + "WHERE o.product = :product AND o.product.tenant = :tenant AND o.warehouse = :warehouse  AND o.outcomingDate < :outcomingDate")
//    @NamedQuery(name = "OutcomingGood.findAll", query = "SELECT o FROM OutcomingGood o")
//    , @NamedQuery(name = "OutcomingGood.findBySystemId", query = "SELECT o FROM OutcomingGood o WHERE o.systemId = :systemId")
//    , @NamedQuery(name = "OutcomingGood.sumQtyBeforeDate", query = "SELECT SUM(o.qty) FROM OutcomingGood o "
//        + "WHERE o.outcomingDate = :date")
//    , @NamedQuery(name = "OutcomingGood.findBeforeOutcomingDate", query = "SELECT o FROM OutcomingGood o WHERE o.outcomingDate < :outcomingDate")
//    , @NamedQuery(name = "OutcomingGood.findByOutcomingDate", query = "SELECT o FROM OutcomingGood o WHERE o.outcomingDate = :outcomingDate")
//    , @NamedQuery(name = "OutcomingGood.findByQty", query = "SELECT o FROM OutcomingGood o WHERE o.qty = :qty")
//    , @NamedQuery(name = "OutcomingGood.findByRefType", query = "SELECT o FROM OutcomingGood o WHERE o.refType = :refType")
//    , @NamedQuery(name = "OutcomingGood.findByRefId", query = "SELECT o FROM OutcomingGood o WHERE o.refId = :refId")
//    , @NamedQuery(name = "OutcomingGood.findByMemo", query = "SELECT o FROM OutcomingGood o WHERE o.memo = :memo")
//    , @NamedQuery(name = "OutcomingGood.findByCreatedAt", query = "SELECT o FROM OutcomingGood o WHERE o.createdAt = :createdAt")
//    , @NamedQuery(name = "OutcomingGood.findByUpdatedAt", query = "SELECT o FROM OutcomingGood o WHERE o.updatedAt = :updatedAt")
//    , @NamedQuery(name = "OutcomingGood.findBySysDeleted", query = "SELECT o FROM OutcomingGood o WHERE o.sysdeleted = :sysdeleted")
})
public class OutcomingGood implements Serializable 
{

    public static final int REF_SALES = 0;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_outcoming_good")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "outcomingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outcomingDate;
    @Basic(optional = false)
    @Column(name = "qty")
    private Double qty;
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
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private Boolean sysDeleted = false;
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

    public OutcomingGood ()
    {
    }

    public OutcomingGood (String systemId)
    {
        this.systemId = systemId;
    }

    public OutcomingGood (String systemId, Date outcomingDate, Double qty, Integer refType, Boolean sysdeleted)
    {
        this.systemId = systemId;
        this.outcomingDate = outcomingDate;
        this.qty = qty;
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

    public Date getOutcomingDate ()
    {
        return outcomingDate;
    }

    public void setOutcomingDate (Date outcomingDate)
    {
        this.outcomingDate = outcomingDate;
    }

    public Double getQty ()
    {
        return qty;
    }

    public void setQty (Double qty)
    {
        this.qty = qty;
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
        if (!(object instanceof OutcomingGood)) {
            return false;
        }
        OutcomingGood other = (OutcomingGood) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.OutcomingGood[ systemId=" + systemId + " ]";
    }

}
