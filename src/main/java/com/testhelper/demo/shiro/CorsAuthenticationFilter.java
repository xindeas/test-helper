package com.testhelper.demo.shiro;

import com.alibaba.fastjson.JSON;
import com.testhelper.demo.utils.ConstUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class CorsAuthenticationFilter extends FormAuthenticationFilter {

    public CorsAuthenticationFilter() {
        super();
    }

    /**
     * 允许访问的操作
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String options = "OPTIONS";
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (options.equals(((HttpServletRequest) request).getMethod().toUpperCase())) {
            return true;
        }
//    }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 拒绝访问的操作
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("code", ConstUtils.CODE_EXPIRED);
        map.put("msg", "登录状态已过期");
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }
}
