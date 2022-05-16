package com.example.common.filter;

import com.example.common.utils.UserContextHolder;
import com.example.common.utils.UserContextHolder.UserContext;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.factory.InitializingBean;

public abstract class UserContextFilter implements Filter, InitializingBean {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    UserContext userContext = this.getUserContext();
    UserContextHolder.setContext(userContext);

    filterChain.doFilter(servletRequest, servletResponse);
  }

  public abstract UserContext getUserContext();

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
