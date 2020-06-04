/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserGroupDTO.java
 *
 * Created on Oct 30, 2017, 12:01:06 PM
 */

package sunwell.pos.entity.dto;

import aegwyn.core.web.util.DateTimeAdapter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.UGAccessRight;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDTO extends StandardDTO
{
    private String systemId;
    private String name;
    private Boolean isMale;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date birthdate;
    
    private String memberNo;
    private String address;
    private String province;
    private String nation;
    private String phone;
    private String email;
    private Double disc;
    private String memo;
    private Boolean sysbuiltin;
   
    
    public CustomerDTO() {
        
    }
    
    public CustomerDTO(Customer _cust) {
        setData(_cust);
    }
    
    public void setData(Customer _cust) {
        systemId = _cust.getSystemId ();
        name = _cust.getName ();
        isMale = _cust.getIsMale ();
        birthdate = _cust.getBirthdate ();
        memberNo = _cust.getMemberNo ();
        address = _cust.getAddress ();
        province = _cust.getProvince ();
        nation = _cust.getNation ();
        phone = _cust.getPhone ();
        email = _cust.getEmail ();
        disc = _cust.getDisc ();
        memo = _cust.getMemo();
        sysbuiltin = _cust.getSysbuiltin ();
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
     * @return the isMale
     */
    public Boolean getIsMale ()
    {
        return isMale;
    }

    /**
     * @param isMale the isMale to set
     */
    public void setIsMale (Boolean isMale)
    {
        this.isMale = isMale;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate ()
    {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate (Date birthdate)
    {
        this.birthdate = birthdate;
    }

    /**
     * @return the memberNo
     */
    public String getMemberNo ()
    {
        return memberNo;
    }

    /**
     * @param memberNo the memberNo to set
     */
    public void setMemberNo (String memberNo)
    {
        this.memberNo = memberNo;
    }

    /**
     * @return the address
     */
    public String getAddress ()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress (String address)
    {
        this.address = address;
    }

    /**
     * @return the province
     */
    public String getProvince ()
    {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince (String province)
    {
        this.province = province;
    }

    /**
     * @return the nation
     */
    public String getNation ()
    {
        return nation;
    }

    /**
     * @param nation the nation to set
     */
    public void setNation (String nation)
    {
        this.nation = nation;
    }

    /**
     * @return the phone
     */
    public String getPhone ()
    {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail ()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail (String email)
    {
        this.email = email;
    }

    /**
     * @return the disc
     */
    public Double getDisc ()
    {
        return disc;
    }

    /**
     * @param disc the disc to set
     */
    public void setDisc (Double disc)
    {
        this.disc = disc;
    }

    /**
     * @return the memo
     */
    public String getMemo ()
    {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    /**
     * @return the sysbuiltin
     */
    public Boolean getSysbuiltin ()
    {
        return sysbuiltin;
    }

    /**
     * @param sysbuiltin the sysbuiltin to set
     */
    public void setSysbuiltin (Boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
    }

}
