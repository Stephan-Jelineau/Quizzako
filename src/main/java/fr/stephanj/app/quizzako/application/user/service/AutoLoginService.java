package fr.stephanj.app.quizzako.application.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AutoLoginService {

	public void login(HttpServletRequest request, HttpServletResponse response, String email);
	
}