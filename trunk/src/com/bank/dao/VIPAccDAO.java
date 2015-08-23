package com.bank.dao;
// default package

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.entity.VIPAcc;

/**
 	* A data access object (DAO) providing persistence and search support for VIPAcc entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .VIPAcc
  * @author MyEclipse Persistence Tools 
 */

public class VIPAccDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(VIPAccDAO.class);
		//property constants
	public static final String CUSTLOGINFO_ID = "custloginfoId";
	public static final String DEBTTIME = "debttime";



	protected void initDao() {
		//do nothing
	}
    
    public void save(VIPAcc transientInstance) {
        log.debug("saving VIPAcc instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(VIPAcc persistentInstance) {
        log.debug("deleting VIPAcc instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VIPAcc findById( Long id) {
        log.debug("getting VIPAcc instance with id: " + id);
        try {
            VIPAcc instance = (VIPAcc) getHibernateTemplate()
                    .get("com.bank.entity.VIPAcc", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(VIPAcc instance) {
        log.debug("finding VIPAcc instance by example");
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
      log.debug("finding VIPAcc instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from VIPAcc as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCustloginfoId(Object custloginfoId
	) {
		return findByProperty(CUSTLOGINFO_ID, custloginfoId
		);
	}
	
	public List findByDebttime(Object debttime
	) {
		return findByProperty(DEBTTIME, debttime
		);
	}
	

	public List findAll() {
		log.debug("finding all VIPAcc instances");
		try {
			String queryString = "from VIPAcc";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public VIPAcc merge(VIPAcc detachedInstance) {
        log.debug("merging VIPAcc instance");
        try {
            VIPAcc result = (VIPAcc) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(VIPAcc instance) {
        log.debug("attaching dirty VIPAcc instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VIPAcc instance) {
        log.debug("attaching clean VIPAcc instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static VIPAccDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (VIPAccDAO) ctx.getBean("VIPAccDAO");
	}
}