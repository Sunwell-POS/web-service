/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * GenericFacade.java
 *
 * Created on Oct 16, 2017, 9:56:29 AM
 */

package sunwell.pos.entity.bus;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.dao.GenericDAO;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GenericFacade 
{
    @Inject
    GenericDAO genericDAO;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T create(T _entity) {
        genericDAO.create (_entity);
        return _entity;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T edit(T _entity) {
        genericDAO.edit (_entity);
        return _entity;
    }
        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T delete(Object _id, Class<T> _type) {
        T entity = genericDAO.findById (_id, _type);
        genericDAO.delete (entity);
        return entity;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> T deleteObject(T _obj) {
        genericDAO.delete (_obj);
        return _obj;
    }
    
    public <T> T findById(Object _id, Class<T> _type) {
        return genericDAO.findById (_id, _type);
    }
    
    public <T> List<T> findAll(Class<T> _type) {
        return genericDAO.findAll (_type);
    }
    
    public void flush() {
        genericDAO.flush ();
    }
    
    public void remove(Object _entity) {
        genericDAO.remove (_entity);
    }
    
    public void refresh(Object _ent) {
        genericDAO.refresh(_ent);
    }
    
    public void rollback() {
        genericDAO.rollback ();
    }
    
    public abstract class abs<T> {
    
    }
    
    public class ext extends abs<Product> {
        
    }
    
    private abs<Product> var = new ext();
}


