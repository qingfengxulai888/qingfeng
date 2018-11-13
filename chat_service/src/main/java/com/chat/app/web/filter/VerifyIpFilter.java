package com.chat.app.web.filter;

import com.us.base.util.datawapper.Result;
import com.us.base.util.tool.IpTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xulin on 18/11/08.
 */

@WebFilter(filterName = "VerifyIpFilter",urlPatterns ={"/account/userInfo/*","/account/api/userInfo/*"})
public class VerifyIpFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(VerifyIpFilter.class);

    public VerifyIpFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String ip = IpTool.getIp((HttpServletRequest)request);
        if(ip.equals("127.0.0.1") || ip.equals("123.56.199.12") || ip.equals("10.47.215.188") || ip.equals("101.200.170.15")){
            filterChain.doFilter(request, response);
        }else {
            Result result = new Result();
          //  response.getWriter().print(result.addMessage("Invalid IP.").ExeFaild(401));
            return;
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }


}
