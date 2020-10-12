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

package com.rafa.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.rafa.model.Celebrity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing Celebrity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CelebrityCacheModel
	implements CacheModel<Celebrity>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CelebrityCacheModel)) {
			return false;
		}

		CelebrityCacheModel celebrityCacheModel = (CelebrityCacheModel)obj;

		if (celebrityId == celebrityCacheModel.celebrityId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, celebrityId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{celebrityId=");
		sb.append(celebrityId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", profession=");
		sb.append(profession);
		sb.append(", nickName=");
		sb.append(nickName);
		sb.append(", country=");
		sb.append(country);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Celebrity toEntityModel() {
		CelebrityImpl celebrityImpl = new CelebrityImpl();

		celebrityImpl.setCelebrityId(celebrityId);

		if (name == null) {
			celebrityImpl.setName("");
		}
		else {
			celebrityImpl.setName(name);
		}

		if (profession == null) {
			celebrityImpl.setProfession("");
		}
		else {
			celebrityImpl.setProfession(profession);
		}

		if (nickName == null) {
			celebrityImpl.setNickName("");
		}
		else {
			celebrityImpl.setNickName(nickName);
		}

		if (country == null) {
			celebrityImpl.setCountry("");
		}
		else {
			celebrityImpl.setCountry(country);
		}

		celebrityImpl.resetOriginalValues();

		return celebrityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		celebrityId = objectInput.readLong();
		name = objectInput.readUTF();
		profession = objectInput.readUTF();
		nickName = objectInput.readUTF();
		country = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(celebrityId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (profession == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(profession);
		}

		if (nickName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(nickName);
		}

		if (country == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(country);
		}
	}

	public long celebrityId;
	public String name;
	public String profession;
	public String nickName;
	public String country;

}