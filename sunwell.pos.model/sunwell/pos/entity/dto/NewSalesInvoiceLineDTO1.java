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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.SalesInvoiceLine;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewSalesInvoiceLineDTO1 extends StandardDTO 
{
    private String invoiceId;
    private Double price;
    private String metric;
    private Boolean hasdiscount;
    private Double discValue;
    private Integer discType;
    private Double qty;
    private ProductDTO product;
    
    
    public NewSalesInvoiceLineDTO1 ()
    {
    }

    public NewSalesInvoiceLineDTO1 (SalesInvoiceLine _line)
    {
        setData (_line);
    }

    public void setData(SalesInvoiceLine _line) {
        invoiceId = _line.getSystemId ();
        price = _line.getPrice ();
        metric = _line.getMetric ();
        hasdiscount = _line.getHasdiscount ();
        discValue = _line.getDiscValue ();
        discType = _line.getDiscType ();
        qty = _line.getQty ();
        product = new ProductDTO(_line.getProduct ());
    }

    /**
     * @return the invoiceId
     */
    public String getInvoiceId ()
    {
        return invoiceId;
    }

    /**
     * @param systemId the invoiceId to set
     */
    public void setInvoiceId (String systemId)
    {
        this.invoiceId = systemId;
    }

    /**
     * @return the price
     */
    public Double getPrice ()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice (Double price)
    {
        this.price = price;
    }

    /**
     * @return the metric
     */
    public String getMetric ()
    {
        return metric;
    }

    /**
     * @param metric the metric to set
     */
    public void setMetric (String metric)
    {
        this.metric = metric;
    }

    /**
     * @return the hasdiscount
     */
    public Boolean getHasdiscount ()
    {
        return hasdiscount;
    }

    /**
     * @param hasdiscount the hasdiscount to set
     */
    public void setHasdiscount (Boolean hasdiscount)
    {
        this.hasdiscount = hasdiscount;
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
     * @return the qty
     */
    public Double getQty ()
    {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty (Double qty)
    {
        this.qty = qty;
    }

    /**
     * @return the product
     */
    public ProductDTO getProduct ()
    {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct (ProductDTO product)
    {
        this.product = product;
    }
    
}
