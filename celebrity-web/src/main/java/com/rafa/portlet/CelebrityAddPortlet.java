package com.rafa.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.rafa.constants.CelebrityWebPortletKeys;
import com.rafa.model.Celebrity;
import com.rafa.service.CelebrityLocalService;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
/**
 * Celebrity Add Portlet
 * @author mrityunjay.e.kumar
 *
 */


@Component(
		immediate=true,
		property= {
				"com.liferay.portlet.display-category=Celebrity",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=CelebrityAdd",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/celebrity-add.jsp",
				"javax.portlet.name=" + CelebrityWebPortletKeys.CELEBRITYADD,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class CelebrityAddPortlet extends MVCPortlet {
	
	@Reference
	private CelebrityLocalService _celebrityLocalService;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.doView(renderRequest, renderResponse);
	}

	@ProcessAction(name="addCelebrity")
	public void addCelebrity(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		System.out.println("Inside Process Action - addCelebrity of CelebrityAddPortlet........");
		
		String _name = ParamUtil.getString(actionRequest, "cel-name");
		String _profession = ParamUtil.getString(actionRequest, "cel-profession");
		String _nickName = ParamUtil.getString(actionRequest, "cel-nickname");
		String _country = ParamUtil.getString(actionRequest, "cel-country");
		
		Celebrity celebrity = _celebrityLocalService.createCelebrity(CounterLocalServiceUtil.increment());
		celebrity.setName(_name);
		celebrity.setProfession(_profession);
		celebrity.setNickName(_nickName);
		celebrity.setCountry(_country);
		
		_celebrityLocalService.addCelebrity(celebrity);
		
		SessionMessages.add(actionRequest, "addedCelebrity");
		
		actionResponse.setRenderParameter("MvcPath", "/celebrity-add.jsp");
//		super.processAction(actionRequest, actionResponse);
	}

}
