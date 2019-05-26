package org.caps.myshop.commons.web;

import org.caps.myshop.commons.dto.AbstractBaseDomain;
import org.caps.myshop.commons.dto.AbstractBaseResult;
import org.caps.myshop.commons.dto.BaseResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author caps
 * @Date 2019/5/25 21:26
 * @Description
 */
public class AbstractBaseController<T extends AbstractBaseDomain> {

    private static final String ENVIRONMENT_LOGGING_LEVEL_MY_SHOP = "logging.level.org.caps.myshop";

    protected HttpServletResponse response;

    protected HttpServletRequest request;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @ModelAttribute
    public void initReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
    }

    /**
     * 请求成功
     * @param self
     * @param attribute
     * @return
     */
    protected AbstractBaseResult success(String self,T attribute){
        return BaseResultFactory.getInstance(response).build(self,attribute);
    }

    /**
     * 请求成功
     * @param self
     * @param next
     * @param last
     * @param attributes
     * @return
     */
    protected  AbstractBaseResult success(String self, int next, int last, List<T> attributes){
        return BaseResultFactory.getInstance(response).build(self,next,last,attributes);
    }

    /**
     * 请求失败
     * @param title
     * @param detail
     * @return
     */
    protected AbstractBaseResult error(String title, String detail) {
        return error(HttpStatus.UNAUTHORIZED.value(), title, detail);
    }

    /**
     * 请求失败
     * @param code
     * @param title
     * @param detail
     * @return
     */
    protected AbstractBaseResult error(int code, String title, String detail) {
        return BaseResultFactory.getInstance(response).build(code, title, detail, applicationContext.getEnvironment().getProperty(ENVIRONMENT_LOGGING_LEVEL_MY_SHOP));
    }
}
