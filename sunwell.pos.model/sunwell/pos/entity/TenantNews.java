/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantNews.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
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
@Table(name = "tenant_news")
@NamedQueries({
    @NamedQuery(name = "TenantNews.findAll", query = "SELECT t FROM TenantNews t")
    , @NamedQuery(name = "TenantNews.findBySystemId", query = "SELECT t FROM TenantNews t WHERE t.systemId = :systemId")
    , @NamedQuery(name = "TenantNews.findByTitle", query = "SELECT t FROM TenantNews t WHERE t.title = :title")
    , @NamedQuery(name = "TenantNews.findByContent", query = "SELECT t FROM TenantNews t WHERE t.content = :content")
    , @NamedQuery(name = "TenantNews.findByCreatedAt", query = "SELECT t FROM TenantNews t WHERE t.createdAt = :createdAt")
    , @NamedQuery(name = "TenantNews.findByUpdatedAt", query = "SELECT t FROM TenantNews t WHERE t.updatedAt = :updatedAt")
    , @NamedQuery(name = "TenantNews.findBySyscreator", query = "SELECT t FROM TenantNews t WHERE t.sysCreator = :sysCreator")
    , @NamedQuery(name = "TenantNews.findBySyslastupdater", query = "SELECT t FROM TenantNews t WHERE t.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "TenantNews.findBySysdeleted", query = "SELECT t FROM TenantNews t WHERE t.sysDeleted = :sysDeleted")})
public class TenantNews implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
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
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;

    public TenantNews ()
    {
    }

    public TenantNews (String systemId)
    {
        this.systemId = systemId;
    }

    public TenantNews (String systemId, String title, String content, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.title = title;
        this.content = content;
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

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
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
        if (!(object instanceof TenantNews)) {
            return false;
        }
        TenantNews other = (TenantNews) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantNews[ systemId=" + systemId + " ]";
    }

}
