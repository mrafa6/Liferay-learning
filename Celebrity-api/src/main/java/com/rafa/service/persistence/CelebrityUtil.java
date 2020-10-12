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

package com.rafa.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.rafa.model.Celebrity;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the celebrity service. This utility wraps <code>com.rafa.service.persistence.impl.CelebrityPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CelebrityPersistence
 * @generated
 */
@ProviderType
public class CelebrityUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Celebrity celebrity) {
		getPersistence().clearCache(celebrity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Celebrity> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Celebrity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Celebrity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Celebrity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Celebrity> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Celebrity update(Celebrity celebrity) {
		return getPersistence().update(celebrity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Celebrity update(
		Celebrity celebrity, ServiceContext serviceContext) {

		return getPersistence().update(celebrity, serviceContext);
	}

	/**
	 * Caches the celebrity in the entity cache if it is enabled.
	 *
	 * @param celebrity the celebrity
	 */
	public static void cacheResult(Celebrity celebrity) {
		getPersistence().cacheResult(celebrity);
	}

	/**
	 * Caches the celebrities in the entity cache if it is enabled.
	 *
	 * @param celebrities the celebrities
	 */
	public static void cacheResult(List<Celebrity> celebrities) {
		getPersistence().cacheResult(celebrities);
	}

	/**
	 * Creates a new celebrity with the primary key. Does not add the celebrity to the database.
	 *
	 * @param celebrityId the primary key for the new celebrity
	 * @return the new celebrity
	 */
	public static Celebrity create(long celebrityId) {
		return getPersistence().create(celebrityId);
	}

	/**
	 * Removes the celebrity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity that was removed
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public static Celebrity remove(long celebrityId)
		throws com.rafa.exception.NoSuchCelebrityException {

		return getPersistence().remove(celebrityId);
	}

	public static Celebrity updateImpl(Celebrity celebrity) {
		return getPersistence().updateImpl(celebrity);
	}

	/**
	 * Returns the celebrity with the primary key or throws a <code>NoSuchCelebrityException</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public static Celebrity findByPrimaryKey(long celebrityId)
		throws com.rafa.exception.NoSuchCelebrityException {

		return getPersistence().findByPrimaryKey(celebrityId);
	}

	/**
	 * Returns the celebrity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity, or <code>null</code> if a celebrity with the primary key could not be found
	 */
	public static Celebrity fetchByPrimaryKey(long celebrityId) {
		return getPersistence().fetchByPrimaryKey(celebrityId);
	}

	/**
	 * Returns all the celebrities.
	 *
	 * @return the celebrities
	 */
	public static List<Celebrity> findAll() {
		return getPersistence().findAll();
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
	public static List<Celebrity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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
	public static List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Removes all the celebrities from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of celebrities.
	 *
	 * @return the number of celebrities
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CelebrityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CelebrityPersistence, CelebrityPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CelebrityPersistence.class);

		ServiceTracker<CelebrityPersistence, CelebrityPersistence>
			serviceTracker =
				new ServiceTracker<CelebrityPersistence, CelebrityPersistence>(
					bundle.getBundleContext(), CelebrityPersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}