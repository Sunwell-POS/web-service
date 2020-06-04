/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * PaymentDTO.java
 *
 * Created on Nov 6, 2017, 3:35:13 PM
 */

package sunwell.pos.entity.dto;

import sunwell.pos.util.DateTimeAdapter;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.PaymentMethod;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.SalesPayment;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesPaymentDTO extends StandardDTO
{
    
    private String systemId;
    private String cardNumber;
    private String nameCardHolder;
    private Double amount;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date paidDate;
    
    private String memo;
    private SalesInvoiceDTO parent;
    private PaymentMethodDTO paymentMethod;
    
    public SalesPaymentDTO() {
        
    }
    
    public SalesPaymentDTO(SalesPayment _payment) {
        setData (_payment);
    }
    
    public void setData(SalesPayment _payment) {
        setSystemId (_payment.getSystemId ());
        setCardNumber (_payment.getCardNumber ());
        setNameCardHolder (_payment.getNameCardHolder ());
        setAmount (_payment.getAmount ());
        setPaidDate (_payment.getPaidDate ().getTime ());
        setMemo (_payment.getMemo ());
        setParent (new SalesInvoiceDTO(_payment.getParent ()));
        setPaymentMethod (new PaymentMethodDTO(_payment.getPaymentMethod ()));
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
     * @return the cardNumber
     */
    public String getCardNumber ()
    {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber (String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the nameCardHolder
     */
    public String getNameCardHolder ()
    {
        return nameCardHolder;
    }

    /**
     * @param nameCardHolder the nameCardHolder to set
     */
    public void setNameCardHolder (String nameCardHolder)
    {
        this.nameCardHolder = nameCardHolder;
    }

    /**
     * @return the amount
     */
    public Double getAmount ()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount (Double amount)
    {
        this.amount = amount;
    }

    /**
     * @return the paidDate
     */
    public Date getPaidDate ()
    {
        return paidDate;
    }

    /**
     * @param paidDate the paidDate to set
     */
    public void setPaidDate (Date paidDate)
    {
        this.paidDate = paidDate;
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
     * @return the parent
     */
    public SalesInvoiceDTO getParent ()
    {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent (SalesInvoiceDTO parent)
    {
        this.parent = parent;
    }

    /**
     * @return the paymentMethod
     */
    public PaymentMethodDTO getPaymentMethod ()
    {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod (PaymentMethodDTO paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

}
