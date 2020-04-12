package com.cqjtu.sc.gateway.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyShiroFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest){
            if (((HttpServletRequest)request).getMethod().toUpperCase().equals("OPTIONS")){
                return true;
            }
        }
        if (request.getAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID)!=null)
            return true;
        return false;
    }
}
