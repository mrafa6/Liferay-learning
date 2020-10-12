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

package com.rafa.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link CelebrityLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CelebrityLocalService
 * @generated
 */
@ProviderType
public class CelebrityLocalServiceWrapper
	implements CelebrityLocalService, ServiceWrapper<CelebrityLocalService> {

	public CelebrityLocalServiceWrapper(
		CelebrityLocalService celebrityLocalService) {

		_celebrityLocalService = celebrityLocalService;
	}

	/**
	 * Adds the celebrity to the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrity the celebrity
	 * @return the celebrity that was added
	 */
	@Override
	public com.rafa.model.Celebrity addCelebrity(
		com.rafa.model.Celebrity celebrity) {

		return _celebrityLocalService.addCelebrity(celebrity);
	}

	/**
	 * Creates a new celebrity with the primary key. Does not add the celebrity to the database.
	 *
	 * @param celebrityId the primary key for the new celebrity
	 * @return the new celebrity
	 */
	@Override
	public com.rafa.model.Celebrity createCelebrity(long celebrityId) {
		return _celebrityLocalService.createCelebrity(celebrityId);
	}

	/**
	 * Deletes the celebrity from the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrity the celebrity
	 * @return the celebrity that was removed
	 */
	@Override
	public com.rafa.model.Celebrity deleteCelebrity(
		com.rafa.model.Celebrity celebrity) {

		return _celebrityLocalService.deleteCelebrity(celebrity);
	}

	/**
	 * Deletes the celebrity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity that was removed
	 * @throws PortalException if a celebrity with the primary key could not be found
	 */
	@Override
	public com.rafa.model.Celebrity deleteCelebrity(long celebrityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _celebrityLocalService.deleteCelebrity(celebrityId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _celebrityLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _celebrityLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _celebrityLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.rafa.model.impl.CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _celebrityLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.rafa.model.impl.CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _celebrityLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _celebrityLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _celebrityLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.rafa.model.Celebrity fetchCelebrity(long celebrityId) {
		return _celebrityLocalService.fetchCelebrity(celebrityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _celebrityLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the celebrities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.rafa.model.impl.CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @return the range of celebrities
	 */
	@Override
	public java.util.List<com.rafa.model.Celebrity> getCelebrities(
		int start, int end) {

		return _celebrityLocalService.getCelebrities(start, end);
	}

	/**
	 * Returns the number of celebrities.
	 *
	 * @return the number of celebrities
	 */
	@Override
	public int getCelebritiesCount() {
		return _celebrityLocalService.getCelebritiesCount();
	}

	/**
	 * Returns the celebrity with the primary key.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity
	 * @throws PortalException if a celebrity with the primary key could not be found
	 */
	@Override
	public com.rafa.model.Celebrity getCelebrity(long celebrityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _celebrityLocalService.getCelebrity(celebrityId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _celebrityLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _celebrityLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _celebrityLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the celebrity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param celebrity the celebrity
	 * @return the celebrity that was updated
	 */
	@Override
	public com.rafa.model.Celebrity updateCelebrity(
		com.rafa.model.Celebrity celebrity) {

		return _celebrityLocalService.updateCelebrity(celebrity);
	}

	@Override
	public CelebrityLocalService getWrappedService() {
		return _celebrityLocalService;
	}

	@Override
	public void setWrappedService(CelebrityLocalService celebrityLocalService) {
		_celebrityLocalService = celebrityLocalService;
	}

	private CelebrityLocalService _celebrityLocalService;

}