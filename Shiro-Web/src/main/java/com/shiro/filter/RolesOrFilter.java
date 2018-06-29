package com.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Crete by Marlon
 * Create Date: 2018/6/29
 * Class Describe
 * <p>
 * AuthorizationFilter 授权过滤器
 * AuthenticationFilter 认证过滤器
 * 只要有其中的任意一个权限就可以访问
 *
 **/

public class RolesOrFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("RolesOrFilter判断用户是否有该角色");
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] roles = (String[]) o;
        if (roles == null || roles.length == 0) {
            return true;
        }
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

}
