/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredDTO.java
 *
 * Created on Oct 16, 2017, 10:03:42 AM
 */

package sunwell.pos.entity.dto;

import aegwyn.core.web.util.Util;
import java.io.Serializable;
import java.util.Date;
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
public class ProdCategoryDTO extends StandardDTO implements Serializable 
{
    private String systemId;
    private String name;
    private String bgColor;
    private Boolean default1;
    
    public ProdCategoryDTO() {
        
    }
    
    public ProdCategoryDTO(ProdCategory _category) {
        setData (_category);
    }
    
    public void setData(ProdCategory _category) {
        systemId = _category.getSystemId ();
        name = _category.getName ();
        bgColor = _category.getBgColor ();
        System.out.println ("_CTGR: " + _category.getDefault1 ());
        default1 = _category.getDefault1 ();
    }


    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
    
    public Boolean isDefault1 ()
    {
        return default1;
    }

    public void setDefault1 (Boolean default1)
    {
        this.default1 = default1;
    }
}
