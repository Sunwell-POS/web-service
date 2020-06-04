/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantContact.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
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
@Table(name = "tenant_contacts")
//@NamedQueries({
//    @NamedQuery(name = "TenantContact.findAll", query = "SELECT t FROM TenantContact t")
//    , @NamedQuery(name = "TenantContact.findBySystemId", query = "SELECT t FROM TenantContact t WHERE t.systemId = :systemId")
//    , @NamedQuery(name = "TenantContact.findByName", query = "SELECT t FROM TenantContact t WHERE t.name = :name")
//    , @NamedQuery(name = "TenantContact.findByNickname", query = "SELECT t FROM TenantContact t WHERE t.nickname = :nickname")
//    , @NamedQuery(name = "TenantContact.findByEmail", query = "SELECT t FROM TenantContact t WHERE t.email = :email")
//    , @NamedQuery(name = "TenantContact.findByAddress", query = "SELECT t FROM TenantContact t WHERE t.address = :address")
//    , @NamedQuery(name = "TenantContact.findByPhone", query = "SELECT t FROM TenantContact t WHERE t.phone = :phone")
//    , @NamedQuery(name = "TenantContact.findByMemo", query = "SELECT t FROM TenantContact t WHERE t.memo = :memo")
//    , @NamedQuery(name = "TenantContact.findByPhoto", query = "SELECT t FROM TenantContact t WHERE t.photo = :photo")
//    , @NamedQuery(name = "TenantContact.findByCreatedAt", query = "SELECT t FROM TenantContact t WHERE t.createdAt = :createdAt")
//    , @NamedQuery(name = "TenantContact.findByUpdatedAt", query = "SELECT t FROM TenantContact t WHERE t.updatedAt = :updatedAt")
//    , @NamedQuery(name = "TenantContact.findBySyscreator", query = "SELECT t FROM TenantContact t WHERE t.sysCreator = :sysCreator")
//    , @NamedQuery(name = "TenantContact.findBySyslastupdater", query = "SELECT t FROM TenantContact t WHERE t.sysLastUpdater = :sysLastUpdater")
//    , @NamedQuery(name = "TenantContact.findBySysdeleted", query = "SELECT t FROM TenantContact t WHERE t.sysDeleted = :sysDeleted")})
public class TenantContact implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "memo")
    private String memo;
    @Column(name = "photo")
    private String photo;
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

    public TenantContact ()
    {
    }

    public TenantContact (String systemId)
    {
        this.systemId = systemId;
    }

    public TenantContact (String systemId, boolean sysdeleted)
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

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getNickName ()
    {
        return nickName;
    }

    public void setNickName (String nickname)
    {
        this.nickName = nickname;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
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
        if (!(object instanceof TenantContact)) {
            return false;
        }
        TenantContact other = (TenantContact) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantContact[ systemId=" + systemId + " ]";
    }

}
