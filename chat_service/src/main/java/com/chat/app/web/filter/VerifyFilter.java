package com.chat.app.web.filter;

import com.us.base.common.controller.VerifyLoginFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by xulin on 18/11/08.
 */

@WebFilter(filterName = "VerifyFilter",urlPatterns ={"/account/*"})
public class VerifyFilter extends VerifyLoginFilter {


}
