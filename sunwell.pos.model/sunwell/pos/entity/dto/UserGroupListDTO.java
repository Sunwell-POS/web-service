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
import sunwell.pos.entity.UserGroup;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserGroupListDTO extends StandardDTO
{
    
    private List<UserGroupDTO> listUserGroup;
    
    public UserGroupListDTO() {
        
    }
    
    public UserGroupListDTO(List<UserGroup> _users) {
        setData (_users);
    }
    
    public void setData(List<UserGroup> _groups) {
        if(_groups != null && _groups.size () > 0) {
            listUserGroup = new LinkedList<> ();
            for (UserGroup _ug : _groups) {
                listUserGroup.add (new UserGroupDTO (_ug));
            }
        }
        else
            listUserGroup = null;
    }

    /**
     * @return the listUser
     */
    public List<UserGroupDTO> getListUserGroup ()
    {
        return listUserGroup;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUserGroup (List<UserGroupDTO> _list)
    {
        this.listUserGroup = _list;
    }
}
