/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * SalesPayment.java
 *
 * Created on Oct 13, 2017, 3:43:00 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Calendar;
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
@UuidGenerator(name="UUID_salesPayment")
@Table(name = "sales_payments")
@NamedQueries({
    @NamedQuery(name = "SalesPayment.findAll", query = "SELECT s FROM SalesPayment s")
    , @NamedQuery(name = "SalesPayment.findBySystemId", query = "SELECT s FROM SalesPayment s WHERE s.systemId = :systemId")
    , @NamedQuery(name = "SalesPayment.findByTenant", query = "SELECT s FROM SalesPayment s WHERE s.parent.tenant = :tenant")
    , @NamedQuery(name = "SalesPayment.findByCardNumber", query = "SELECT s FROM SalesPayment s WHERE s.cardNumber = :cardNumber")
    , @NamedQuery(name = "SalesPayment.findByNameCardHolder", query = "SELECT s FROM SalesPayment s WHERE s.nameCardHolder = :nameCardHolder")
    , @NamedQuery(name = "SalesPayment.findByAmount", query = "SELECT s FROM SalesPayment s WHERE s.amount = :amount")
    , @NamedQuery(name = "SalesPayment.findByPaidDate", query = "SELECT s FROM SalesPayment s WHERE s.paidDate = :paidDate")
    , @NamedQuery(name = "SalesPayment.findByMemo", query = "SELECT s FROM SalesPayment s WHERE s.memo = :memo")
    , @NamedQuery(name = "SalesPayment.findByCreatedAt", query = "SELECT s FROM SalesPayment s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "SalesPayment.findByUpdatedAt", query = "SELECT s FROM SalesPayment s WHERE s.updatedAt = :updatedAt")
    , @NamedQuery(name = "SalesPayment.findBySysDeleted", query = "SELECT s FROM SalesPayment s WHERE s.sysDeleted = :sysDeleted")})
public class SalesPayment implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_salesPayment")
    private String systemId;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "nameCardHolder")
    private String nameCardHolder;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paidDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar paidDate;
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
    @JoinColumn(name = "parentId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private SalesInvoice parent;
    @JoinColumn(name = "paymentMethodId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private PaymentMethod paymentMethod;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;

    public SalesPayment ()
    {
    }

    public SalesPayment (String systemId)
    {
        this.systemId = systemId;
    }

    public SalesPayment (String systemId, Boolean sysdeleted)
    {
        this.systemId = systemId;
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

    public String getCardNumber ()
    {
        return cardNumber;
    }

    public void setCardNumber (String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getNameCardHolder ()
    {
        return nameCardHolder;
    }

    public void setNameCardHolder (String nameCardHolder)
    {
        this.nameCardHolder = nameCardHolder;
    }

    public Double getAmount ()
    {
        return amount;
    }

    public void setAmount (Double amount)
    {
        this.amount = amount;
    }

    public Calendar getPaidDate ()
    {
        return paidDate;
    }

    public void setPaidDate (Calendar paidDate)
    {
        this.paidDate = paidDate;
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

    public SalesInvoice getParent ()
    {
        return parent;
    }

    public void setParent (SalesInvoice parentId)
    {
        this.parent = parentId;
    }

    public PaymentMethod getPaymentMethod ()
    {
        return paymentMethod;
    }

    public void setPaymentMethod (PaymentMethod paymentMethodId)
    {
        this.paymentMethod = paymentMethodId;
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
        if (!(object instanceof SalesPayment)) {
            return false;
        }
        SalesPayment other = (SalesPayment) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.SalesPayment[ systemId=" + systemId + " ]";
    }

}
