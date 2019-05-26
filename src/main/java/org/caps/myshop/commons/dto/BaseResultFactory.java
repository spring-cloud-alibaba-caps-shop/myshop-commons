package org.caps.myshop.commons.dto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author caps
 * @Date: 2019/5/15 17:52
 * @Description:
 */
public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static String LOGGER_LEAVE_DEBUG ="DEBUG";

    private static BaseResultFactory baseResultFactory;

    // 设置通用的响应
    private static HttpServletResponse response;

    private BaseResultFactory(){}

    public static BaseResultFactory getInstance(HttpServletResponse response){
        if(baseResultFactory==null){
            synchronized (BaseResultFactory.class){
                if (baseResultFactory==null){
                    baseResultFactory=new BaseResultFactory();
                }
            }
        }
        BaseResultFactory.response=response;
        baseResultFactory.initResponse();
        return baseResultFactory;
    }

    /**
     * 构建单笔数据结果集
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self,T attributes){
        return new SuccessResult(self,attributes);
    }
    /**
     * 构建多笔数据结果集
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributes){
        return new SuccessResult(self,next,last,attributes);
    }

    /**
     * 构建请求错误的响应结构
     * @param code
     * @param detail
     * @param title
     * @param level 日志级别，有debug才显示
     * @return
     */
    public AbstractBaseResult build(int code,String title,String detail,String level){
        // 设置请求失败的响应码
        response.setStatus(code);

        if(LOGGER_LEAVE_DEBUG.equals(level)) {
            return new ErrorResult(code, title, detail);
        }else {
            return new ErrorResult(code,title,null);
        }
    }
    /**
     * 初始化 HttpServletResponse
     */
    private void initResponse() {
        // 需要符合 JSON API 规范
        response.setHeader("Content-Type", "application/vnd.api+json");
    }
}
