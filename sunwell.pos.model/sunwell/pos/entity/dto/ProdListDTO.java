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
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProdListDTO extends StandardDTO
{
    
    private List<ProductDTO> listProduct;
    
    public ProdListDTO() {
        
    }
    
    public ProdListDTO(List<Product> _products) {
        setData (_products);
    }
    
    public void setData(List<Product> _products) {
        if(_products != null && _products.size () > 0) {
            listProduct = new LinkedList<> ();
            for (Product product : _products) {
                listProduct.add (new ProductDTO (product));
            }
        }
        else
            listProduct = null;
    }

    /**
     * @return the listCategory
     */
    public List<ProductDTO> getListProduct ()
    {
        return listProduct;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListProduct (List<ProductDTO> listProduct)
    {
        this.listProduct = listProduct;
    }
}
