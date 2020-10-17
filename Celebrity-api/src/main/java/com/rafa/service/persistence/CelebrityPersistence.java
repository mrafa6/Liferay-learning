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

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.rafa.exception.NoSuchCelebrityException;
import com.rafa.model.Celebrity;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the celebrity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CelebrityUtil
 * @generated
 */
@ProviderType
public interface CelebrityPersistence extends BasePersistence<Celebrity> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CelebrityUtil} to access the celebrity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the celebrities where profession = &#63;.
	 *
	 * @param profession the profession
	 * @return the matching celebrities
	 */
	public java.util.List<Celebrity> findByoccupation(String profession);

	/**
	 * Returns a range of all the celebrities where profession = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param profession the profession
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @return the range of matching celebrities
	 */
	public java.util.List<Celebrity> findByoccupation(
		String profession, int start, int end);

	/**
	 * Returns an ordered range of all the celebrities where profession = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByoccupation(String, int, int, OrderByComparator)}
	 * @param profession the profession
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching celebrities
	 */
	@Deprecated
	public java.util.List<Celebrity> findByoccupation(
		String profession, int start, int end,
		OrderByComparator<Celebrity> orderByComparator, boolean useFinderCache);

	/**
	 * Returns an ordered range of all the celebrities where profession = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param profession the profession
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching celebrities
	 */
	public java.util.List<Celebrity> findByoccupation(
		String profession, int start, int end,
		OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the first celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	public Celebrity findByoccupation_First(
			String profession, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Returns the first celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	public Celebrity fetchByoccupation_First(
		String profession, OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the last celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	public Celebrity findByoccupation_Last(
			String profession, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Returns the last celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	public Celebrity fetchByoccupation_Last(
		String profession, OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the celebrities before and after the current celebrity in the ordered set where profession = &#63;.
	 *
	 * @param celebrityId the primary key of the current celebrity
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public Celebrity[] findByoccupation_PrevAndNext(
			long celebrityId, String profession,
			OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Removes all the celebrities where profession = &#63; from the database.
	 *
	 * @param profession the profession
	 */
	public void removeByoccupation(String profession);

	/**
	 * Returns the number of celebrities where profession = &#63;.
	 *
	 * @param profession the profession
	 * @return the number of matching celebrities
	 */
	public int countByoccupation(String profession);

	/**
	 * Returns all the celebrities where country = &#63;.
	 *
	 * @param country the country
	 * @return the matching celebrities
	 */
	public java.util.List<Celebrity> findByCountry(String country);

	/**
	 * Returns a range of all the celebrities where country = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param country the country
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @return the range of matching celebrities
	 */
	public java.util.List<Celebrity> findByCountry(
		String country, int start, int end);

	/**
	 * Returns an ordered range of all the celebrities where country = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByCountry(String, int, int, OrderByComparator)}
	 * @param country the country
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching celebrities
	 */
	@Deprecated
	public java.util.List<Celebrity> findByCountry(
		String country, int start, int end,
		OrderByComparator<Celebrity> orderByComparator, boolean useFinderCache);

	/**
	 * Returns an ordered range of all the celebrities where country = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CelebrityModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param country the country
	 * @param start the lower bound of the range of celebrities
	 * @param end the upper bound of the range of celebrities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching celebrities
	 */
	public java.util.List<Celebrity> findByCountry(
		String country, int start, int end,
		OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the first celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	public Celebrity findByCountry_First(
			String country, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Returns the first celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	public Celebrity fetchByCountry_First(
		String country, OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the last celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	public Celebrity findByCountry_Last(
			String country, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Returns the last celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	public Celebrity fetchByCountry_Last(
		String country, OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Returns the celebrities before and after the current celebrity in the ordered set where country = &#63;.
	 *
	 * @param celebrityId the primary key of the current celebrity
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public Celebrity[] findByCountry_PrevAndNext(
			long celebrityId, String country,
			OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException;

	/**
	 * Removes all the celebrities where country = &#63; from the database.
	 *
	 * @param country the country
	 */
	public void removeByCountry(String country);

	/**
	 * Returns the number of celebrities where country = &#63;.
	 *
	 * @param country the country
	 * @return the number of matching celebrities
	 */
	public int countByCountry(String country);

	/**
	 * Caches the celebrity in the entity cache if it is enabled.
	 *
	 * @param celebrity the celebrity
	 */
	public void cacheResult(Celebrity celebrity);

	/**
	 * Caches the celebrities in the entity cache if it is enabled.
	 *
	 * @param celebrities the celebrities
	 */
	public void cacheResult(java.util.List<Celebrity> celebrities);

	/**
	 * Creates a new celebrity with the primary key. Does not add the celebrity to the database.
	 *
	 * @param celebrityId the primary key for the new celebrity
	 * @return the new celebrity
	 */
	public Celebrity create(long celebrityId);

	/**
	 * Removes the celebrity with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity that was removed
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public Celebrity remove(long celebrityId) throws NoSuchCelebrityException;

	public Celebrity updateImpl(Celebrity celebrity);

	/**
	 * Returns the celebrity with the primary key or throws a <code>NoSuchCelebrityException</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	public Celebrity findByPrimaryKey(long celebrityId)
		throws NoSuchCelebrityException;

	/**
	 * Returns the celebrity with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param celebrityId the primary key of the celebrity
	 * @return the celebrity, or <code>null</code> if a celebrity with the primary key could not be found
	 */
	public Celebrity fetchByPrimaryKey(long celebrityId);

	/**
	 * Returns all the celebrities.
	 *
	 * @return the celebrities
	 */
	public java.util.List<Celebrity> findAll();

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
	public java.util.List<Celebrity> findAll(int start, int end);

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
	public java.util.List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator,
		boolean useFinderCache);

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
	public java.util.List<Celebrity> findAll(
		int start, int end, OrderByComparator<Celebrity> orderByComparator);

	/**
	 * Removes all the celebrities from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of celebrities.
	 *
	 * @return the number of celebrities
	 */
	public int countAll();

}