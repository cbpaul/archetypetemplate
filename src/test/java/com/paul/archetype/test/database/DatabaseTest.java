package com.paul.archetype.test.database;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/applicationContext.xml"})
public class DatabaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static Logger logger = LoggerFactory.getLogger(DatabaseTest.class);
	protected DataSource dataSource;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
	@PersistenceContext
	private EntityManager em;

	@Test
	public void allClassMapping() throws Exception {
		Metamodel model = em.getEntityManagerFactory().getMetamodel();
		assertTrue("No entity mapping found", model.getEntities().size() > 0);
		for (@SuppressWarnings("rawtypes") EntityType entityType : model.getEntities()) {
			String entityName = entityType.getName();
			System.out.println(entityName);
			em.createQuery("select o from " + entityName + " o").getResultList();
			logger.info("ok: " + entityName);
		}
	}
}
