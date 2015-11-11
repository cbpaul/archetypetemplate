package com.paul.archetype.service.generic.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.paul.archetype.repository.BaseRepository;
import com.paul.archetype.service.generic.GenericManager;
import com.paul.archetype.utils.GenericsUtils;


/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.sam.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.sam.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.sam.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.sam.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.sam.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.sam.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Updated by jgarcia: added full text search + reindexing
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {
	protected Class<T> entityClass;
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected BaseRepository<T, PK> dao;

    public GenericManagerImpl(BaseRepository<T, PK> genericDao) {
        this.dao = genericDao;
    }
    protected Class<T> getEntityClass(){
    	return entityClass;
    }
    @SuppressWarnings("unchecked")
	public GenericManagerImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		if (log.isDebugEnabled()) {
			log.debug("AbstractHibernateDao be Created, and class is :"
					+ entityClass.toString());
		}
	}
    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.findAll();
    }
    public List<T> getAll(Direction sortType,String...sortProperties){
    	return dao.findAll(new Sort(sortType, sortProperties));
    }
    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.findOne(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        dao.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Search implementation using Hibernate Search.
     */
	public List<T> search(Map<String, Object> searchParams, Direction sortType,String...sortProperties) {
        if (searchParams == null ||	searchParams.size()==0) {
            return getAll();
        }
        Specification<T> specification= buildSpecification(searchParams);
        return dao.findAll(specification,new Sort(sortType, sortProperties));
    }

	public Page<T> findPage(int pageNumber, int pageSize, String sortType, Map<String, Object> searchParams,String... properties){
		  PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType,properties);
		  Specification<T> specification= buildSpecification(searchParams);
		return dao.findAll(specification,pageRequest);
	}

	/**
     * 创建分页请求.
     */
    public PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType,String... properties) {
        Sort sort = null;
        if ("desc".equals(sortType)) {
            sort = new Sort(Direction.DESC, properties);
        } else if ("asc".equals(sortType)) {
            sort = new Sort(Direction.ASC, properties);
        }
        return new PageRequest(pageNumber, pagzSize, sort);
    }
    
    /**
	 * 创建动态查询条件组合.
	 */
	protected Specification<T> buildSpecification( Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters =new HashMap<String, SearchFilter>();
		if(searchParams!=null){
			filters = SearchFilter.parse(searchParams);
		}
		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), getEntityClass());
		return spec;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void remove(PK...ids) {
		for(PK pk:ids){
			remove(pk);
		}
		
	}
    
}
