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
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethodObjListDTO extends StandardDTO
{
    
    private List<PaymentMethodObjDTO> listPaymentMethod;
    
    public PaymentMethodObjListDTO() {
        
    }
    
    public PaymentMethodObjListDTO(List<PaymentMethodObj> _methods) {
        setData (_methods);
    }
    
    public void setData(List<PaymentMethodObj> _methods) {
        if(_methods != null && _methods.size () > 0) {
            listPaymentMethod = new LinkedList<> ();
            for (PaymentMethodObj _method : _methods) {
                listPaymentMethod.add (new PaymentMethodObjDTO (_method));
            }
        }
        else
            listPaymentMethod = null;
    }

    /**
     * @return the listUser
     */
    public List<PaymentMethodObjDTO> getListPaymentMethod ()
    {
        return listPaymentMethod;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListPaymentMethod (List<PaymentMethodObjDTO> _methods)
    {
        this.listPaymentMethod = _methods;
    }
}
