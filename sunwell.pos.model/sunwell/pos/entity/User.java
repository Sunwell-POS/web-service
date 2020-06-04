/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * User.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "users")
@UuidGenerator(name="UUID_user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findBySystemId", query = "SELECT u FROM User u WHERE u.systemId = :systemId")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByTenantAndEmail", query = "SELECT u FROM User u WHERE u.email = :email AND u.tenant = :tenant")
    , @NamedQuery(name = "User.findByTenant", query = "SELECT u FROM User u WHERE u.tenant = :tenant")
    , @NamedQuery(name = "User.findByUserKey", query = "SELECT u FROM User u WHERE u.userKey = :userKey")
    , @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active")
    , @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone")
    , @NamedQuery(name = "User.findByRememberToken", query = "SELECT u FROM User u WHERE u.rememberToken = :rememberToken")
    , @NamedQuery(name = "User.findByCreatedAt", query = "SELECT u FROM User u WHERE u.createdAt = :createdAt")
    , @NamedQuery(name = "User.findByUpdatedAt", query = "SELECT u FROM User u WHERE u.updatedAt = :updatedAt")
    , @NamedQuery(name = "User.findBySyscreator", query = "SELECT u FROM User u WHERE u.syscreator = :syscreator")
    , @NamedQuery(name = "User.findBySyslastupdater", query = "SELECT u FROM User u WHERE u.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "User.findBySysdeleted", query = "SELECT u FROM User u WHERE u.sysDeleted = :sysDeleted")})
public class User implements Serializable 
{
    private static final long serialVersionUID = 1L;
//    @EmbeddedId
//    protected UserPK usersPK;
    
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_user")
    private String systemId;
    
//    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "tenantId")
    private Tenant tenant;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "usergroupId", referencedColumnName = "systemId")
    private UserGroup userGroup;
    
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "userKey")
    private String userKey;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "img")
    private String img;
    @Column(name = "remember_token")
    private String rememberToken;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "syscreator")
    private String syscreator;
    @Column(name = "sysLastUpdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysDeleted")
    private boolean sysDeleted;    

    public User ()
    {
    }

//    public User (UserPK usersPK)
//    {
//        this.usersPK = usersPK;
//    }
//
//    public User (UserPK usersPK, String email, boolean active, boolean status, boolean sysDeleted)
//    {
//        this.usersPK = usersPK;
//        this.email = email;
//        this.active = active;
//        this.status = status;
//        this.sysDeleted = sysDeleted;
//    }
//
//    public User (String systemId, String tenantId)
//    {
//        this.usersPK = new UserPK (systemId, tenantId);
//    }
//
//    public UserPK getUserPK ()
//    {
//        return usersPK;
//    }
//
//    public void setUserPK (UserPK usersPK)
//    {
//        this.usersPK = usersPK;
//    }
    
    /**
     * @return the systemId
     */
    public String getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }
    
    public String getImg ()
    {
        return img;
    }

    public void setImg (String _img)
    {
        this.img = img;
    }

    public String getUserKey ()
    {
        return userKey;
    }

    public void setUserKey (String userKey)
    {
        this.userKey = userKey;
    }

    public boolean getActive ()
    {
        return active;
    }

    public void setActive (boolean active)
    {
        this.active = active;
    }

    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getRememberToken ()
    {
        return rememberToken;
    }

    public void setRememberToken (String rememberToken)
    {
        this.rememberToken = rememberToken;
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

    public String getSyscreator ()
    {
        return syscreator;
    }

    public void setSyscreator (String syscreator)
    {
        this.syscreator = syscreator;
    }

    public String getSyslastupdater ()
    {
        return sysLastUpdater;
    }

    public void setSyslastupdater (String sysLastUpdater)
    {
        this.sysLastUpdater = sysLastUpdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysdeleted (boolean sysDeleted)
    {
        this.sysDeleted = sysDeleted;
    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenants)
    {
        this.tenant = tenants;
    }

    public UserGroup getUserGroup ()
    {
        return userGroup;
    }

    public void setUserGroup (UserGroup usergroupId)
    {
        this.userGroup = usergroupId;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (systemId != null ? systemId.hashCode () : 0);
        hash += (tenant != null ? tenant.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        if ((this.tenant == null && other.tenant != null) || (this.tenant != null && !this.tenant.equals (other.tenant)))
            return false;
        return true;
    }

//    @Override
//    public String toString ()
//    {
//        return "sunwell.pos.entity.User[ usersPK=" + usersPK + " ]";
//    }

}
