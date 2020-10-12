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

package com.rafa.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Celebrity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Celebrity
 * @generated
 */
@ProviderType
public class CelebrityWrapper
	extends BaseModelWrapper<Celebrity>
	implements Celebrity, ModelWrapper<Celebrity> {

	public CelebrityWrapper(Celebrity celebrity) {
		super(celebrity);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("celebrityId", getCelebrityId());
		attributes.put("name", getName());
		attributes.put("profession", getProfession());
		attributes.put("nickName", getNickName());
		attributes.put("country", getCountry());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long celebrityId = (Long)attributes.get("celebrityId");

		if (celebrityId != null) {
			setCelebrityId(celebrityId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String profession = (String)attributes.get("profession");

		if (profession != null) {
			setProfession(profession);
		}

		String nickName = (String)attributes.get("nickName");

		if (nickName != null) {
			setNickName(nickName);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}
	}

	/**
	 * Returns the celebrity ID of this celebrity.
	 *
	 * @return the celebrity ID of this celebrity
	 */
	@Override
	public long getCelebrityId() {
		return model.getCelebrityId();
	}

	/**
	 * Returns the country of this celebrity.
	 *
	 * @return the country of this celebrity
	 */
	@Override
	public String getCountry() {
		return model.getCountry();
	}

	/**
	 * Returns the name of this celebrity.
	 *
	 * @return the name of this celebrity
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the nick name of this celebrity.
	 *
	 * @return the nick name of this celebrity
	 */
	@Override
	public String getNickName() {
		return model.getNickName();
	}

	/**
	 * Returns the primary key of this celebrity.
	 *
	 * @return the primary key of this celebrity
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the profession of this celebrity.
	 *
	 * @return the profession of this celebrity
	 */
	@Override
	public String getProfession() {
		return model.getProfession();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the celebrity ID of this celebrity.
	 *
	 * @param celebrityId the celebrity ID of this celebrity
	 */
	@Override
	public void setCelebrityId(long celebrityId) {
		model.setCelebrityId(celebrityId);
	}

	/**
	 * Sets the country of this celebrity.
	 *
	 * @param country the country of this celebrity
	 */
	@Override
	public void setCountry(String country) {
		model.setCountry(country);
	}

	/**
	 * Sets the name of this celebrity.
	 *
	 * @param name the name of this celebrity
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the nick name of this celebrity.
	 *
	 * @param nickName the nick name of this celebrity
	 */
	@Override
	public void setNickName(String nickName) {
		model.setNickName(nickName);
	}

	/**
	 * Sets the primary key of this celebrity.
	 *
	 * @param primaryKey the primary key of this celebrity
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the profession of this celebrity.
	 *
	 * @param profession the profession of this celebrity
	 */
	@Override
	public void setProfession(String profession) {
		model.setProfession(profession);
	}

	@Override
	protected CelebrityWrapper wrap(Celebrity celebrity) {
		return new CelebrityWrapper(celebrity);
	}

}