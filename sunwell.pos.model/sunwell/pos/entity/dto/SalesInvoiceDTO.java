/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantDTO.java
 *
 * Created on Oct 16, 2017, 10:04:25 AM
 */

package sunwell.pos.entity.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.SalesInvoiceLine;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesInvoiceDTO extends StandardDTO 
{
    
    private String systemId;
    
    @QueryParam("paid")    
    private Boolean paid ;
    
    private Boolean voided ;
    private Integer splitCount;
    private String name;
    private String noInvoice; 
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date noInvoiceDate;
    
    private Double discValue;
    private Integer discType;
    private Double discTotal;
    private Double miscChargesValue;
    private Integer miscChargesType;
    private String miscChargesMemo;
    private Date voidDate;
    private String voidMemo;
    private Double vat;
    private Boolean vatInclusive;
    private String description;
    private CustomerDTO customer;
    private List<SalesInvoiceLineDTO> salesInvoiceLines;
    
    public SalesInvoiceDTO ()
    {
    }

    public SalesInvoiceDTO (SalesInvoice _inv)
    {
        setData (_inv);
    }

    public void setData(SalesInvoice _inv) {
        systemId = _inv.getSystemId ();
        paid = _inv.getPaid ();
        voided = _inv.getVoided ();
        splitCount = _inv.getSplitCount ();
        name = _inv.getName ();
        noInvoice = _inv.getNoInvoice ();
        noInvoiceDate = _inv.getNoInvoiceDate () != null ? _inv.getNoInvoiceDate ().getTime () : null;
        discValue = _inv.getDiscValue ();
        discType = _inv.getDiscType ();
        discTotal = _inv.getDiscTotal ();
        miscChargesValue = _inv.getMiscChargesValue ();
        miscChargesType = _inv.getMiscChargesType ();
        miscChargesMemo = _inv.getMiscChargesMemo ();
        voidDate = _inv.getVoidDate () != null ? _inv.getVoidDate ().getTime () : null;
        voidMemo = _inv.getVoidMemo ();
        vat = _inv.getVat ();
        vatInclusive = _inv.getVatInclusive ();
        description = _inv.getDescription ();
        customer = new CustomerDTO(_inv.getCustomer ());
        
        if(_inv.getSalesInvoiceLines () != null && _inv.getSalesInvoiceLines ().size () > 0) {
            setSalesInvoiceLines (new LinkedList<>());
            for (SalesInvoiceLine salesInvoiceLine : _inv.getSalesInvoiceLines ()) {
                System.out.println ("ADDINg SIL");
                getSalesInvoiceLines ().add (new SalesInvoiceLineDTO (salesInvoiceLine));
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
     * @return the paid
     */
    public Boolean getPaid ()
    {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid (Boolean paid)
    {
        this.paid = paid;
    }

    /**
     * @return the voided
     */
    public Boolean getVoided ()
    {
        return voided;
    }

    /**
     * @param voided the voided to set
     */
    public void setVoided (Boolean voided)
    {
        this.voided = voided;
    }

    /**
     * @return the splitCount
     */
    public Integer getSplitCount ()
    {
        return splitCount;
    }

    /**
     * @param splitCount the splitCount to set
     */
    public void setSplitCount (Integer splitCount)
    {
        this.splitCount = splitCount;
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
     * @return the noInvoice
     */
    public String getNoInvoice ()
    {
        return noInvoice;
    }

    /**
     * @param noInvoice the noInvoice to set
     */
    public void setNoInvoice (String noInvoice)
    {
        this.noInvoice = noInvoice;
    }

    /**
     * @return the noInvoiceDate
     */
    public Date getNoInvoiceDate ()
    {
        return noInvoiceDate;
    }

    /**
     * @param noInvoiceDate the noInvoiceDate to set
     */
    public void setNoInvoiceDate (Date noInvoiceDate)
    {
        this.noInvoiceDate = noInvoiceDate;
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
     * @return the discTotal
     */
    public Double getDiscTotal ()
    {
        return discTotal;
    }

    /**
     * @param discTotal the discTotal to set
     */
    public void setDiscTotal (Double discTotal)
    {
        this.discTotal = discTotal;
    }

    /**
     * @return the miscChargesValue
     */
    public Double getMiscChargesValue ()
    {
        return miscChargesValue;
    }

    /**
     * @param miscChargesValue the miscChargesValue to set
     */
    public void setMiscChargesValue (Double miscChargesValue)
    {
        this.miscChargesValue = miscChargesValue;
    }

    /**
     * @return the miscChargesType
     */
    public Integer getMiscChargesType ()
    {
        return miscChargesType;
    }

    /**
     * @param miscChargesType the miscChargesType to set
     */
    public void setMiscChargesType (Integer miscChargesType)
    {
        this.miscChargesType = miscChargesType;
    }

    /**
     * @return the miscChargesMemo
     */
    public String getMiscChargesMemo ()
    {
        return miscChargesMemo;
    }

    /**
     * @param miscChargesMemo the miscChargesMemo to set
     */
    public void setMiscChargesMemo (String miscChargesMemo)
    {
        this.miscChargesMemo = miscChargesMemo;
    }

    /**
     * @return the voidDate
     */
    public Date getVoidDate ()
    {
        return voidDate;
    }

    /**
     * @param voidDate the voidDate to set
     */
    public void setVoidDate (Date voidDate)
    {
        this.voidDate = voidDate;
    }

    /**
     * @return the voidMemo
     */
    public String getVoidMemo ()
    {
        return voidMemo;
    }

    /**
     * @param voidMemo the voidMemo to set
     */
    public void setVoidMemo (String voidMemo)
    {
        this.voidMemo = voidMemo;
    }

    /**
     * @return the vat
     */
    public Double getVat ()
    {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat (Double vat)
    {
        this.vat = vat;
    }

    /**
     * @return the vatInclusive
     */
    public Boolean getVatInclusive ()
    {
        return vatInclusive;
    }

    /**
     * @param vatInclusive the vatInclusive to set
     */
    public void setVatInclusive (Boolean vatInclusive)
    {
        this.vatInclusive = vatInclusive;
    }

    /**
     * @return the description
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * @return the customer
     */
    public CustomerDTO getCustomer ()
    {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer (CustomerDTO customer)
    {
        this.customer = customer;
    }

    /**
     * @return the salesInvoiceLines
     */
    public List<SalesInvoiceLineDTO> getSalesInvoiceLines ()
    {
        return salesInvoiceLines;
    }

    /**
     * @param salesInvoiceLines the salesInvoiceLines to set
     */
    public void setSalesInvoiceLines (List<SalesInvoiceLineDTO> salesInvoiceLines)
    {
        this.salesInvoiceLines = salesInvoiceLines;
    }

}
