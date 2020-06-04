/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredFacade.java
 *
 * Created on Oct 16, 2017, 9:59:37 AM
 */

package sunwell.pos.entity.bus;

import aegwyn.core.web.util.Util;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.mindrot.jbcrypt.BCrypt;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;
import sunwell.pos.entity.dao.CredDAO;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserCredFacade 
{
    /**
     * <pre>
     * Mengembalikan nilai integer sbb:
     * 0 = login gagal (wrong username and/or passwd)
     * 1 = login berhasil
     * 2 = account suspended
     * </pre>
     * 
     * @param _username
     * @param _passwd
     * @return 
     */
    
    @Inject
    CredDAO userCredDAO;
    
    public User validate (Tenant _tenant, String _email, String _password) throws Exception
    {
        try {
            User usr = userCredDAO.findByTenantAndEmail (_tenant, _email); 

            if (usr != null) {
                // Hitung MD5 dari password masukan user.
                String passDariUser = Util.generateMD5 (_password);
                String pass = usr.getPassword ();
                pass = "$2a" + pass.substring (3);
                
//                String s = BCrypt.hashpw(_password, BCrypt.gensalt());

                System.out.println ("PASSWORD: " + _password);
                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ hasil MD5 oleh java = " + passDariUser);
                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ passwd di DB = " + usr.getPassword ());

                // Periksa password
//                if (!passDariUser.equals (usr.getPassword ())) 
                if(!BCrypt.checkpw (_password, pass)) {
                    System.out.println ("DOESN't MATCH");
                    usr = null;
                }
                else {
                    System.out.println ("MATCHED");
                }
            }
            return usr;
        }
        catch (NoResultException ex) {
            ex.printStackTrace ();
            return null;
        }
    }
        
//    public User validate (String _email, String _password) throws Exception
//    {
//        try {
//            User usr = userCredDAO.findByEmail (_email); 
//
//            if (usr != null) {
//                // Hitung MD5 dari password masukan user.
//                String passDariUser = Util.generateMD5 (_password);
//                String pass = usr.getPassword ();
//                pass = "$2a" + pass.substring (3);
//                
////                String s = BCrypt.hashpw(_password, BCrypt.gensalt());
//
//                System.out.println ("PASSWORD: " + _password);
//                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ hasil MD5 oleh java = " + passDariUser);
//                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ passwd di DB = " + usr.getPassword ());
//
//                // Periksa password
////                if (!passDariUser.equals (usr.getPassword ())) 
//                if(!BCrypt.checkpw (_password, pass)) {
//                    System.out.println ("DOESN't MATCH");
//                    usr = null;
//                }
//                else {
//                    System.out.println ("MATCHED");
//                }
//            }
//            return usr;
//        }
//        catch (NoResultException ex) {
//            ex.printStackTrace ();
//            return null;
//        }
//    }
    
    public List<UserGroup> findUserGroupByTenant(Tenant _t) {
        return userCredDAO.findUserGroupByTenant (_t);
    }
    
    public List<User> findByTenant(Tenant _t) {
        return userCredDAO.findByTenant (_t);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(User _usr) {
        userCredDAO.create (_usr);
    }    
}
