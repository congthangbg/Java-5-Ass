package com.vn.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.vn.entity.Account;

@Component
public class UserAuthentcate implements HandlerInterceptor{
	  @Override
	    public boolean preHandle(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        Object handler
	    ) throws IOException {
	        HttpSession session = request.getSession();
	        Account account = (Account) session.getAttribute("user");
	        if ( account.getAdmin() == 2  ) {
	            response.sendRedirect(request.getContextPath() + "/login");
	            return false;
	        }
	        return true;
	    }
	}

