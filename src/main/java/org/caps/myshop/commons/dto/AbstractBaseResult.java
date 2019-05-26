package org.caps.myshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caps
 * @Date: 2019/5/15 17:38
 * @Description:
 */
@Data
public abstract class AbstractBaseResult implements Serializable {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class Links{
        private String self;
        private String next;
        private String last;
    }
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class DataBean<T extends AbstractBaseDomain> {
        private String type;
        private Long id;
        private T attributes;
        private T relationships;
        private Links links;
    }
}
