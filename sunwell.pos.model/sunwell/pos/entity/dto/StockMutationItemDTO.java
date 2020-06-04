/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * OnHandDTO.java
 *
 * Created on Dec 11, 2017, 4:40:01 PM
 */

package sunwell.pos.entity.dto;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.User;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StockMutationItemDTO extends StandardDTO
{    
    private ProductDTO product;
    private Double beginningBalance ;
    private Double inQty;
    private Double outQty;
    
    public StockMutationItemDTO() {
        
    }
    
//    public StockMutationItemDTO(IncomingGood _ic) {
//        setData (_ic);
//    }
//    
//    public void setData(IncomingGood _ic) {
//        systemId = _ic.getSystemId ();
//        
//        // bisa null product dan warehousenya karena ada incoming good summary yanghanya punya inQty saja
//        
//        if(_ic.getProduct () != null)
//            product = new ProductDTO(_ic.getProduct ());
//        
//        if(_ic.getWarehouse () != null)
//            warehouse = new WarehouseDTO(_ic.getWarehouse ());
//        incomingDate = _ic.getIncomingDate ();
//        refType = _ic.getRefType ();
//        refId = _ic.getRefId ();
//        memo = _ic.getMemo ();
//        inQty = _ic.getQty ();
//        outQty = _ic.getUnitPrice ();
//    }
    
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

    /**
     * @return the inQty
     */
    public double getInQty ()
    {
        return inQty;
    }

    /**
     * @param qty the inQty to set
     */
    public void setInQty (double qty)
    {
        this.inQty = qty;
    }

    /**
     * @return the outQty
     */
    public double getOutQty ()
    {
        return outQty;
    }

    /**
     * @param unitPrice the outQty to set
     */
    public void setOutQty (double unitPrice)
    {
        this.outQty = unitPrice;
    }
    
    /**
     * @return the beginningBalance
     */
    public double getBeginningBalance ()
    {
        return beginningBalance;
    }

    /**
     * @param beginningBalance the beginningBalance to set
     */
    public void setBeginningBalance (double beginningBalance)
    {
        this.beginningBalance = beginningBalance;
    }
    
    /**
     * @return the beginningBalance
     */
    public double getBalance ()
    {
        return beginningBalance + (inQty - outQty);
    }

    
}
