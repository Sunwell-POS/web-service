/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * AccessRightDTO.java
 *
 * Created on Oct 30, 2017, 12:03:45 PM
 */

package sunwell.pos.entity.dto;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.UGAccessRight;
import sunwell.pos.entity.UGTaskType;
import sunwell.pos.entity.UserGroup;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessRightDTO extends StandardDTO
{   
    private Integer systemId;
    private String taskName;
    private String taskDisplayName;
    private String default1;
    private Boolean status;
    
    public AccessRightDTO() {
        
    }
    
    public AccessRightDTO(UGAccessRight _ac) {
        setData (_ac);
    }
    
    public void setData(UGAccessRight _ac) {
        systemId = _ac.getUGTaskTypes ().getSystemId ();
        taskName = _ac.getUGTaskTypes ().getTaskName ();
        taskDisplayName = _ac.getUGTaskTypes ().getTaskDisplayName ();
        default1 = _ac.getUGTaskTypes ().getDefault1 ();
        status = _ac.getStatus ();
    }
    
    /**
     * @return the systemId
     */
    public Integer getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (Integer systemId)
    {
        this.systemId = systemId;
    }

    /**
     * @return the taskName
     */
    public String getTaskName ()
    {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName (String taskName)
    {
        this.taskName = taskName;
    }

    /**
     * @return the taskDisplayName
     */
    public String getTaskDisplayName ()
    {
        return taskDisplayName;
    }

    /**
     * @param taskDisplayName the taskDisplayName to set
     */
    public void setTaskDisplayName (String taskDisplayName)
    {
        this.taskDisplayName = taskDisplayName;
    }

    /**
     * @return the default1
     */
    public String getDefault1 ()
    {
        return default1;
    }

    /**
     * @param default1 the default1 to set
     */
    public void setDefault1 (String default1)
    {
        this.default1 = default1;
    }

    /**
     * @return the status
     */
    public Boolean isStatus ()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus (Boolean status)
    {
        this.status = status;
    }
  
}
