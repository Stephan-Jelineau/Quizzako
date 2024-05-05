package fr.stephanj.app.quizzako.presentation.user.controller.common;

public class UserConstants {
	
	//URL's
	public static final String USER_REGISTER_URL =  "/register";
	public static final String USER_ACCOUNT_URL = "/account";
	public static final String USER_DELETE_URL =  USER_ACCOUNT_URL + "/delete";
	
	//Pages
	public static final String VIEW_USER_PAGE = "user/view_user";
	public static final String USER_REGISTER_PAGE = "user/register_user";
	
	//Messages
	public static final String SUCCESS_FLASH_ATTR = "successMessage";
	
	//Errors code
	public static final String ERROR_CODE_PASSWORD_MUST_MATCH = "user.passwordsMustMatch";
}
