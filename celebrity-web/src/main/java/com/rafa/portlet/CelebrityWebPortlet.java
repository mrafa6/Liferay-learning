package com.rafa.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.rafa.constants.CelebrityWebPortletKeys;
import com.rafa.model.Celebrity;
import com.rafa.service.CelebrityLocalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
		"javax.portlet.security-role-ref=power-user,user"
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
		//System.out.println("Inside do view of CelebrityWebPortlet........");
		int START=0;
		int END=RECORDS_PER_PAGE-1;
		
		int count = _celebrityLocalService.getCelebritiesCount();
		System.out.println("Total no of Celebrity......"+count);
		
		ArrayList <Celebrity> celebrityList = new ArrayList<Celebrity>(_celebrityLocalService.getCelebrities(QueryUtil.ALL_POS,QueryUtil.ALL_POS ));
		Collections.reverse(celebrityList);
		
		int pageCount = (count % RECORDS_PER_PAGE == 0) ? (count/RECORDS_PER_PAGE) : (count/RECORDS_PER_PAGE)+1;
		
		HttpServletRequest httpRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		
		String envi = StringUtil.toLowerCase(GetterUtil.getString(System
				.getProperty("app.environment.type")));
		System.out.println("Environment........."+envi);
		
		//Displaying celebrity details.
		Celebrity celebrity = (Celebrity) renderRequest.getAttribute("celebrity");
		if(Validator.isNotNull(celebrity)) {
			renderRequest.setAttribute("celebrity", celebrity);
			getPortletContext().getRequestDispatcher("/celebrity-details.jsp").include(renderRequest, renderResponse);
			return;
		}
		

		String pageNo = httpRequest.getParameter("page");
		if(Validator.isNotNull(pageNo) && pageNo != "1" ) {
			START = (Integer.parseInt(pageNo)-1)*RECORDS_PER_PAGE;
			END = count - START > RECORDS_PER_PAGE? (START + RECORDS_PER_PAGE) :count;
		}else {
			pageNo = "1";
		}
		System.out.println("Page no..... "+pageNo);
		System.out.println("START......"+START +".......END........"+END);
		celebrityList = new ArrayList<Celebrity>(celebrityList.subList(START, END));
		
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