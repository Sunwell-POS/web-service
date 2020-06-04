/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UsersPK.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Benny
 */
public class UserPK implements Serializable 
{

    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "tenantId")
    private String tenant;

    public UserPK ()
    {
    }

    public UserPK (String systemId, String tenantId)
    {
        this.systemId = systemId;
        this.tenant = tenantId;
    }

    public String getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    public String getTenant ()
    {
        return tenant;
    }

    public void setTenant (String tenantId)
    {
        this.tenant = tenantId;
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
        if (!(object instanceof UserPK)) {
            return false;
        }
        UserPK other = (UserPK) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        if ((this.tenant == null && other.tenant != null) || (this.tenant != null && !this.tenant.equals (other.tenant)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.UsersPK[ systemId=" + systemId + ", tenantId=" + tenant + " ]";
    }

}
