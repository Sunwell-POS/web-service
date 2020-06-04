/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * SalesInvoice.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@UuidGenerator(name="UUID_salesInvoice")
@Table(name = "sales_invoices")
@NamedQueries({
    @NamedQuery(name = "SalesInvoice.findAll", query = "SELECT s FROM SalesInvoice s")
    , @NamedQuery(name = "SalesInvoice.findBySystemId", query = "SELECT s FROM SalesInvoice s WHERE s.systemId = :systemId")
    , @NamedQuery(name = "SalesInvoice.findByTenant", query = "SELECT s FROM SalesInvoice s WHERE s.tenant = :tenant")
    , @NamedQuery(name = "SalesInvoice.findByPaid", query = "SELECT s FROM SalesInvoice s WHERE s.paid = :paid")
    , @NamedQuery(name = "SalesInvoice.findByVoided", query = "SELECT s FROM SalesInvoice s WHERE s.voided = :voided")
    , @NamedQuery(name = "SalesInvoice.findBySplitCount", query = "SELECT s FROM SalesInvoice s WHERE s.splitCount = :splitCount")
    , @NamedQuery(name = "SalesInvoice.findByName", query = "SELECT s FROM SalesInvoice s WHERE s.name = :name")
    , @NamedQuery(name = "SalesInvoice.findByNoInvoice", query = "SELECT s FROM SalesInvoice s WHERE s.noInvoice = :noInvoice")
    , @NamedQuery(name = "SalesInvoice.findByNoInvoiceDate", query = "SELECT s FROM SalesInvoice s WHERE s.noInvoiceDate = :noInvoiceDate")
    , @NamedQuery(name = "SalesInvoice.findByDiscValue", query = "SELECT s FROM SalesInvoice s WHERE s.discValue = :discValue")
    , @NamedQuery(name = "SalesInvoice.findByDiscType", query = "SELECT s FROM SalesInvoice s WHERE s.discType = :discType")
    , @NamedQuery(name = "SalesInvoice.findByDiscTotal", query = "SELECT s FROM SalesInvoice s WHERE s.discTotal = :discTotal")
    , @NamedQuery(name = "SalesInvoice.findByMiscChargesValue", query = "SELECT s FROM SalesInvoice s WHERE s.miscChargesValue = :miscChargesValue")
    , @NamedQuery(name = "SalesInvoice.findByMiscChargesType", query = "SELECT s FROM SalesInvoice s WHERE s.miscChargesType = :miscChargesType")
    , @NamedQuery(name = "SalesInvoice.findByMiscChargesMemo", query = "SELECT s FROM SalesInvoice s WHERE s.miscChargesMemo = :miscChargesMemo")
    , @NamedQuery(name = "SalesInvoice.findByVoidDate", query = "SELECT s FROM SalesInvoice s WHERE s.voidDate = :voidDate")
    , @NamedQuery(name = "SalesInvoice.findByVat", query = "SELECT s FROM SalesInvoice s WHERE s.vat = :vat")
    , @NamedQuery(name = "SalesInvoice.findByVatInclusive", query = "SELECT s FROM SalesInvoice s WHERE s.vatInclusive = :vatInclusive")
    , @NamedQuery(name = "SalesInvoice.findByCreatedAt", query = "SELECT s FROM SalesInvoice s WHERE s.createdAt = :createdAt")
    , @NamedQuery(name = "SalesInvoice.findByUpdatedAt", query = "SELECT s FROM SalesInvoice s WHERE s.updatedAt = :updatedAt")
    , @NamedQuery(name = "SalesInvoice.findBySysDeleted", query = "SELECT s FROM SalesInvoice s WHERE s.sysDeleted = :sysDeleted")})
public class SalesInvoice implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_salesInvoice")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "paid")
    private Boolean paid = false;
    @Basic(optional = false)
    @Column(name = "voided")
    private Boolean voided = false;
    @Column(name = "splitCount")
    private Integer splitCount;
    @Column(name = "name")
    private String name;
    @Column(name = "noInvoice")
    private String noInvoice;
    @Column(name = "noInvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar noInvoiceDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discValue")
    private Double discValue;
    @Column(name = "discType")
    private Integer discType;
    @Column(name = "discTotal")
    private Double discTotal;
    @Column(name = "miscChargesValue")
    private Double miscChargesValue;
    @Column(name = "miscChargesType")
    private Integer miscChargesType;
    @Column(name = "miscChargesMemo")
    private String miscChargesMemo;
    @Column(name = "voidDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar voidDate;
    @Lob
    @Column(name = "voidMemo")
    private String voidMemo;
    @Column(name = "vat")
    private Double vat;
    @Column(name = "vat_inclusive")
    private Boolean vatInclusive;
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
    private Boolean sysDeleted = false;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
//    private List<SalesPayment> salesPaymentsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<SalesInvoiceLine> salesInvoiceLines;
    @JoinColumn(name = "customerId", referencedColumnName = "systemId")
    @ManyToOne
    private Customer customer;
    @JoinColumn(name = "inputBy", referencedColumnName = "systemId")
    @ManyToOne
    private User inputBy;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User syscreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User syslastupdater;
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;
    @JoinColumn(name = "voidBy", referencedColumnName = "systemId")
    @ManyToOne
    private User voidBy;

    public SalesInvoice ()
    {
    }

    public SalesInvoice (String systemId)
    {
        this.systemId = systemId;
    }

    public SalesInvoice (String systemId, Boolean paid, Boolean voided, Boolean sysdeleted)
    {
        this.systemId = systemId;
        this.paid = paid;
        this.voided = voided;
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

    public Boolean getPaid ()
    {
        return paid;
    }

    public void setPaid (Boolean paid)
    {
        this.paid = paid;
    }

    public Boolean getVoided ()
    {
        return voided;
    }

    public void setVoided (Boolean voided)
    {
        this.voided = voided;
    }

    public Integer getSplitCount ()
    {
        return splitCount;
    }

    public void setSplitCount (Integer splitCount)
    {
        this.splitCount = splitCount;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getNoInvoice ()
    {
        return noInvoice;
    }

    public void setNoInvoice (String noInvoice)
    {
        this.noInvoice = noInvoice;
    }

    public Calendar getNoInvoiceDate ()
    {
        return noInvoiceDate;
    }

    public void setNoInvoiceDate (Calendar noInvoiceDate)
    {
        this.noInvoiceDate = noInvoiceDate;
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

    public Double getDiscTotal ()
    {
        return discTotal;
    }

    public void setDiscTotal (Double discTotal)
    {
        this.discTotal = discTotal;
    }

    public Double getMiscChargesValue ()
    {
        return miscChargesValue;
    }

    public void setMiscChargesValue (Double miscChargesValue)
    {
        this.miscChargesValue = miscChargesValue;
    }

    public Integer getMiscChargesType ()
    {
        return miscChargesType;
    }

    public void setMiscChargesType (Integer miscChargesType)
    {
        this.miscChargesType = miscChargesType;
    }

    public String getMiscChargesMemo ()
    {
        return miscChargesMemo;
    }

    public void setMiscChargesMemo (String miscChargesMemo)
    {
        this.miscChargesMemo = miscChargesMemo;
    }

    public Calendar getVoidDate ()
    {
        return voidDate;
    }

    public void setVoidDate (Calendar voidDate)
    {
        this.voidDate = voidDate;
    }

    public String getVoidMemo ()
    {
        return voidMemo;
    }

    public void setVoidMemo (String voidMemo)
    {
        this.voidMemo = voidMemo;
    }

    public Double getVat ()
    {
        return vat;
    }

    public void setVat (Double vat)
    {
        this.vat = vat;
    }

    public Boolean getVatInclusive ()
    {
        return vatInclusive;
    }

    public void setVatInclusive (Boolean vatInclusive)
    {
        this.vatInclusive = vatInclusive;
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

    public Boolean getSysDeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (Boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

//    public List<SalesPayment> getSalesPaymentsList ()
//    {
//        return salesPaymentsList;
//    }
//
//    public void setSalesPaymentsList (List<SalesPayment> salesPaymentsList)
//    {
//        this.salesPaymentsList = salesPaymentsList;
//    }

    public List<SalesInvoiceLine> getSalesInvoiceLines ()
    {
        return salesInvoiceLines;
    }

    public void setSalesInvoiceLines (List<SalesInvoiceLine> salesInvoiceLines)
    {
        this.salesInvoiceLines = salesInvoiceLines;
    }

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer cust)
    {
        this.customer = cust;
    }

    public User getInputBy ()
    {
        return inputBy;
    }

    public void setInputBy (User inputBy)
    {
        this.inputBy = inputBy;
    }

    public User getSyscreator ()
    {
        return syscreator;
    }

    public void setSyscreator (User syscreator)
    {
        this.syscreator = syscreator;
    }

    public User getSyslastupdater ()
    {
        return syslastupdater;
    }

    public void setSyslastupdater (User syslastupdater)
    {
        this.syslastupdater = syslastupdater;
    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenantId)
    {
        this.tenant = tenantId;
    }

    public User getVoidBy ()
    {
        return voidBy;
    }

    public void setVoidBy (User voidBy)
    {
        this.voidBy = voidBy;
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
        if (!(object instanceof SalesInvoice)) {
            return false;
        }
        SalesInvoice other = (SalesInvoice) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.SalesInvoice[ systemId=" + systemId + " ]";
    }

}
