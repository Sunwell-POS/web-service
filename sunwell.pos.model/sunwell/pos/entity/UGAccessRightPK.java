/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UGAccessRightsPK.java
 *
 * Created on Oct 13, 2017, 3:43:00 PM
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
@Embeddable 
public class UGAccessRightPK implements Serializable 
{
    private String userGroup;
    private Integer ugTaskType;

    public UGAccessRightPK ()
    {
    }

    public UGAccessRightPK (String usergroupId, int ugTasktypeId)
    {
        this.userGroup = usergroupId;
        this.ugTaskType = ugTasktypeId;
    }

    public String getUserGroup ()
    {
        return userGroup;
    }

    public void setUserGroup (String usergroupId)
    {
        this.userGroup = usergroupId;
    }

    public Integer getUgTaskType ()
    {
        return ugTaskType;
    }

    public void setUgTaskType (Integer ugTasktypeId)
    {
        this.ugTaskType = ugTasktypeId;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (userGroup != null ? userGroup.hashCode () : 0);
        hash += (Integer) ugTaskType;
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UGAccessRightPK)) {
            return false;
        }
        UGAccessRightPK other = (UGAccessRightPK) object;
        if ((this.userGroup == null && other.userGroup != null) || (this.userGroup != null && !this.userGroup.equals (other.userGroup)))
            return false;
        if (this.ugTaskType.equals (other.ugTaskType))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.UGAccessRightsPK[ usergroupId=" + userGroup + ", ugTasktypeId=" + ugTaskType + " ]";
    }

}
