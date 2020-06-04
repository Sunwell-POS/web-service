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
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.UGAccessRight;
import sunwell.pos.entity.UGTaskType;
import sunwell.pos.entity.UserGroup;
import sunwell.pos.entity.Warehouse;
/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseDTO extends StandardDTO
{   
    @QueryParam("warehouseId")
    private String systemId;
    private String name;
    private String memo;
    
    public WarehouseDTO() {
        
    }
    
    public WarehouseDTO(Warehouse _wr) {
        setData (_wr);
    }
    
    public void setData(Warehouse _wr) {
        this.systemId = _wr.getSystemId ();
        this.name = _wr.getName ();
        this.memo = _wr.getMemo ();
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
}
