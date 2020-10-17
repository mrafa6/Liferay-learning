package com.rafa.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.rafa.model.Celebrity;
import com.rafa.model.impl.CelebrityImpl;
import com.rafa.service.persistence.CelebrityFinder;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CelebrityFinder.class)
public class CelebrityFinderImpl extends CelebrityFinderBaseImpl implements CelebrityFinder{
	
	@Reference
	private CustomSQL customSQL;
	
	
	
	public List<Celebrity> getCelebrityByFirstLetter(String letter){
		Session session = null;
		
		List <Celebrity> celebrityList = Collections.EMPTY_LIST;
		
		try {
			session = openSession();
			String sql = customSQL.get(getClass(), "getCelebrityByLetter");
			System.out.println("sql........."+sql);
			
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setCacheable(Boolean.FALSE);
			sqlQuery.addEntity("Celebrity", CelebrityImpl.class);
			
			QueryPos pos = QueryPos.getInstance(sqlQuery);
			pos.add(letter);
			
			celebrityList = (List<Celebrity>) sqlQuery.list();
			
			return celebrityList;
		}catch (Exception e) {
		}finally {
			closeSession(session);
		}
	return celebrityList;
	}

}
