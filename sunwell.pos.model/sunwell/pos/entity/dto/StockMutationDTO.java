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
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StockMutationDTO extends StandardDTO
{
    
    private List<StockMutationItemDTO> listStockMutation;
    
    public StockMutationDTO() {
        
    }
    
    /**
     * @return the listCategory
     */
    public List<StockMutationItemDTO> getListStockMutation ()
    {
        return listStockMutation;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListStockMutation (List<StockMutationItemDTO> _listIC)
    {
        this.listStockMutation = _listIC;
    }
}



