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
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerListDTO extends StandardDTO
{
    
    private List<CustomerDTO> listCustomer;
    
    public CustomerListDTO() {
        
    }
    
    public CustomerListDTO(List<Customer> _custs) {
        setData (_custs);
    }
    
    public void setData(List<Customer> _custs) {
        if(_custs != null && _custs.size () > 0) {
            listCustomer = new LinkedList<> ();
            for (Customer cust : _custs) {
                listCustomer.add (new CustomerDTO (cust));
            }
        }
        else
            listCustomer = null;
    }

    /**
     * @return the listUser
     */
    public List<CustomerDTO> getListCustomer ()
    {
        return listCustomer;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListCustomer (List<CustomerDTO> _list)
    {
        this.listCustomer = _list;
    }
}
