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
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProdCategoryListDTO extends StandardDTO
{
    
    private List<ProdCategoryDTO> listCategory;
    
    public ProdCategoryListDTO() {
        
    }
    
    public ProdCategoryListDTO(List<ProdCategory> _users) {
        setData (_users);
    }
    
    public void setData(List<ProdCategory> _categories) {
        if(_categories != null && _categories.size () > 0) {
            listCategory = new LinkedList<> ();
            for (ProdCategory _category : _categories) {
                listCategory.add (new ProdCategoryDTO (_category));
            }
        }
        else
            listCategory = null;
    }

    /**
     * @return the listUser
     */
    public List<ProdCategoryDTO> getListCategory ()
    {
        return listCategory;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListCategory (List<ProdCategoryDTO> _categories)
    {
        this.listCategory = _categories;
    }
}
