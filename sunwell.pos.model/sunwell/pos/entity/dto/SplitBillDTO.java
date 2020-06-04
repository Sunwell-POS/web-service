/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * SplitBillDTO.java
 *
 * Created on Jan 9, 2018, 10:13:52 AM
 */

package sunwell.pos.entity.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SplitBillDTO extends StandardDTO
{
    private SalesInvoiceDTO invoice;
    private List<ProductDTO> products ;
    
    
    /**
     * @return the invoiceId
     */
    public SalesInvoiceDTO getInvoice ()
    {
        return invoice;
    }

    /**
     * @param invoiceId the invoiceId to set
     */
    public void setInvoice (SalesInvoiceDTO _inv)
    {
        this.invoice = _inv;
    }

    /**
     * @return the products
     */
    public List<ProductDTO> getProducts ()
    {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts (List<ProductDTO> products)
    {
        this.products = products;
    }
}
