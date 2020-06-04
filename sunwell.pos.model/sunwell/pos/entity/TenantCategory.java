/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantCategory.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "tenant_categories")
@NamedQueries({
    @NamedQuery(name = "TenantCategory.findAll", query = "SELECT t FROM TenantCategory t")
    , @NamedQuery(name = "TenantCategory.findBySystemId", query = "SELECT t FROM TenantCategory t WHERE t.systemId = :systemId")
    , @NamedQuery(name = "TenantCategory.findByName", query = "SELECT t FROM TenantCategory t WHERE t.name = :name")
    , @NamedQuery(name = "TenantCategory.findByCreatedAt", query = "SELECT t FROM TenantCategory t WHERE t.createdAt = :createdAt")
    , @NamedQuery(name = "TenantCategory.findByUpdatedAt", query = "SELECT t FROM TenantCategory t WHERE t.updatedAt = :updatedAt")
    , @NamedQuery(name = "TenantCategory.findBySyscreator", query = "SELECT t FROM TenantCategory t WHERE t.sysCreator = :sysCreator")
    , @NamedQuery(name = "TenantCategory.findBySysLastUpdater", query = "SELECT t FROM TenantCategory t WHERE t.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "TenantCategory.findBySysDeleted", query = "SELECT t FROM TenantCategory t WHERE t.sysDeleted = :sysDeleted")})
@XmlRootElement
public class TenantCategory implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "syscreator")
    private String sysCreator;
    @Column(name = "syslastupdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
//    @OneToMany(mappedBy = "tenantcategoryId")
//    private List<Tenant> tenantsList;

    public TenantCategory ()
    {
    }

    public TenantCategory (String systemId)
    {
        this.systemId = systemId;
    }

    public TenantCategory (String systemId, String name, boolean sysdeleted)
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

    public void setSysCreator (String syscreator)
    {
        this.sysCreator = syscreator;
    }

    public String getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    public void setSysLastUpdater (String syslastupdater)
    {
        this.sysLastUpdater = syslastupdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

//    public List<Tenant> getTenantsList ()
//    {
//        return tenantsList;
//    }
//
//    public void setTenantsList (List<Tenant> tenantsList)
//    {
//        this.tenantsList = tenantsList;
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
        if (!(object instanceof TenantCategory)) {
            return false;
        }
        TenantCategory other = (TenantCategory) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantCategory[ systemId=" + systemId + " ]";
    }

}
