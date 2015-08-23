package com.bank.dao;
// default package

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.bank.operator.OptRecord;

/**
 	* A data access object (DAO) providing persistence and search support for OptRecord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .OptRecord
  * @author MyEclipse Persistence Tools 
 */

public class OptRecordDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(OptRecordDAO.class);
		//property constants
	public static final String ACCOUNT_ID = "accountId";
	public static final String DESCRITION = "descrition";



	protected void initDao() {
		//do nothing
	}
    
    public void save(OptRecord transientInstance) {
        log.debug("saving OptRecord instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(OptRecord persistentInstance) {
        log.debug("deleting OptRecord instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public OptRecord findById( Long id) {
        log.debug("getting OptRecord instance with id: " + id);
        try {
            OptRecord instance = (OptRecord) getHibernateTemplate()
                    .get("OptRecord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(OptRecord instance) {
        log.debug("finding OptRecord instance by example");
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
      log.debug("finding OptRecord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OptRecord as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAccountId(Object accountId
	) {
		return findByProperty(ACCOUNT_ID, accountId
		);
	}
	
	public List findByDescrition(Object descrition
	) {
		return findByProperty(DESCRITION, descrition
		);
	}
	

	public List findAll() {
		log.debug("finding all OptRecord instances");
		try {
			String queryString = "from OptRecord";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public OptRecord merge(OptRecord detachedInstance) {
        log.debug("merging OptRecord instance");
        try {
            OptRecord result = (OptRecord) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(OptRecord instance) {
        log.debug("attaching dirty OptRecord instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(OptRecord instance) {
        log.debug("attaching clean OptRecord instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static OptRecordDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (OptRecordDAO) ctx.getBean("OptRecordDAO");
	}
}