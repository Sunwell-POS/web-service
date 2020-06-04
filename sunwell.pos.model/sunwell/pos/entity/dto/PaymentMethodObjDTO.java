/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * PaymentMethodObjDTO.java
 *
 * Created on Nov 7, 2017, 11:28:30 AM
 */

package sunwell.pos.entity.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.PaymentMethod;
import sunwell.pos.entity.PaymentMethodObj;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethodObjDTO extends StandardDTO
{
    
    private String systemId;
    private String name;
    private String memo;
    private Boolean sysbuiltin;
    private Boolean status;
    private Boolean hasDisc;
    private Double discValue;
    private Integer discType;
    private Double minPayment;
    private Double maxPayment;
    private Date dueDate;
    private PaymentMethodDTO parent;
    
    public PaymentMethodObjDTO() {
        
    }
    
    public PaymentMethodObjDTO(PaymentMethodObj _method) {
        setData (_method);
    }
    
    public void setData(PaymentMethodObj _method) {
        systemId = _method.getSystemId ();
        name = _method.getName ();
        sysbuiltin = _method.getSysbuiltin ();
        status = _method.getStatus ();
        hasDisc = _method.getHasDisc ();
        discValue = _method.getDiscValue ();
        discType = _method.getDiscType ();
        minPayment = _method.getMinPayment ();
        maxPayment = _method.getMaxPayment ();
        dueDate = _method.getDueDate ();
        memo = _method.getMemo ();
        parent = new PaymentMethodDTO(_method.getParent ());
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
    public Boolean getSysbuiltin ()
    {
        return sysbuiltin;
    }

    /**
     * @param sysbuiltin the sysbuiltin to set
     */
    public void setSysbuiltin (Boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
    }

    /**
     * @return the status
     */
    public Boolean getStatus ()
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

    /**
     * @return the hasDisc
     */
    public Boolean getHasDisc ()
    {
        return hasDisc;
    }

    /**
     * @param hasDisc the hasDisc to set
     */
    public void setHasDisc (Boolean hasDisc)
    {
        this.hasDisc = hasDisc;
    }

    /**
     * @return the discValue
     */
    public Double getDiscValue ()
    {
        return discValue;
    }

    /**
     * @param discValue the discValue to set
     */
    public void setDiscValue (Double discValue)
    {
        this.discValue = discValue;
    }

    /**
     * @return the discType
     */
    public Integer getDiscType ()
    {
        return discType;
    }

    /**
     * @param discType the discType to set
     */
    public void setDiscType (Integer discType)
    {
        this.discType = discType;
    }

    /**
     * @return the minPayment
     */
    public Double getMinPayment ()
    {
        return minPayment;
    }

    /**
     * @param minPayment the minPayment to set
     */
    public void setMinPayment (Double minPayment)
    {
        this.minPayment = minPayment;
    }

    /**
     * @return the maxPayment
     */
    public Double getMaxPayment ()
    {
        return maxPayment;
    }

    /**
     * @param maxPayment the maxPayment to set
     */
    public void setMaxPayment (Double maxPayment)
    {
        this.maxPayment = maxPayment;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate ()
    {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate (Date dueDate)
    {
        this.dueDate = dueDate;
    }

    /**
     * @return the parent
     */
    public PaymentMethodDTO getParent ()
    {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent (PaymentMethodDTO parent)
    {
        this.parent = parent;
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
