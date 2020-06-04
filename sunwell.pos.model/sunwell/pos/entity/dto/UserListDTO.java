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
public class UserListDTO extends StandardDTO
{
    
    private List<UserCredDTO> listUser;
    
    public UserListDTO() {
        
    }
    
    public UserListDTO(List<User> _users) {
        setData (_users);
    }
    
    public void setData(List<User> _users) {
        if(_users != null && _users.size () > 0) {
            listUser = new LinkedList<> ();
            for (User _user : _users) {
                listUser.add (new UserCredDTO (_user));
            }
        }
        else
            listUser = null;
    }

    /**
     * @return the listUser
     */
    public List<UserCredDTO> getListUser ()
    {
        return listUser;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUser (List<UserCredDTO> listUser)
    {
        this.listUser = listUser;
    }
}
