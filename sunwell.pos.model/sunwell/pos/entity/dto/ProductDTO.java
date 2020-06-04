/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProductDTO.java
 *
 * Created on Oct 23, 2017, 11:07:37 AM
 */

package sunwell.pos.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO extends StandardDTO
{
    @QueryParam("productId")
    private String systemId;
    
    private String name;
    private String metric;
    private Double price;
    private Boolean hasStock;
    private Double stockMin;
    private Boolean hasDiscount;
    private Boolean status;
    private String img;
    private String imgData;
    private String barCode;
    private String description;    
    private List<ProdCategoryDTO> categories;
    private String categoriesString;

    public ProductDTO ()
    {
        
    }
    
    public ProductDTO (Product _p)
    {
        setData (_p);
    }
    
    public void setData (Product _p)
    {
        systemId = _p.getSystemId ();
        name =_p.getName ();
        metric = _p.getMetric ();
        price = _p.getPrice ();
        hasStock = _p.getHasStock ();
        stockMin = _p.getStockMin ();
        hasDiscount = _p.getHasDiscount ();
        status = _p.getStatus ();
        img = _p.getImg ();
        barCode = _p.getBarcode ();
        description = _p.getDescription ();
        if(_p.getCategories () != null) {
            System.out.println ("NAME: " + name + " size: " + _p.getCategories ().size ());
            categories = new LinkedList<>();
            for (ProdCategory category : _p.getCategories ()) {
                categories.add (new ProdCategoryDTO (category));
            }
        }
    }
    
    /**
     * @return the systemId
     */
    public String getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
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
     * @return the hasStock
     */
    public Boolean getHasStock ()
    {
        return hasStock;
    }

    /**
     * @param hasStock the hasStock to set
     */
    public void setHasStock (Boolean hasStock)
    {
        this.hasStock = hasStock;
    }

    /**
     * @return the stockMin
     */
    public Double getStockMin ()
    {
        return stockMin;
    }

    /**
     * @param stockMin the stockMin to set
     */
    public void setStockMin (Double stockMin)
    {
        this.stockMin = stockMin;
    }

    /**
     * @return the hasDiscount
     */
    public Boolean getHasDiscount ()
    {
        return hasDiscount;
    }

    /**
     * @param hasDiscount the hasDiscount to set
     */
    public void setHasDiscount (Boolean hasDiscount)
    {
        this.hasDiscount = hasDiscount;
    }

    /**
     * @return the status
     */
    public Boolean getStatus ()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus (Boolean status)
    {
        this.status = status;
    }

    /**
     * @return the img
     */
    public String getImg ()
    {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg (String img)
    {
        this.img = img;
    }

    /**
     * @return the barCode
     */
    public String getBarCode ()
    {
        return barCode;
    }

    /**
     * @param barCode the barCode to set
     */
    public void setBarCode (String barCode)
    {
        this.barCode = barCode;
    }

    /**
     * @return the description
     */
    public String getDescription ()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription (String description)
    {
        this.description = description;
    }

    /**
     * @return the categories
     */
    public List<ProdCategoryDTO> getCategories ()
    {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories (List<ProdCategoryDTO> categories)
    {
        this.categories = categories;
    }
    
    /**
     * @return the categoriesString
     */
    public String getCategoriesString ()
    {
        return categoriesString;
    }

    /**
     * @param categoriesString the categoriesString to set
     */
    public void setCategoriesString (String categoriesString)
    {
        this.categoriesString = categoriesString;
    }
    
    /**
     * @return the imgData
     */
    public String getImgData ()
    {
        return imgData;
    }

    /**
     * @param imgData the imgData to set
     */
    public void setImgData (String imgData)
    {
        this.imgData = imgData;
    }
}
