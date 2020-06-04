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
import javax.ws.rs.BeanParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OnHandStockDTO extends StandardDTO
{
    @BeanParam
    private ProductDTO product;
    
    @BeanParam
    private WarehouseDTO warehouse;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date lastInputDate;
    private Double qty;
    
    public OnHandStockDTO() {
        
    }
    
    public OnHandStockDTO(OnHandStock _ohs) {
        setData (_ohs);
    }
    
    public void setData(OnHandStock _ohs) {
        this.product = new ProductDTO (_ohs.getProduct ());
        this.warehouse = new WarehouseDTO(_ohs.getWarehouse ());
        this.qty = _ohs.getQty ();
        this.lastInputDate = _ohs.getLastInputDate ();
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

    /**
     * @return the warehouse
     */
    public WarehouseDTO getWarehouse ()
    {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse (WarehouseDTO warehouse)
    {
        this.warehouse = warehouse;
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
     * @return the lastInputDate
     */
    public Date getLastInputDate ()
    {
        return lastInputDate;
    }

    /**
     * @param lastInputDate the lastInputDate to set
     */
    public void setLastInputDate (Date lastInputDate)
    {
        this.lastInputDate = lastInputDate;
    }
    
}
