/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * PaymentMethodDTO.java
 *
 * Created on Nov 6, 2017, 3:38:19 PM
 */

package sunwell.pos.entity.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.PaymentMethod;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethodDTO extends StandardDTO
{
    private String systemId;
    private String name;
    private String memo;
    
    public PaymentMethodDTO() {
        
    }
    
    public PaymentMethodDTO(PaymentMethod _method) {
        setData(_method);
    }
    
    public void setData(PaymentMethod _method) {
        systemId = _method.getSystemId ();
        name = _method.getName ();
        memo = _method.getMemo ();
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
