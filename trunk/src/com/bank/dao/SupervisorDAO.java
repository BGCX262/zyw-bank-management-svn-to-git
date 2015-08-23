package com.bank.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.operator.Supervisor;

/**
 * A data access object (DAO) providing persistence and search support for
 * Supervisor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bank.dao.Supervisor
 * @author MyEclipse Persistence Tools
 */

public class SupervisorDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SupervisorDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Supervisor transientInstance) {
		log.debug("saving Supervisor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Supervisor persistentInstance) {
		log.debug("deleting Supervisor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Supervisor findById(Long id) {
		log.debug("getting Supervisor instance with id: " + id);
		try {
			Supervisor instance = (Supervisor) getHibernateTemplate().get(
					"com.bank.operator.Supervisor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Supervisor instance) {
		log.debug("finding Supervisor instance by example");
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
		log.debug("finding Supervisor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Supervisor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Supervisor instances");
		try {
			String queryString = "from Supervisor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Supervisor merge(Supervisor detachedInstance) {
		log.debug("merging Supervisor instance");
		try {
			Supervisor result = (Supervisor) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Supervisor instance) {
		log.debug("attaching dirty Supervisor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Supervisor instance) {
		log.debug("attaching clean Supervisor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SupervisorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SupervisorDAO) ctx.getBean("SupervisorDAO");
	}
}