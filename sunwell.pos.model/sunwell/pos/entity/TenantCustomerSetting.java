/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantCustomerSetting.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(TenantCustomerSettingPK.class)
@Table(name = "tenant_customer_settings")
@NamedQueries({
    @NamedQuery(name = "TenantCustomerSetting.findAll", query = "SELECT t FROM TenantCustomerSetting t")
    , @NamedQuery(name = "TenantCustomerSetting.findByCustomer", query = "SELECT t FROM TenantCustomerSetting t WHERE t.customer = :customer")
    , @NamedQuery(name = "TenantCustomerSetting.findByTenant", query = "SELECT t FROM TenantCustomerSetting t WHERE t.tenant = :tenant")
    , @NamedQuery(name = "TenantCustomerSetting.findByStatus", query = "SELECT t FROM TenantCustomerSetting t WHERE t.status = :status")
    , @NamedQuery(name = "TenantCustomerSetting.findBySysbuiltin", query = "SELECT t FROM TenantCustomerSetting t WHERE t.sysbuiltin = :sysbuiltin")
    , @NamedQuery(name = "TenantCustomerSetting.findByCreatedAt", query = "SELECT t FROM TenantCustomerSetting t WHERE t.createdAt = :createdAt")
    , @NamedQuery(name = "TenantCustomerSetting.findByUpdatedAt", query = "SELECT t FROM TenantCustomerSetting t WHERE t.updatedAt = :updatedAt")
    , @NamedQuery(name = "TenantCustomerSetting.findBySysdeleted", query = "SELECT t FROM TenantCustomerSetting t WHERE t.sysDeleted = :sysDeleted")})
public class TenantCustomerSetting implements Serializable 
{

    private static final long serialVersionUID = 1L;
//    @EmbeddedId
//    protected TenantCustomerSettingPK tenantCustomerSettingsPK;
    
    @Id
    @JoinColumn(name = "customerId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Customer customer;
    
    @Id
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;
        
    @Basic(optional = false)
    @Column(name = "status")
    private Boolean status = false;
    @Basic(optional = false)
    @Column(name = "sysbuiltin")
    private Boolean sysbuiltin = true;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private Boolean sysDeleted = false;
        
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
    
    

    public TenantCustomerSetting ()
    {
    }

//    public TenantCustomerSetting (TenantCustomerSettingPK tenantCustomerSettingsPK)
//    {
//        this.tenantCustomerSettingsPK = tenantCustomerSettingsPK;
//    }

    public TenantCustomerSetting (TenantCustomerSettingPK tenantCustomerSettingsPK, boolean status, boolean sysbuiltin, boolean sysdeleted)
    {
//        this.tenantCustomerSettingsPK = tenantCustomerSettingsPK;
        this.status = status;
        this.sysbuiltin = sysbuiltin;
        this.sysDeleted = sysdeleted;
    }

//    public TenantCustomerSetting (String customerId, String tenantId)
//    {
//        this.tenantCustomerSettingsPK = new TenantCustomerSettingPK (customerId, tenantId);
//    }
//
//    public TenantCustomerSettingPK getTenantCustomerSettingPK ()
//    {
//        return tenantCustomerSettingsPK;
//    }
//
//    public void setTenantCustomerSettingPK (TenantCustomerSettingPK tenantCustomerSettingsPK)
//    {
//        this.tenantCustomerSettingsPK = tenantCustomerSettingsPK;
//    }

    public Boolean getStatus ()
    {
        return status;
    }

    public void setStatus (Boolean status)
    {
        this.status = status;
    }

    public Boolean getSysbuiltin ()
    {
        return sysbuiltin;
    }

    public void setSysbuiltin (boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
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

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer customers)
    {
        this.customer = customers;
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

    public void setTenant (Tenant tenants)
    {
        this.tenant = tenants;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (customer != null ? customer.hashCode () : 0);
        hash += (tenant != null ? tenant.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenantCustomerSetting)) {
            return false;
        }
        TenantCustomerSetting other = (TenantCustomerSetting) object;
        if ((this.customer == null && other.customer != null) || (this.customer != null && !this.customer.equals (other.customer)))
            return false;
        if ((this.tenant == null && other.tenant != null) || (this.tenant != null && !this.tenant.equals (other.tenant)))
            return false;
        return true;
    }

//    @Override
//    public String toString ()
//    {
//        return "sunwell.pos.entity.TenantCustomerSetting[ tenantCustomerSettingsPK=" + tenantCustomerSettingsPK + " ]";
//    }

}
