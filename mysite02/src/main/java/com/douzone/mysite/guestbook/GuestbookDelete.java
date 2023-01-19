package com.douzone.mysite.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class GuestbookDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Long number = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		new GuestbookDao().deleteByNoPassword(number, password);
		
		MvcUtil.redirect(request.getContextPath()+"/guestbook", request, response);
	}

}
