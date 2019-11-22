package cl.utem.dist.proyecto.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public class SimpleCORSFilter implements Filter {

    private static final long serialVersionUID = 6691621384503402496L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nada
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String originOptional = StringUtils.trimToEmpty(request.getHeader("origin"));
        String origin = originOptional.replace("*", "");
        LOGGER.debug("origin=" + origin);

        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-APP-KEY");

        // Just ACCEPT and REPLY OK if OPTIONS
        if (StringUtils.equalsIgnoreCase("OPTIONS", request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //nada
    }

}
