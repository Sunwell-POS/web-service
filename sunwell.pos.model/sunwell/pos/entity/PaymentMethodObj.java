/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * PaymentMethodObj.java
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.UuidGenerator;
import sunwell.pos.entity.dto.StandardDTO;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "payment_method_objs")
@UuidGenerator(name="payment_method_objs")
@NamedQueries({
    @NamedQuery(name = "PaymentMethodObj.findAll", query = "SELECT p FROM PaymentMethodObj p")
    , @NamedQuery(name = "PaymentMethodObj.findBySystemId", query = "SELECT p FROM PaymentMethodObj p WHERE p.systemId = :systemId")
    , @NamedQuery(name = "PaymentMethodObj.findByTenant", query = "SELECT p FROM PaymentMethodObj p WHERE p.tenant = :tenant")
    , @NamedQuery(name = "PaymentMethodObj.findByName", query = "SELECT p FROM PaymentMethodObj p WHERE p.name = :name")
    , @NamedQuery(name = "PaymentMethodObj.findBySysbuiltin", query = "SELECT p FROM PaymentMethodObj p WHERE p.sysbuiltin = :sysbuiltin")
    , @NamedQuery(name = "PaymentMethodObj.findByStatus", query = "SELECT p FROM PaymentMethodObj p WHERE p.status = :status")
    , @NamedQuery(name = "PaymentMethodObj.findByHasDisc", query = "SELECT p FROM PaymentMethodObj p WHERE p.hasDisc = :hasDisc")
    , @NamedQuery(name = "PaymentMethodObj.findByDiscValue", query = "SELECT p FROM PaymentMethodObj p WHERE p.discValue = :discValue")
    , @NamedQuery(name = "PaymentMethodObj.findByDiscType", query = "SELECT p FROM PaymentMethodObj p WHERE p.discType = :discType")
    , @NamedQuery(name = "PaymentMethodObj.findByMinPayment", query = "SELECT p FROM PaymentMethodObj p WHERE p.minPayment = :minPayment")
    , @NamedQuery(name = "PaymentMethodObj.findByMaxPayment", query = "SELECT p FROM PaymentMethodObj p WHERE p.maxPayment = :maxPayment")
    , @NamedQuery(name = "PaymentMethodObj.findByDueDate", query = "SELECT p FROM PaymentMethodObj p WHERE p.dueDate = :dueDate")
    , @NamedQuery(name = "PaymentMethodObj.findByCreatedAt", query = "SELECT p FROM PaymentMethodObj p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PaymentMethodObj.findByUpdatedAt", query = "SELECT p FROM PaymentMethodObj p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "PaymentMethodObj.findBySysDeleted", query = "SELECT p FROM PaymentMethodObj p WHERE p.sysDeleted = :sysDeleted")})
public class PaymentMethodObj implements Serializable 
{
    public static final int DISC_TYPE_PERCENTAGE = 0;
    public static final int DISC_TYPE_AMOUNT = 1;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="payment_method_objs")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "sysbuiltin")
    private Boolean sysbuiltin = true;
    @Basic(optional = false)
    @Column(name = "status")
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "hasDisc")
    private Boolean hasDisc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discValue")
    private Double discValue;
    @Basic(optional = false)
    @Column(name = "discType")
    private Integer discType;
    @Column(name = "minPayment")
    private Double minPayment;
    @Column(name = "maxPayment")
    private Double maxPayment;
    @Lob
    @Column(name = "memo")
    private String memo;
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "sysdeleted")
    private Boolean sysDeleted;
    @JoinColumn(name = "parentId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private PaymentMethod parent;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;

    public PaymentMethodObj ()
    {
    }

    public PaymentMethodObj (String systemId)
    {
        this.systemId = systemId;
    }

    public PaymentMethodObj (String systemId, String name, Boolean sysbuiltin, Boolean status, Boolean hasDisc, Integer discType, Boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.sysbuiltin = sysbuiltin;
        this.status = status;
        this.hasDisc = hasDisc;
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

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public boolean getSysbuiltin ()
    {
        return sysbuiltin;
    }

    public void setSysbuiltin (Boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
    }

    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (Boolean status)
    {
        this.status = status;
    }

    public boolean getHasDisc ()
    {
        return hasDisc;
    }

    public void setHasDisc (boolean hasDisc)
    {
        this.hasDisc = hasDisc;
    }

    public Double getDiscValue ()
    {
        return discValue;
    }

    public void setDiscValue (Double discValue)
    {
        this.discValue = discValue;
    }

    public int getDiscType ()
    {
        return discType;
    }

    public void setDiscType (Integer discType)
    {
        this.discType = discType;
    }

    public Double getMinPayment ()
    {
        return minPayment;
    }

    public void setMinPayment (Double minPayment)
    {
        this.minPayment = minPayment;
    }

    public Double getMaxPayment ()
    {
        return maxPayment;
    }

    public void setMaxPayment (Double maxPayment)
    {
        this.maxPayment = maxPayment;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public Date getDueDate ()
    {
        return dueDate;
    }

    public void setDueDate (Date dueDate)
    {
        this.dueDate = dueDate;
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

    public PaymentMethod getParent ()
    {
        return parent;
    }

    public void setParent (PaymentMethod parentId)
    {
        this.parent = parentId;
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

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenantId)
    {
        this.tenant = tenantId;
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
        if (!(object instanceof PaymentMethodObj)) {
            return false;
        }
        PaymentMethodObj other = (PaymentMethodObj) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.PaymentMethodObj[ systemId=" + systemId + " ]";
    }

}
