package com.bank.dao;
// default package

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.entity.CustLogInfo;

/**
 	* A data access object (DAO) providing persistence and search support for CustLogInfo entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .CustLogInfo
  * @author MyEclipse Persistence Tools 
 */

public class CustLogInfoDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(CustLogInfoDAO.class);
		//property constants
	public static final String CUSTOMER_ID = "customerId";
	public static final String PASSWORD = "password";



	protected void initDao() {
		//do nothing
	}
    
    public void save(CustLogInfo transientInstance) {
        log.debug("saving CustLogInfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(CustLogInfo persistentInstance) {
        log.debug("deleting CustLogInfo instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CustLogInfo findById( Long id) {
        log.debug("getting CustLogInfo instance with id: " + id);
        try {
            CustLogInfo instance = (CustLogInfo) getHibernateTemplate()
                    .get("CustLogInfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(CustLogInfo instance) {
        log.debug("finding CustLogInfo instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding CustLogInfo instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from CustLogInfo as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCustomerId(Object customerId
	) {
		return findByProperty(CUSTOMER_ID, customerId
		);
	}
	
	public List findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	

	public List findAll() {
		log.debug("finding all CustLogInfo instances");
		try {
			String queryString = "from CustLogInfo";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public CustLogInfo merge(CustLogInfo detachedInstance) {
        log.debug("merging CustLogInfo instance");
        try {
            CustLogInfo result = (CustLogInfo) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CustLogInfo instance) {
        log.debug("attaching dirty CustLogInfo instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CustLogInfo instance) {
        log.debug("attaching clean CustLogInfo instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CustLogInfoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CustLogInfoDAO) ctx.getBean("CustLogInfoDAO");
	}
}