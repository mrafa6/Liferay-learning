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
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.rafa.exception.NoSuchCelebrityException;
import com.rafa.model.Celebrity;
import com.rafa.model.impl.CelebrityImpl;
import com.rafa.model.impl.CelebrityModelImpl;
import com.rafa.service.persistence.CelebrityPersistence;
import com.rafa.service.persistence.impl.constants.roasterPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	private FinderPath _finderPathWithPaginationFindByoccupation;
	private FinderPath _finderPathWithoutPaginationFindByoccupation;
	private FinderPath _finderPathCountByoccupation;

	/**
	 * Returns all the celebrities where profession = &#63;.
	 *
	 * @param profession the profession
	 * @return the matching celebrities
	 */
	@Override
	public List<Celebrity> findByoccupation(String profession) {
		return findByoccupation(
			profession, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Celebrity> findByoccupation(
		String profession, int start, int end) {

		return findByoccupation(profession, start, end, null);
	}

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
	@Override
	public List<Celebrity> findByoccupation(
		String profession, int start, int end,
		OrderByComparator<Celebrity> orderByComparator,
		boolean useFinderCache) {

		return findByoccupation(profession, start, end, orderByComparator);
	}

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
	@Override
	public List<Celebrity> findByoccupation(
		String profession, int start, int end,
		OrderByComparator<Celebrity> orderByComparator) {

		profession = Objects.toString(profession, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByoccupation;
			finderArgs = new Object[] {profession};
		}
		else {
			finderPath = _finderPathWithPaginationFindByoccupation;
			finderArgs = new Object[] {
				profession, start, end, orderByComparator
			};
		}

		List<Celebrity> list = (List<Celebrity>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Celebrity celebrity : list) {
				if (!profession.equals(celebrity.getProfession())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CELEBRITY_WHERE);

			boolean bindProfession = false;

			if (profession.isEmpty()) {
				query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_3);
			}
			else {
				bindProfession = true;

				query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CelebrityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProfession) {
					qPos.add(profession);
				}

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
	 * Returns the first celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	@Override
	public Celebrity findByoccupation_First(
			String profession, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		Celebrity celebrity = fetchByoccupation_First(
			profession, orderByComparator);

		if (celebrity != null) {
			return celebrity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("profession=");
		msg.append(profession);

		msg.append("}");

		throw new NoSuchCelebrityException(msg.toString());
	}

	/**
	 * Returns the first celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	@Override
	public Celebrity fetchByoccupation_First(
		String profession, OrderByComparator<Celebrity> orderByComparator) {

		List<Celebrity> list = findByoccupation(
			profession, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	@Override
	public Celebrity findByoccupation_Last(
			String profession, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		Celebrity celebrity = fetchByoccupation_Last(
			profession, orderByComparator);

		if (celebrity != null) {
			return celebrity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("profession=");
		msg.append(profession);

		msg.append("}");

		throw new NoSuchCelebrityException(msg.toString());
	}

	/**
	 * Returns the last celebrity in the ordered set where profession = &#63;.
	 *
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	@Override
	public Celebrity fetchByoccupation_Last(
		String profession, OrderByComparator<Celebrity> orderByComparator) {

		int count = countByoccupation(profession);

		if (count == 0) {
			return null;
		}

		List<Celebrity> list = findByoccupation(
			profession, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the celebrities before and after the current celebrity in the ordered set where profession = &#63;.
	 *
	 * @param celebrityId the primary key of the current celebrity
	 * @param profession the profession
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity[] findByoccupation_PrevAndNext(
			long celebrityId, String profession,
			OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		profession = Objects.toString(profession, "");

		Celebrity celebrity = findByPrimaryKey(celebrityId);

		Session session = null;

		try {
			session = openSession();

			Celebrity[] array = new CelebrityImpl[3];

			array[0] = getByoccupation_PrevAndNext(
				session, celebrity, profession, orderByComparator, true);

			array[1] = celebrity;

			array[2] = getByoccupation_PrevAndNext(
				session, celebrity, profession, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Celebrity getByoccupation_PrevAndNext(
		Session session, Celebrity celebrity, String profession,
		OrderByComparator<Celebrity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CELEBRITY_WHERE);

		boolean bindProfession = false;

		if (profession.isEmpty()) {
			query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_3);
		}
		else {
			bindProfession = true;

			query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CelebrityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindProfession) {
			qPos.add(profession);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(celebrity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Celebrity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the celebrities where profession = &#63; from the database.
	 *
	 * @param profession the profession
	 */
	@Override
	public void removeByoccupation(String profession) {
		for (Celebrity celebrity :
				findByoccupation(
					profession, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(celebrity);
		}
	}

	/**
	 * Returns the number of celebrities where profession = &#63;.
	 *
	 * @param profession the profession
	 * @return the number of matching celebrities
	 */
	@Override
	public int countByoccupation(String profession) {
		profession = Objects.toString(profession, "");

		FinderPath finderPath = _finderPathCountByoccupation;

		Object[] finderArgs = new Object[] {profession};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CELEBRITY_WHERE);

			boolean bindProfession = false;

			if (profession.isEmpty()) {
				query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_3);
			}
			else {
				bindProfession = true;

				query.append(_FINDER_COLUMN_OCCUPATION_PROFESSION_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindProfession) {
					qPos.add(profession);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_OCCUPATION_PROFESSION_2 =
		"celebrity.profession = ?";

	private static final String _FINDER_COLUMN_OCCUPATION_PROFESSION_3 =
		"(celebrity.profession IS NULL OR celebrity.profession = '')";

	private FinderPath _finderPathWithPaginationFindByCountry;
	private FinderPath _finderPathWithoutPaginationFindByCountry;
	private FinderPath _finderPathCountByCountry;

	/**
	 * Returns all the celebrities where country = &#63;.
	 *
	 * @param country the country
	 * @return the matching celebrities
	 */
	@Override
	public List<Celebrity> findByCountry(String country) {
		return findByCountry(
			country, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Celebrity> findByCountry(String country, int start, int end) {
		return findByCountry(country, start, end, null);
	}

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
	@Override
	public List<Celebrity> findByCountry(
		String country, int start, int end,
		OrderByComparator<Celebrity> orderByComparator,
		boolean useFinderCache) {

		return findByCountry(country, start, end, orderByComparator);
	}

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
	@Override
	public List<Celebrity> findByCountry(
		String country, int start, int end,
		OrderByComparator<Celebrity> orderByComparator) {

		country = Objects.toString(country, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByCountry;
			finderArgs = new Object[] {country};
		}
		else {
			finderPath = _finderPathWithPaginationFindByCountry;
			finderArgs = new Object[] {country, start, end, orderByComparator};
		}

		List<Celebrity> list = (List<Celebrity>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Celebrity celebrity : list) {
				if (!country.equals(celebrity.getCountry())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CELEBRITY_WHERE);

			boolean bindCountry = false;

			if (country.isEmpty()) {
				query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
			}
			else {
				bindCountry = true;

				query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(CelebrityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountry) {
					qPos.add(country);
				}

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
	 * Returns the first celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	@Override
	public Celebrity findByCountry_First(
			String country, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		Celebrity celebrity = fetchByCountry_First(country, orderByComparator);

		if (celebrity != null) {
			return celebrity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("country=");
		msg.append(country);

		msg.append("}");

		throw new NoSuchCelebrityException(msg.toString());
	}

	/**
	 * Returns the first celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	@Override
	public Celebrity fetchByCountry_First(
		String country, OrderByComparator<Celebrity> orderByComparator) {

		List<Celebrity> list = findByCountry(country, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity
	 * @throws NoSuchCelebrityException if a matching celebrity could not be found
	 */
	@Override
	public Celebrity findByCountry_Last(
			String country, OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		Celebrity celebrity = fetchByCountry_Last(country, orderByComparator);

		if (celebrity != null) {
			return celebrity;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("country=");
		msg.append(country);

		msg.append("}");

		throw new NoSuchCelebrityException(msg.toString());
	}

	/**
	 * Returns the last celebrity in the ordered set where country = &#63;.
	 *
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching celebrity, or <code>null</code> if a matching celebrity could not be found
	 */
	@Override
	public Celebrity fetchByCountry_Last(
		String country, OrderByComparator<Celebrity> orderByComparator) {

		int count = countByCountry(country);

		if (count == 0) {
			return null;
		}

		List<Celebrity> list = findByCountry(
			country, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the celebrities before and after the current celebrity in the ordered set where country = &#63;.
	 *
	 * @param celebrityId the primary key of the current celebrity
	 * @param country the country
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next celebrity
	 * @throws NoSuchCelebrityException if a celebrity with the primary key could not be found
	 */
	@Override
	public Celebrity[] findByCountry_PrevAndNext(
			long celebrityId, String country,
			OrderByComparator<Celebrity> orderByComparator)
		throws NoSuchCelebrityException {

		country = Objects.toString(country, "");

		Celebrity celebrity = findByPrimaryKey(celebrityId);

		Session session = null;

		try {
			session = openSession();

			Celebrity[] array = new CelebrityImpl[3];

			array[0] = getByCountry_PrevAndNext(
				session, celebrity, country, orderByComparator, true);

			array[1] = celebrity;

			array[2] = getByCountry_PrevAndNext(
				session, celebrity, country, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Celebrity getByCountry_PrevAndNext(
		Session session, Celebrity celebrity, String country,
		OrderByComparator<Celebrity> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CELEBRITY_WHERE);

		boolean bindCountry = false;

		if (country.isEmpty()) {
			query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
		}
		else {
			bindCountry = true;

			query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CelebrityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindCountry) {
			qPos.add(country);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(celebrity)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Celebrity> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the celebrities where country = &#63; from the database.
	 *
	 * @param country the country
	 */
	@Override
	public void removeByCountry(String country) {
		for (Celebrity celebrity :
				findByCountry(
					country, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(celebrity);
		}
	}

	/**
	 * Returns the number of celebrities where country = &#63;.
	 *
	 * @param country the country
	 * @return the number of matching celebrities
	 */
	@Override
	public int countByCountry(String country) {
		country = Objects.toString(country, "");

		FinderPath finderPath = _finderPathCountByCountry;

		Object[] finderArgs = new Object[] {country};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CELEBRITY_WHERE);

			boolean bindCountry = false;

			if (country.isEmpty()) {
				query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_3);
			}
			else {
				bindCountry = true;

				query.append(_FINDER_COLUMN_COUNTRY_COUNTRY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountry) {
					qPos.add(country);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COUNTRY_COUNTRY_2 =
		"celebrity.country = ?";

	private static final String _FINDER_COLUMN_COUNTRY_COUNTRY_3 =
		"(celebrity.country IS NULL OR celebrity.country = '')";

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

		if (!(celebrity instanceof CelebrityModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(celebrity.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(celebrity);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in celebrity proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Celebrity implementation " +
					celebrity.getClass());
		}

		CelebrityModelImpl celebrityModelImpl = (CelebrityModelImpl)celebrity;

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

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {celebrityModelImpl.getProfession()};

			finderCache.removeResult(_finderPathCountByoccupation, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByoccupation, args);

			args = new Object[] {celebrityModelImpl.getCountry()};

			finderCache.removeResult(_finderPathCountByCountry, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCountry, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((celebrityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByoccupation.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					celebrityModelImpl.getOriginalProfession()
				};

				finderCache.removeResult(_finderPathCountByoccupation, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByoccupation, args);

				args = new Object[] {celebrityModelImpl.getProfession()};

				finderCache.removeResult(_finderPathCountByoccupation, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByoccupation, args);
			}

			if ((celebrityModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCountry.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					celebrityModelImpl.getOriginalCountry()
				};

				finderCache.removeResult(_finderPathCountByCountry, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCountry, args);

				args = new Object[] {celebrityModelImpl.getCountry()};

				finderCache.removeResult(_finderPathCountByCountry, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCountry, args);
			}
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

		_finderPathWithPaginationFindByoccupation = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByoccupation",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByoccupation = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByoccupation",
			new String[] {String.class.getName()},
			CelebrityModelImpl.PROFESSION_COLUMN_BITMASK);

		_finderPathCountByoccupation = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByoccupation",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByCountry = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCountry",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCountry = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CelebrityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCountry",
			new String[] {String.class.getName()},
			CelebrityModelImpl.COUNTRY_COLUMN_BITMASK);

		_finderPathCountByCountry = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCountry",
			new String[] {String.class.getName()});
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

	private static final String _SQL_SELECT_CELEBRITY_WHERE =
		"SELECT celebrity FROM Celebrity celebrity WHERE ";

	private static final String _SQL_COUNT_CELEBRITY =
		"SELECT COUNT(celebrity) FROM Celebrity celebrity";

	private static final String _SQL_COUNT_CELEBRITY_WHERE =
		"SELECT COUNT(celebrity) FROM Celebrity celebrity WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "celebrity.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Celebrity exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Celebrity exists with the key {";

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