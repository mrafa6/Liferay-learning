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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CelebritySoap implements Serializable {

	public static CelebritySoap toSoapModel(Celebrity model) {
		CelebritySoap soapModel = new CelebritySoap();

		soapModel.setCelebrityId(model.getCelebrityId());
		soapModel.setName(model.getName());
		soapModel.setProfession(model.getProfession());
		soapModel.setNickName(model.getNickName());
		soapModel.setCountry(model.getCountry());

		return soapModel;
	}

	public static CelebritySoap[] toSoapModels(Celebrity[] models) {
		CelebritySoap[] soapModels = new CelebritySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CelebritySoap[][] toSoapModels(Celebrity[][] models) {
		CelebritySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CelebritySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CelebritySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CelebritySoap[] toSoapModels(List<Celebrity> models) {
		List<CelebritySoap> soapModels = new ArrayList<CelebritySoap>(
			models.size());

		for (Celebrity model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CelebritySoap[soapModels.size()]);
	}

	public CelebritySoap() {
	}

	public long getPrimaryKey() {
		return _celebrityId;
	}

	public void setPrimaryKey(long pk) {
		setCelebrityId(pk);
	}

	public long getCelebrityId() {
		return _celebrityId;
	}

	public void setCelebrityId(long celebrityId) {
		_celebrityId = celebrityId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getProfession() {
		return _profession;
	}

	public void setProfession(String profession) {
		_profession = profession;
	}

	public String getNickName() {
		return _nickName;
	}

	public void setNickName(String nickName) {
		_nickName = nickName;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	private long _celebrityId;
	private String _name;
	private String _profession;
	private String _nickName;
	private String _country;

}