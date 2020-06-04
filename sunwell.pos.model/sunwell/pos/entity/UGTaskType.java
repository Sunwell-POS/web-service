/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UGTaskType.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
systemId, taskName, taskDisplayName, default1, created_at, updated_at, syscreator, syslastupdater, sysdeleted
100, Product - Can View, View product, Supervisor, , , , , 0
101, Product - Can Add, Add product, Supervisor, , , , , 0
102, Product - Can Edit, Edit product, Supervisor, , , , , 0
110, Category - Can View, View category, Supervisor, , , , , 0
111, Category - Can Add, Add category, Supervisor, , , , , 0
112, Category - Can Edit, Edit category, Supervisor, , , , , 0
120, Stock - Can View, View stock list, Supervisor, , , , , 0
121, Stock - Can Add Incoming, Add incoming stock, Supervisor, , , , , 0
122, Stock - Can Add Adjustment, Add adjustment stock, Supervisor, , , , , 0
130, Staff - Can View, View staff list, Admin, , , , , 0
131, Staff - Can Add, Add staff, Admin, , , , , 0
132, Staff - Can Edit, Edit staff status, Admin, , , , , 0
140, Staff Type - Can View, View staff type, Admin, , , , , 0
141, Staff Type - Can Edit, Edit staff type, Admin, , , , , 0
150, Customer - Can View, View customer, Other, , , , , 0
151, Customer - Can Add, Add customer, Other, , , , , 0
152, Customer - Can Edit, Edit customer, Supervisor, , , , , 0
153, Customer - Can Delete, Delete customer, Admin, , , , , 0
160, Sell Tx - Can Input, Input invoice, Other, , , , , 0
161, Sell Tx - Can Void, Void invoice, Supervisor, , , , , 0
162, Sell Tx - Can Reprint, Reprint invoice, Supervisor, , , , , 0
170, Payment - Can View, View payment, Supervisor, , , , , 0
171, Payment - Can Add, Add payment, Supervisor, , , , , 0
172, Payment - Can Edit, Edit payment, Supervisor, , , , , 0
400, Report - Can View All, View all report, Admin, , , , , 0
700, Preference - Can View, View preference, Supervisor, , , , , 0
701, Preference - Can Edit, Edit preference, Admin, , , , , 0
*/

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "u_g_task_types")
//@NamedQueries({
//    @NamedQuery(name = "UGTaskType.findAll", query = "SELECT u FROM UGTaskType u")
//    , @NamedQuery(name = "UGTaskType.findBySystemId", query = "SELECT u FROM UGTaskType u WHERE u.systemId = :systemId")
//    , @NamedQuery(name = "UGTaskType.findByTaskName", query = "SELECT u FROM UGTaskType u WHERE u.taskName = :taskName")
//    , @NamedQuery(name = "UGTaskType.findByTaskDisplayName", query = "SELECT u FROM UGTaskType u WHERE u.taskDisplayName = :taskDisplayName")
//    , @NamedQuery(name = "UGTaskType.findByDefault1", query = "SELECT u FROM UGTaskType u WHERE u.default1 = :default1")
//    , @NamedQuery(name = "UGTaskType.findByCreatedAt", query = "SELECT u FROM UGTaskType u WHERE u.createdAt = :createdAt")
//    , @NamedQuery(name = "UGTaskType.findByUpdatedAt", query = "SELECT u FROM UGTaskType u WHERE u.updatedAt = :updatedAt")
//    , @NamedQuery(name = "UGTaskType.findBySyscreator", query = "SELECT u FROM UGTaskType u WHERE u.syscreator = :syscreator")
//    , @NamedQuery(name = "UGTaskType.findBySyslastupdater", query = "SELECT u FROM UGTaskType u WHERE u.syslastupdater = :syslastupdater")
//    , @NamedQuery(name = "UGTaskType.findBySysdeleted", query = "SELECT u FROM UGTaskType u WHERE u.sysdeleted = :sysdeleted")})
public class UGTaskType implements Serializable 
{
    public static final int TASK_VIEW_PRODUCT = 100;
    public static final int TASK_ADD_PRODUCT = 101;
    public static final int TASK_EDIT_PRODUCT = 102;
    public static final int TASK_VIEW_CATEGORY = 110;
    public static final int TASK_ADD_CATEGORY = 111;
    public static final int TASK_EDIT_CATEGORY = 112; 
    
    public static final int TASK_VIEW_STOCK = 120;
    public static final int TASK_ADD_STOCK = 121;
    public static final int TASK_ADD_ADJUSTMENT = 122;
    public static final int TASK_VIEW_STAFF = 130;
    public static final int TASK_ADD_STAFF = 131;
    public static final int TASK_EDIT_STAFF = 131; 
    
    public static final int TASK_VIEW_STAFF_TYPE = 140;
    public static final int TASK_ADD_STAFF_TYPE = 141;
    public static final int TASK_EDIT_STAFF_TYPE = 141;
    public static final int TASK_VIEW_CUSTOMER = 150;
    public static final int TASK_ADD_CUSTOMER = 151;
    public static final int TASK_EDIT_CUSTOMER = 152; 
    public static final int TASK_DELETE_CUSTOMER = 153; 
    
    public static final int TASK_INPUT_INVOICE = 160;
    public static final int TASK_VOID_INVOICE = 161;
    public static final int TASK_PRINT_INVOICE = 162;
    public static final int TASK_VIEW_PAYMENT = 170;
    public static final int TASK_ADD_PAYMENT = 171;
    public static final int TASK_EDIT_PAYMENT = 172; 
    public static final int TASK_VIEW_REPORT = 400;
            
    public static final int TASK_VIEW_PREFERENCE = 700; 
    public static final int TASK_EDIT_PREFERENCE = 701;; 
 
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private Integer systemId;
    @Basic(optional = false)
    @Column(name = "taskName")
    private String taskName;
    @Basic(optional = false)
    @Column(name = "taskDisplayName")
    private String taskDisplayName;
    @Basic(optional = false)
    @Column(name = "default1")
    private String default1;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "syscreator")
    private String sysCreator;
    @Column(name = "syslastupdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uGTaskTypes")
//    private List<UGAccessRight> uGAccessRightsList;

    public UGTaskType ()
    {
    }

    public UGTaskType (Integer systemId)
    {
        this.systemId = systemId;
    }

    public UGTaskType (Integer systemId, String taskName, String taskDisplayName, String default1, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.taskName = taskName;
        this.taskDisplayName = taskDisplayName;
        this.default1 = default1;
        this.sysDeleted = sysdeleted;
    }

    public Integer getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (Integer systemId)
    {
        this.systemId = systemId;
    }

    public String getTaskName ()
    {
        return taskName;
    }

    public void setTaskName (String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskDisplayName ()
    {
        return taskDisplayName;
    }

    public void setTaskDisplayName (String taskDisplayName)
    {
        this.taskDisplayName = taskDisplayName;
    }

    public String getDefault1 ()
    {
        return default1;
    }

    public void setDefault1 (String default1)
    {
        this.default1 = default1;
    }

    public Date getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getSysCreator ()
    {
        return sysCreator;
    }

    public void setSysCreator (String syscreator)
    {
        this.sysCreator = syscreator;
    }

    public String getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    public void setSysLastUpdater (String syslastupdater)
    {
        this.sysLastUpdater = syslastupdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

//    public List<UGAccessRight> getUGAccessRightsList ()
//    {
//        return uGAccessRightsList;
//    }
//
//    public void setUGAccessRightsList (List<UGAccessRight> uGAccessRightsList)
//    {
//        this.uGAccessRightsList = uGAccessRightsList;
//    }

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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UGTaskType)) {
            return false;
        }
        UGTaskType other = (UGTaskType) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.UGTaskType[ systemId=" + systemId + " ]";
    }

}



