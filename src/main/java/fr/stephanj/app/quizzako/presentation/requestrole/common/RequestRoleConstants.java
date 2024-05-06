package fr.stephanj.app.quizzako.presentation.requestrole.common;

import fr.stephanj.app.quizzako.presentation.admin.controller.common.AdminConstants;

public class RequestRoleConstants {
	
	//URL's
	public static final String ROLE_URL = "/role";
	public static final String REQUEST_ROLE_URL = ROLE_URL + "/request";
	public static final String SHOW_REQUESTS_URL = AdminConstants.ROLE_ADMIN_URL + "/requests";
	
	//Pages
	public static final String SHOW_REQUESTS_PAGE = AdminConstants.ADMIN_ROOT_PAGE + "/role/show_requests";
}
