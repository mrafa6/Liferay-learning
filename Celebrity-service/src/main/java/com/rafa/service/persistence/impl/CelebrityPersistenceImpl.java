/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rafa.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.rafa.exception.NoSuchCelebrityException;
import com.rafa.model.Celebrity;
import com.rafa.model.impl.CelebrityImpl;
import com.rafa.model.impl.CelebrityModelImpl;
import com.rafa.service.persistence.CelebrityPersistence;
import com.rafa.service.persistence.impl.constants.roasterPersistenceConstants;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the celebrity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CelebrityPersistence.class)
@ProviderType
public class CelebrityPersistenceImpl
	extends BasePersistenceImpl<Celebrity> implements CelebrityPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CelebrityUtil</code> to access the celebrity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CelebrityImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public CelebrityPersistenceImpl() {
		setModelClass(Celebrity.class);

		setModelImplClass(CelebrityImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the celebrity in the entity cache if it is enabled.
	 *
	 * @param celebrity the celebrity
	 */
	@Override
	public void cacheResult(Celebrity celebrity) {
		entityCache.putResult(
			entityCacheEnabled, CelebrityImpl.class, celebrity.getPrimaryKey(),
			celebrity);

		celebrity.resetOriginalValues();
	}

	/**
	 * Caches the celebrities in the entity cache if it is enabled.
	 *
	 * @param celebrities the celebrities
	 */
	@Override
	public void cacheResult(List<Celebrity> celebrities) {
		for (Celebrity celebrity : celebrities) {
			if (entityCache.getResult(
					entityCacheEnabled, CelebrityImpl.class,
					celebrity.getPrimaryKey()) == null) {

				cacheResult(celebrity);
			}
			else {
				celebrity.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all celebrities.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CelebrityImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the celebrity.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Celebrity celebrity) {
		entityCache.removeResult(
			entityCacheEnabled, CelebrityImpl.class, celebrity.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Celebrity> celebrities) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Celebrity celebrity : celebrities) {
			entityCache.removeResult(
				entityCacheEnabled, CelebrityImpl.class,
				celebrity.getPrimaryKey());
		}
	}

	/**
	 * Creates a new celebrity with the primary key. Does not add the celebrity to the database.
	 *
	 * @param celebrityId the primary key for the new celebrity
	 * @return the new celebrity
	 */
	@Override
	public Celebrity create(long celebrityId) {
		Celebrity celebrity = new CelebrityImpl();

		celebrity.setNew(true);
		celebrity.setPrimaryKey(celebrityId);

		return celebrity;
	}

	/**
	 * Removes the celebrity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity that was removed
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity remove(long celebrityId) throws NoSuchCelebrityException {
		return remove((Serializable)celebrityId);
	}

	/**
	 * Removes the celebrity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the celebrity
	 * @return the celebrity that was removed
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity remove(Serializable primaryKey)
		throws NoSuchCelebrityException {

		Session session = null;

		try {
			session = openSession();

			Celebrity celebrity = (Celebrity)session.get(
				CelebrityImpl.class, primaryKey);

			if (celebrity == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCelebrityException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(celebrity);
		}
		catch (NoSuchCelebrityException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Celebrity removeImpl(Celebrity celebrity) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(celebrity)) {
				celebrity = (Celebrity)session.get(
					CelebrityImpl.class, celebrity.getPrimaryKeyObj());
			}

			if (celebrity != null) {
				session.delete(celebrity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (celebrity != null) {
			clearCache(celebrity);
		}

		return celebrity;
	}

	@Override
	public Celebrity updateImpl(Celebrity celebrity) {
		boolean isNew = celebrity.isNew();

		Session session = null;

		try {
			session = openSession();

			if (celebrity.isNew()) {
				session.save(celebrity);

				celebrity.setNew(false);
			}
			else {
				celebrity = (Celebrity)session.merge(celebrity);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, CelebrityImpl.class, celebrity.getPrimaryKey(),
			celebrity, false);

		celebrity.resetOriginalValues();

		return celebrity;
	}

	/**
	 * Returns the celebrity with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the celebrity
	 * @return the celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCelebrityException {

		Celebrity celebrity = fetchByPrimaryKey(primaryKey);

		if (celebrity == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCelebrityException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return celebrity;
	}

	/**
	 * Returns the celebrity with the primary key or throws a <code>NoSuchCelebrityException</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity findByPrimaryKey(long celebrityId)
		throws NoSuchCelebrityException {

		return findByPrimaryKey((Serializable)celebrityId);
	}

	/**
	 * Returns the celebrity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity, or <code>null</code> if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity fetchByPrimaryKey(long celebrityId) {
		return fetchByPrimaryKey((Serializable)celebrityId);
	}

	/**
	 * Returns all the celebrities.
	 *
	 * @return the celebrities
	 */
	@Override
	public List<Celebrity> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the celebrities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @return the range of celebrities
	 */
	@Override
	public List<Celebrity> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the celebrities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of celebrities
	 */
	@Deprecated
	@Override
	public List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the celebrities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of celebrities
	 */
	@Override
	public List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Celebrity> list = (List<Celebrity>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CELEBRITY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CELEBRITY;

				if (pagination) {
					sql = sql.concat(CelebrityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Celebrity>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Celebrity>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the celebrities from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Celebrity celebrity : findAll()) {
			remove(celebrity);
		}
	}

	/**
	 * Returns the number of celebrities.
	 *
	 * @return the number of celebrities
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CELEBRITY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "celebrityId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CELEBRITY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CelebrityModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the celebrity persistence.
	 */
	@Activate
	public void activate() {
		CelebrityModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CelebrityModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CelebrityImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = roasterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.rafa.model.Celebrity"),
			true);
	}

	@Override
	@Reference(
		target = roasterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = roasterPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CELEBRITY =
		"SELECT celebrity FROM Celebrity celebrity";

	private static final String _SQL_COUNT_CELEBRITY =
		"SELECT COUNT(celebrity) FROM Celebrity celebrity";

	private static final String _ORDER_BY_ENTITY_ALIAS = "celebrity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Celebrity exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CelebrityPersistenceImpl.class);

	static {
		try {
			Class.forName(roasterPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}