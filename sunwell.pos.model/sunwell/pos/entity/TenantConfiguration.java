/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantConfiguration.java
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "tenant_configurations")
@NamedQueries({
    @NamedQuery(name = "TenantConfiguration.findAll", query = "SELECT t FROM TenantConfiguration t")
    , @NamedQuery(name = "TenantConfiguration.findBySystemId", query = "SELECT t FROM TenantConfiguration t WHERE t.systemId = :systemId")
    , @NamedQuery(name = "TenantConfiguration.findByCurrency", query = "SELECT t FROM TenantConfiguration t WHERE t.currency = :currency")
    , @NamedQuery(name = "TenantConfiguration.findByFormatCodeInvoice", query = "SELECT t FROM TenantConfiguration t WHERE t.formatCodeInvoice = :formatCodeInvoice")
    , @NamedQuery(name = "TenantConfiguration.findByDefaultMiscChargesValue", query = "SELECT t FROM TenantConfiguration t WHERE t.defaultMiscChargesValue = :defaultMiscChargesValue")
    , @NamedQuery(name = "TenantConfiguration.findByDefaultMiscChargesType", query = "SELECT t FROM TenantConfiguration t WHERE t.defaultMiscChargesType = :defaultMiscChargesType")
    , @NamedQuery(name = "TenantConfiguration.findByDefaultMiscChargesMemo", query = "SELECT t FROM TenantConfiguration t WHERE t.defaultMiscChargesMemo = :defaultMiscChargesMemo")
    , @NamedQuery(name = "TenantConfiguration.findByDefaultPaymentMethodId", query = "SELECT t FROM TenantConfiguration t WHERE t.defaultPaymentMethodId = :defaultPaymentMethodId")
    , @NamedQuery(name = "TenantConfiguration.findByVatInclusive", query = "SELECT t FROM TenantConfiguration t WHERE t.vatInclusive = :vatInclusive")
    , @NamedQuery(name = "TenantConfiguration.findByDefaultVatValue", query = "SELECT t FROM TenantConfiguration t WHERE t.defaultVatValue = :defaultVatValue")
    , @NamedQuery(name = "TenantConfiguration.findByCreatedAt", query = "SELECT t FROM TenantConfiguration t WHERE t.createdAt = :createdAt")
    , @NamedQuery(name = "TenantConfiguration.findByUpdatedAt", query = "SELECT t FROM TenantConfiguration t WHERE t.updatedAt = :updatedAt")
    , @NamedQuery(name = "TenantConfiguration.findBySysCreator", query = "SELECT t FROM TenantConfiguration t WHERE t.sysCreator = :sysCreator")
    , @NamedQuery(name = "TenantConfiguration.findBySysLastupdater", query = "SELECT t FROM TenantConfiguration t WHERE t.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "TenantConfiguration.findBySysDeleted", query = "SELECT t FROM TenantConfiguration t WHERE t.sysDeleted = :sysDeleted")})
public class TenantConfiguration implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @Column(name = "formatCodeInvoice")
    private String formatCodeInvoice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "defaultMiscChargesValue")
    private BigDecimal defaultMiscChargesValue;
    @Basic(optional = false)
    @Column(name = "defaultMiscChargesType")
    private int defaultMiscChargesType;
    @Basic(optional = false)
    @Column(name = "defaultMiscChargesMemo")
    private String defaultMiscChargesMemo;
    @Basic(optional = false)
    @Column(name = "defaultPaymentMethodId")
    private String defaultPaymentMethodId;
    @Basic(optional = false)
    @Column(name = "vat_inclusive")
    private boolean vatInclusive;
    @Basic(optional = false)
    @Column(name = "defaultVatValue")
    private double defaultVatValue;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "sysCreator")
    private String sysCreator;
    @Column(name = "sysLastUpdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
    @JoinColumn(name = "parentId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant parent;

    public TenantConfiguration ()
    {
    }

    public TenantConfiguration (String systemId)
    {
        this.systemId = systemId;
    }

    public TenantConfiguration (String systemId, String currency, String formatCodeInvoice, BigDecimal defaultMiscChargesValue, int defaultMiscChargesType, String defaultMiscChargesMemo, String defaultPaymentMethodId, boolean vatInclusive, double defaultVatValue, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.currency = currency;
        this.formatCodeInvoice = formatCodeInvoice;
        this.defaultMiscChargesValue = defaultMiscChargesValue;
        this.defaultMiscChargesType = defaultMiscChargesType;
        this.defaultMiscChargesMemo = defaultMiscChargesMemo;
        this.defaultPaymentMethodId = defaultPaymentMethodId;
        this.vatInclusive = vatInclusive;
        this.defaultVatValue = defaultVatValue;
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

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getFormatCodeInvoice ()
    {
        return formatCodeInvoice;
    }

    public void setFormatCodeInvoice (String formatCodeInvoice)
    {
        this.formatCodeInvoice = formatCodeInvoice;
    }

    public BigDecimal getDefaultMiscChargesValue ()
    {
        return defaultMiscChargesValue;
    }

    public void setDefaultMiscChargesValue (BigDecimal defaultMiscChargesValue)
    {
        this.defaultMiscChargesValue = defaultMiscChargesValue;
    }

    public int getDefaultMiscChargesType ()
    {
        return defaultMiscChargesType;
    }

    public void setDefaultMiscChargesType (int defaultMiscChargesType)
    {
        this.defaultMiscChargesType = defaultMiscChargesType;
    }

    public String getDefaultMiscChargesMemo ()
    {
        return defaultMiscChargesMemo;
    }

    public void setDefaultMiscChargesMemo (String defaultMiscChargesMemo)
    {
        this.defaultMiscChargesMemo = defaultMiscChargesMemo;
    }

    public String getDefaultPaymentMethodId ()
    {
        return defaultPaymentMethodId;
    }

    public void setDefaultPaymentMethodId (String defaultPaymentMethodId)
    {
        this.defaultPaymentMethodId = defaultPaymentMethodId;
    }

    public boolean getVatInclusive ()
    {
        return vatInclusive;
    }

    public void setVatInclusive (boolean vatInclusive)
    {
        this.vatInclusive = vatInclusive;
    }

    public double getDefaultVatValue ()
    {
        return defaultVatValue;
    }

    public void setDefaultVatValue (double defaultVatValue)
    {
        this.defaultVatValue = defaultVatValue;
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

    public String getSysCreator ()
    {
        return sysCreator;
    }

    public void setSysCreator (String sysCreator)
    {
        this.sysCreator = sysCreator;
    }

    public String getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    public void setSysLastUpdater (String sysLastUpdater)
    {
        this.sysLastUpdater = sysLastUpdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

    public Tenant getParent ()
    {
        return parent;
    }

    public void setParent (Tenant parentId)
    {
        this.parent = parentId;
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
        if (!(object instanceof TenantConfiguration)) {
            return false;
        }
        TenantConfiguration other = (TenantConfiguration) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantConfiguration[ systemId=" + systemId + " ]";
    }

}
