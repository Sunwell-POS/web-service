/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * PaymentMethod.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "payment_methods")
@UuidGenerator(name="UUID_paymentMethod")
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p")
    , @NamedQuery(name = "PaymentMethod.findBySystemId", query = "SELECT p FROM PaymentMethod p WHERE p.systemId = :systemId")
    , @NamedQuery(name = "PaymentMethod.findByName", query = "SELECT p FROM PaymentMethod p WHERE p.name = :name")
    , @NamedQuery(name = "PaymentMethod.findByMemo", query = "SELECT p FROM PaymentMethod p WHERE p.memo = :memo")
    , @NamedQuery(name = "PaymentMethod.findByCreatedAt", query = "SELECT p FROM PaymentMethod p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "PaymentMethod.findByUpdatedAt", query = "SELECT p FROM PaymentMethod p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "PaymentMethod.findBySysDeleted", query = "SELECT p FROM PaymentMethod p WHERE p.sysDeleted = :sysdeleted")})
public class PaymentMethod implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_paymentMethod")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethodId")
//    private List<SalesPayments> salesPaymentsList;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentId")
//    private List<PaymentMethodObjs> paymentMethodObjsList;

    public PaymentMethod ()
    {
    }

    public PaymentMethod (String systemId)
    {
        this.systemId = systemId;
    }

    public PaymentMethod (String systemId, String name, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
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

//    public List<SalesPayments> getSalesPaymentsList ()
//    {
//        return salesPaymentsList;
//    }
//
//    public void setSalesPaymentsList (List<SalesPayments> salesPaymentsList)
//    {
//        this.salesPaymentsList = salesPaymentsList;
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

//    public List<PaymentMethodObjs> getPaymentMethodObjsList ()
//    {
//        return paymentMethodObjsList;
//    }
//
//    public void setPaymentMethodObjsList (List<PaymentMethodObjs> paymentMethodObjsList)
//    {
//        this.paymentMethodObjsList = paymentMethodObjsList;
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
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.PaymentMethod[ systemId=" + systemId + " ]";
    }

}
