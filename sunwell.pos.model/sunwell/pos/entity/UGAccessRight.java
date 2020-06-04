/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UGAccessRight.java
 *
 * Created on Oct 13, 2017, 3:43:00 PM
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
@Table(name = "u_g_access_rights")
@IdClass(UGAccessRightPK.class)
//@NamedQueries({
//    @NamedQuery(name = "UGAccessRight.findAll", query = "SELECT u FROM UGAccessRight u")
//    , @NamedQuery(name = "UGAccessRight.findByUsergroupId", query = "SELECT u FROM UGAccessRight u WHERE u.uGAccessRightsPK.usergroupId = :usergroupId")
//    , @NamedQuery(name = "UGAccessRight.findByUgTasktypeId", query = "SELECT u FROM UGAccessRight u WHERE u.uGAccessRightsPK.ugTasktypeId = :ugTasktypeId")
//    , @NamedQuery(name = "UGAccessRight.findByStatus", query = "SELECT u FROM UGAccessRight u WHERE u.status = :status")
//    , @NamedQuery(name = "UGAccessRight.findByCreatedAt", query = "SELECT u FROM UGAccessRight u WHERE u.createdAt = :createdAt")
//    , @NamedQuery(name = "UGAccessRight.findByUpdatedAt", query = "SELECT u FROM UGAccessRight u WHERE u.updatedAt = :updatedAt")
//    , @NamedQuery(name = "UGAccessRight.findBySyscreator", query = "SELECT u FROM UGAccessRight u WHERE u.sysCreator = :sysCreator")
//    , @NamedQuery(name = "UGAccessRight.findBySyslastupdater", query = "SELECT u FROM UGAccessRight u WHERE u.sysLastUpdater = :sysLastUpdater")
//    , @NamedQuery(name = "UGAccessRight.findBySysdeleted", query = "SELECT u FROM UGAccessRight u WHERE u.sysDeleted = :sysDeleted")})
public class UGAccessRight implements Serializable 
{
    
    @Id
    @JoinColumn(name = "ugTasktypeId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private UGTaskType ugTaskType;
    
    @Id
    @JoinColumn(name = "usergroupId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private UserGroup userGroup;

    private static final long serialVersionUID = 1L;
//    @EmbeddedId
//    protected UGAccessRightPK uGAccessRightsPK;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
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
    @Column(name = "sysDeleted")
    private boolean sysDeleted;
    
    public UGAccessRight ()
    {
    }

//    public UGAccessRight (UGAccessRightPK uGAccessRightsPK)
//    {
//        this.uGAccessRightsPK = uGAccessRightsPK;
//    }

    public UGAccessRight (UGAccessRightPK uGAccessRightsPK, boolean status, boolean sysDeleted)
    {
//        this.uGAccessRightsPK = uGAccessRightsPK;
        this.status = status;
        this.sysDeleted = sysDeleted;
    }

//    public UGAccessRight (String usergroupId, int ugTasktypeId)
//    {
//        this.uGAccessRightsPK = new UGAccessRightPK (usergroupId, ugTasktypeId);
//    }
//
//    public UGAccessRightPK getUGAccessRightPK ()
//    {
//        return uGAccessRightsPK;
//    }
//
//    public void setUGAccessRightPK (UGAccessRightPK uGAccessRightsPK)
//    {
//        this.uGAccessRightsPK = uGAccessRightsPK;
//    }

    public boolean getStatus ()
    {
        return status;
    }

    public void setStatus (boolean status)
    {
        this.status = status;
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

    public void setSysDeleted (boolean sysDeleted)
    {
        this.sysDeleted = sysDeleted;
    }

    public UGTaskType getUGTaskTypes ()
    {
        return ugTaskType;
    }

    public void setUGTaskTypes (UGTaskType uGTaskTypes)
    {
        this.ugTaskType = uGTaskTypes;
    }

    public UserGroup getUserGroup ()
    {
        return userGroup;
    }

    public void setUserGroup (UserGroup userGroups)
    {
        this.userGroup = userGroups;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (userGroup != null ? userGroup.hashCode () : 0);
        hash += (ugTaskType != null ? ugTaskType.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UGAccessRight)) {
            return false;
        }
        UGAccessRight other = (UGAccessRight) object;
        if ((this.userGroup == null && other.userGroup != null) || (this.userGroup != null && !this.userGroup.equals (other.userGroup)))
            return false;
        if ((this.ugTaskType == null && other.ugTaskType != null) || (this.ugTaskType != null && !this.ugTaskType.equals (other.ugTaskType)))
            return false;
        return true;
    }

//    @Override
//    public String toString ()
//    {
//        return "sunwell.pos.entity.UGAccessRight[ uGAccessRightsPK=" + uGAccessRightsPK + " ]";
//    }

}
