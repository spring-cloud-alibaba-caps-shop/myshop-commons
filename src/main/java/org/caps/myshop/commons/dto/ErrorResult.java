package org.caps.myshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author caps
 * @Date: 2019/5/15 17:38
 * @Description:
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult extends AbstractBaseResult {
    private int code;
    private String title;
    private String detail;

}
