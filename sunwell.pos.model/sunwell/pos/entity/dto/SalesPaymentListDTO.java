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
import sunwell.pos.entity.PaymentMethodObj;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.SalesPayment;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesPaymentListDTO extends StandardDTO
{
    
    private List<SalesPaymentDTO> listSalesPayment;
    
    public SalesPaymentListDTO() {
        
    }
    
    public SalesPaymentListDTO(List<SalesPayment> _payments) {
        setData (_payments);
    }
    
    public void setData(List<SalesPayment> _payments) {
        if(_payments != null && _payments.size () > 0) {
            listSalesPayment = new LinkedList<> ();
            for (SalesPayment _payment : _payments) {
                listSalesPayment.add (new SalesPaymentDTO (_payment));
            }
        }
        else
            listSalesPayment = null;
    }

    /**
     * @return the listUser
     */
    public List<SalesPaymentDTO> getListSalesPayment ()
    {
        return listSalesPayment;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListPaymentMethods (List<SalesPaymentDTO> _payments)
    {
        this.listSalesPayment = _payments;
    }
}
