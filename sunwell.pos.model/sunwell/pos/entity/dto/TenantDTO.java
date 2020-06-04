/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantDTO.java
 *
 * Created on Oct 16, 2017, 10:04:25 AM
 */

package sunwell.pos.entity.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.Tenant;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TenantDTO extends StandardDTO 
{
    private String systemId;
    private String name;
    private String email;
    private String address;
    private String city;
    private String province;
    private String country;
    private String description;
    private String phone;
    private String memo;
    private String news;
    private String logo;
    
    public TenantDTO ()
    {
    }

    public TenantDTO (Tenant _t)
    {
        setData (_t);
    }

    public void setData(Tenant _t) {
        this.systemId = _t.getSystemId ();
        this.name = _t.getName ();
        this.email = _t.getEmail ();
        this.address = _t.getAddress ();
        this.city = _t.getCity ();
        this.province = _t.getProvince ();
        this.country = _t.getCountry ();
        this.description = _t.getDescription ();
        this.phone = _t.getPhone ();
        this.memo = _t.getMemo ();
        this.news = _t.getNews ();
        this.logo = _t.getLogo ();
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

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phoneNumber)
    {
        this.phone = phoneNumber;
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
     * @return the news
     */
    public String getNews ()
    {
        return news;
    }

    /**
     * @param news the news to set
     */
    public void setNews (String news)
    {
        this.news = news;
    }

    /**
     * @return the logo
     */
    public String getLogo ()
    {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo (String logo)
    {
        this.logo = logo;
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
     * @return the city
     */
    public String getCity ()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity (String city)
    {
        this.city = city;
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
     * @return the country
     */
    public String getCountry ()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry (String country)
    {
        this.country = country;
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
    
    
    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (systemId != null ? systemId.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the systemId fields are not set
        if (!(object instanceof TenantDTO)) {
            return false;
        }
        TenantDTO other = (TenantDTO) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

}
