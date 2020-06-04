/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package sunwell.pos.entity.dto;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesInvoiceListDTO extends StandardDTO
{
    
    private List<SalesInvoiceDTO> listSalesInvoice;
    
    public SalesInvoiceListDTO() {
        
    }
    
    public SalesInvoiceListDTO(List<SalesInvoice> _invoices) {
        setData (_invoices);
    }
    
    public void setData(List<SalesInvoice> _invoices) {
        if(_invoices != null && _invoices.size () > 0) {
            listSalesInvoice = new LinkedList<> ();
            for (SalesInvoice _inv : _invoices) {
                listSalesInvoice.add (new SalesInvoiceDTO (_inv));
            }
        }
        else
            listSalesInvoice = null;
    }

    /**
     * @return the listUser
     */
    public List<SalesInvoiceDTO> getListSalesInvoice ()
    {
        return listSalesInvoice;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListSalesInvoice (List<SalesInvoiceDTO> _invoices)
    {
        this.listSalesInvoice = _invoices;
    }
}
