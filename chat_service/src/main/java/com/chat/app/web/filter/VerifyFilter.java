package com.chat.app.web.filter;

import com.us.base.common.controller.VerifyLoginFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by righteyte on 16/8/24.
 */

@WebFilter(filterName = "VerifyFilter",urlPatterns ={"/account/*"})
public class VerifyFilter extends VerifyLoginFilter {


}
