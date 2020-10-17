package com.rafa.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.rafa.constants.CelebrityWebPortletKeys;
import com.rafa.model.Celebrity;
import com.rafa.service.CelebrityLocalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author mrityunjay.e.kumar
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Celebrity",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CelebrityWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/celebrity-home.jsp",
		"javax.portlet.name=" + CelebrityWebPortletKeys.CELEBRITYWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false"
		
	},
	service = Portlet.class
)
public class CelebrityWebPortlet extends MVCPortlet {
	
	private final static int RECORDS_PER_PAGE = 10;
	
	@Reference
	private CelebrityLocalService _celebrityLocalService;
	
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		int START=0;
		int END=RECORDS_PER_PAGE;
		int count = 0;

		ArrayList <Celebrity> celebrityList;
		
		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		//Displaying celebrity details.
		Celebrity celebrity = (Celebrity) renderRequest.getAttribute("celebrity");
		if(Validator.isNotNull(celebrity)) {
			renderRequest.setAttribute("celebrity", celebrity);
			getPortletContext().getRequestDispatcher("/celebrity-details.jsp").include(renderRequest, renderResponse);
			return;
		}
		
		if(Validator.isNotNull(renderRequest.getAttribute("celebrityList"))) {
			celebrityList = (ArrayList<Celebrity>) renderRequest.getAttribute("celebrityList");
			count = celebrityList.size();
		}else {
			celebrityList = new ArrayList<Celebrity>(_celebrityLocalService.getCelebrities(QueryUtil.ALL_POS,QueryUtil.ALL_POS ));
			count = _celebrityLocalService.getCelebritiesCount();
		}
		
		Collections.reverse(celebrityList);
		System.out.println("Total no of Celebrity......"+count);
		
		int pageCount = (count % RECORDS_PER_PAGE == 0) ? (count/RECORDS_PER_PAGE) : (count/RECORDS_PER_PAGE)+1;
		
		String pageNo = httpRequest.getParameter("page");
		if(Validator.isNotNull(pageNo) && pageNo != "1" ) {
			System.out.println("aaaaaaaaa");
			START = (Integer.parseInt(pageNo)-1)*RECORDS_PER_PAGE;
			END = count - START > RECORDS_PER_PAGE? (START + RECORDS_PER_PAGE) :count;
		}else {
			System.out.println("bbbbbbbb");
			pageNo = "1";
			END = count >= RECORDS_PER_PAGE ? RECORDS_PER_PAGE : count;
		}
		System.out.println("Page no..... "+pageNo);
		System.out.println("START......"+START +".......END........"+END);
		celebrityList = new ArrayList<Celebrity>(celebrityList.subList(START, END));
		System.out.println("celebrity list size..."+celebrityList.size());
		renderRequest.setAttribute("celbCount", count);
		renderRequest.setAttribute("pageCount", pageCount);
		renderRequest.setAttribute("celebrities", celebrityList);
		renderRequest.setAttribute("currPage", pageNo != "1"?pageNo:"1");
		
		renderRequest.getAttribute("page");
		super.doView(renderRequest, renderResponse);
		
	}
	
	
	@ProcessAction(name="deleteCelebrity")
	public void deleteCelebrity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		String celebrityId = PortalUtil.getOriginalServletRequest(request).getParameter("celebrityId");
		System.out.println("Celebrity id .........."+celebrityId);
		try {
			_celebrityLocalService.deleteCelebrity(Long.parseLong(celebrityId));
			SessionMessages.add(actionRequest, "deletedCelebrity");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		actionResponse.setRenderParameter("MvcPath", "/celebrity-home.jsp");
//		super.processAction(actionRequest, actionResponse);
	}
	
	@ProcessAction(name="viewDetails")
	public void viewDetails(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		String celebrityId = PortalUtil.getOriginalServletRequest(request).getParameter("id");
		System.out.println("Celebrity id .........."+celebrityId);
		try {
			if(Validator.isNotNull(celebrityId)) {
			Celebrity celebrity = _celebrityLocalService.fetchCelebrity(Long.parseLong(celebrityId));
			actionRequest.setAttribute("celebrity", celebrity);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ProcessAction(name="searchCelebrity")
	public void searchCelebrity(ActionRequest actionRequest, ActionResponse actionResponse) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		String criteria = PortalUtil.getOriginalServletRequest(request).getParameter("findBy");
		String keyword = PortalUtil.getOriginalServletRequest(request).getParameter("q");
		
		List <Celebrity> celList = Collections.EMPTY_LIST;
		
		if(Validator.isNotNull(keyword) && keyword.length() == 1) {
			celList = _celebrityLocalService.findByFirstLetter(keyword+StringPool.PERCENT);
		}else if(criteria.equalsIgnoreCase("profession")) {
			celList =  _celebrityLocalService.findByProfession(keyword);
		}else if(criteria.equalsIgnoreCase("country")) {
			celList =  _celebrityLocalService.findByCountry(keyword);
		}
		
		ArrayList <Celebrity> celebrityList = new ArrayList<Celebrity> (celList);
		actionRequest.setAttribute("celebrityList",celebrityList );

	}
	
	//Handled through renderURL so no more required.
	/*
	 * @ProcessAction(name="pageNavigate") public void pageNavigate(ActionRequest
	 * actionRequest, ActionResponse actionResponse) throws PortletException{
	 * 
	 * HttpServletRequest httpRequest =
	 * PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(
	 * actionRequest));
	 * 
	 * String pageNo = httpRequest.getParameter("page");
	 * actionResponse.setRenderParameter("page", pageNo);
	 * 
	 * System.out.println("Page no..... "+pageNo);
	 * 
	 * 
	 * }
	 */
	
	

}