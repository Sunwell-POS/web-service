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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCredDTO extends StandardDTO implements Serializable 
{
    private String systemId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String img;
    private String imgData;
    private Boolean status;
    private String userKey;
    private Boolean active;
    private String rememberToken;
    private String tenantId;
    private TenantDTO tenant;
    private UserGroupDTO userGroup;

    public UserCredDTO ()
    {
    }

    public UserCredDTO (User _usr)
    {
        setData (_usr);
    }
    
    public void setData(User _usr) {
        systemId = _usr.getSystemId ();
        name = _usr.getName ();
        email = _usr.getEmail ();
        password = _usr.getPassword ();
        phone = _usr.getPhone ();
        img = _usr.getImg ();
//        providerName = _usr.getP ();
//        providerId = _usr.getProviderId ();
//        about = _usr.get ();
//        profilePicture = _usr.getProfilePicture ();
        rememberToken = _usr.getRememberToken ();
        tenantId = _usr.getTenant ().getSystemId ();
        tenant = new TenantDTO(_usr.getTenant ());
        if(_usr.getUserGroup () != null)
            setUserGroup (new UserGroupDTO (_usr.getUserGroup ()));
    }

    
    public String getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (String id)
    {
        this.systemId = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phoneNumber)
    {
        this.phone = phoneNumber;
    }
    
    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }
    
    public String getImgData()
    {
        return imgData;
    }
    
    public void setImgData(String _imgData) {
        this.imgData = _imgData;
    }

//    public String getProviderName ()
//    {
//        return providerName;
//    }
//
//    public void setProviderName (String providerName)
//    {
//        this.providerName = providerName;
//    }
//
//    public String getProviderId ()
//    {
//        return providerId;
//    }
//
//    public void setProviderId (String providerId)
//    {
//        this.providerId = providerId;
//    }
//
//    public String getAbout ()
//    {
//        return about;
//    }
//
//    public void setAbout (String about)
//    {
//        this.about = about;
//    }

//    public String getProfilePicture ()
//    {
//        return profilePicture;
//    }
//
//    public void setProfilePicture (String profilePicture)
//    {
//        this.profilePicture = profilePicture;
//    }

    public String getRememberToken ()
    {
        return rememberToken;
    }

    public void setRememberToken (String rememberToken)
    {
        this.rememberToken = rememberToken;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId ()
    {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId (String tenantId)
    {
        this.tenantId = tenantId;
    }
    
    /**
     * @return the tenant
     */
    public TenantDTO getTenant ()
    {
        return tenant;
    }

    /**
     * @param tenant the tenant to set
     */
    public void setTenant (TenantDTO tenant)
    {
        this.tenant = tenant;
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
     * @return the active
     */
    public Boolean getActive ()
    {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive (Boolean active)
    {
        this.active = active;
    }

    /**
     * @return the userKey
     */
    public String getUserKey ()
    {
        return userKey;
    }

    /**
     * @param userKey the userKey to set
     */
    public void setUserKey (String userKey)
    {
        this.userKey = userKey;
    }

    /**
     * @return the userGroup
     */
    public UserGroupDTO getUserGroup ()
    {
        return userGroup;
    }

    /**
     * @param userGroup the userGroup to set
     */
    public void setUserGroup (UserGroupDTO userGroup)
    {
        this.userGroup = userGroup;
    }

//    public Date getCreatedAt ()
//    {
//        return createdAt;
//    }
//
//    public void setCreatedAt (Date createdAt)
//    {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt ()
//    {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt (Date updatedAt)
//    {
//        this.updatedAt = updatedAt;
//    }
//
//    @XmlTransient
//    public List<Address> getAddressesList ()
//    {
//        return addressesList;
//    }
//
//    public void setAddressesList (List<Address> addressesList)
//    {
//        this.addressesList = addressesList;
//    }

    @Override
    public String toString ()
    {
        return "sunwell.stonefire.cred.model.entity.Users[ id=" + systemId + " ]";
    }

    /**
     * @return the userTypeName
     */
//    public String getUserTypeName ()
//    {
//        return userTypeName;
//    }
//
//    /**
//     * @param userTypeName the userTypeName to set
//     */
//    public void setUserTypeName (String userTypeName)
//    {
//        this.userTypeName = userTypeName;
//    }
//
//    /**
//     * @return the userTypeId
//     */
//    public String getUserTypeId ()
//    {
//        return userTypeId;
//    }
//
//    /**
//     * @param userTypeId the userTypeId to set
//     */
//    public void setUserTypeId (String userTypeId)
//    {
//        this.userTypeId = userTypeId;
//    }
}
