package com.bank.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.operator.Teller;

/**
 * A data access object (DAO) providing persistence and search support for
 * Teller entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bank.dao.Teller
 * @author MyEclipse Persistence Tools
 */

public class TellerDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TellerDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Teller transientInstance) {
		log.debug("saving Teller instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Teller persistentInstance) {
		log.debug("deleting Teller instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Teller findById(Long id) {
		log.debug("getting Teller instance with id: " + id);
		try {
			Teller instance = (Teller) getHibernateTemplate().get(
					"com.bank.operator.Teller", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Teller instance) {
		log.debug("finding Teller instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Teller instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Teller as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Teller instances");
		try {
			String queryString = "from Teller";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Teller merge(Teller detachedInstance) {
		log.debug("merging Teller instance");
		try {
			Teller result = (Teller) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Teller instance) {
		log.debug("attaching dirty Teller instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Teller instance) {
		log.debug("attaching clean Teller instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TellerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TellerDAO) ctx.getBean("TellerDAO");
	}
}