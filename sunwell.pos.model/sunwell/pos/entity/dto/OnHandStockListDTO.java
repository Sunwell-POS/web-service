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
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OnHandStockListDTO extends StandardDTO
{
    
    private List<OnHandStockDTO> listOnHandStock;
    
    public OnHandStockListDTO() {
        
    }
    
    public OnHandStockListDTO(List<OnHandStock> _onhands) {
        setData (_onhands);
    }
    
    public void setData(List<OnHandStock> _onhands) {
        if(_onhands != null && _onhands.size () > 0) {
            listOnHandStock = new LinkedList<> ();
            for (OnHandStock ohs : _onhands) {
                listOnHandStock.add (new OnHandStockDTO (ohs));
            }
        }
        else
            listOnHandStock = null;
    }

    /**
     * @return the listCategory
     */
    public List<OnHandStockDTO> getListOnHandStock ()
    {
        return listOnHandStock;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListProduct (List<OnHandStockDTO> listOnHand)
    {
        this.listOnHandStock = listOnHand;
    }
}
