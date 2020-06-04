/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserGroup.java
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
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "user_groups")
@UuidGenerator(name="UUID_user_group")
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u")
    , @NamedQuery(name = "UserGroup.findBySystemId", query = "SELECT u FROM UserGroup u WHERE u.systemId = :systemId")
    , @NamedQuery(name = "UserGroup.findByTenant", query = "SELECT u FROM UserGroup u WHERE u.tenant = :tenant")
    , @NamedQuery(name = "UserGroup.findByName", query = "SELECT u FROM UserGroup u WHERE u.name = :name")
    , @NamedQuery(name = "UserGroup.findBySysbuiltin", query = "SELECT u FROM UserGroup u WHERE u.sysbuiltin = :sysbuiltin")
    , @NamedQuery(name = "UserGroup.findByMemo", query = "SELECT u FROM UserGroup u WHERE u.memo = :memo")
    , @NamedQuery(name = "UserGroup.findByCreatedAt", query = "SELECT u FROM UserGroup u WHERE u.createdAt = :createdAt")
    , @NamedQuery(name = "UserGroup.findByUpdatedAt", query = "SELECT u FROM UserGroup u WHERE u.updatedAt = :updatedAt")
    , @NamedQuery(name = "UserGroup.findBySyscreator", query = "SELECT u FROM UserGroup u WHERE u.sysCreator = :sysCreator")
    , @NamedQuery(name = "UserGroup.findBySysLastUpdater", query = "SELECT u FROM UserGroup u WHERE u.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "UserGroup.findBySysDeleted", query = "SELECT u FROM UserGroup u WHERE u.sysDeleted = :sysDeleted")})
@XmlRootElement
public class UserGroup implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_user_group")
    private String systemId;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "sysbuiltin")
    private boolean sysbuiltin;
    @Column(name = "memo")
    private String memo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "syscreator")
    private String sysCreator;
    @Column(name = "sysLastUpdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysDeleted")
    private boolean sysDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup")
    private List<UGAccessRight> accessRights;
    @JoinColumn(name = "recOwner", referencedColumnName = "systemId")
    @ManyToOne
    private Tenant tenant;

    public UserGroup ()
    {
    }

    public UserGroup (String systemId)
    {
        this.systemId = systemId;
    }

    public UserGroup (String systemId, String name, boolean sysbuiltin, boolean sysDeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.sysbuiltin = sysbuiltin;
        this.sysDeleted = sysDeleted;
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

    public void setSysbuiltin (boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
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

    public void setSysLastUpdater (String sysLastUpdater)
    {
        this.sysLastUpdater = sysLastUpdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysDeleted)
    {
        this.sysDeleted = sysDeleted;
    }

    public List<UGAccessRight> getUGAccessRights ()
    {
        return accessRights;
    }

    public void setUGAccessRights (List<UGAccessRight> uGAccessRightsList)
    {
        this.accessRights = uGAccessRightsList;
    }
//
//    public List<User> getUsersList ()
//    {
//        return usersList;
//    }
//
//    public void setUsersList (List<User> usersList)
//    {
//        this.usersList = usersList;
//    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant recOwner)
    {
        this.tenant = recOwner;
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
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.UserGroup[ systemId=" + systemId + " ]";
    }

}
