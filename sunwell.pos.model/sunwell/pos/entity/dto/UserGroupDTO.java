/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserGroupDTO.java
 *
 * Created on Oct 30, 2017, 12:01:06 PM
 */

package sunwell.pos.entity.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.UGAccessRight;
import sunwell.pos.entity.UserGroup;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserGroupDTO extends StandardDTO
{
    
    private String systemId;
    private String name;
    private boolean sysbuiltin;
    private String memo;
    private List<AccessRightDTO> accessRights ;
    
    public UserGroupDTO() {
        
    }
    
    public UserGroupDTO(UserGroup _ug) {
        setData (_ug);
    }
    
    public void setData(UserGroup _ug) {
        systemId = _ug.getSystemId ();
        name = _ug.getName ();
        sysbuiltin = _ug.getSysbuiltin ();
        memo = _ug.getMemo ();
        if(_ug.getUGAccessRights () != null && _ug.getUGAccessRights ().size () > 0) {
            setAccessRights (new LinkedList<> ());
            for (UGAccessRight accessRight : _ug.getUGAccessRights ()) {
                getAccessRights ().add (new AccessRightDTO (accessRight));
            }
        }
    }

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

    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
    }

    /**
     * @return the sysbuiltin
     */
    public boolean isSysbuiltin ()
    {
        return sysbuiltin;
    }

    /**
     * @param sysbuiltin the sysbuiltin to set
     */
    public void setSysbuiltin (boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
    }

    /**
     * @return the memo
     */
    public String getMemo ()
    {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    /**
     * @return the accessRights
     */
    public List<AccessRightDTO> getAccessRights ()
    {
        return accessRights;
    }

    /**
     * @param accessRights the accessRights to set
     */
    public void setAccessRights (List<AccessRightDTO> accessRights)
    {
        this.accessRights = accessRights;
    }
       
}
