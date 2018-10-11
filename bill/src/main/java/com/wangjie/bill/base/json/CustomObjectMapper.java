/**
 * 
 */
package com.wangjie.bill.base.json;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

/**
 * 定制ObjectMapper，在spring mvc中引用
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月7日 下午3:22:17
 */
public class CustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 2422384875387894735L;
    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public CustomObjectMapper() {
        super();
        customProps();
    }

    public CustomObjectMapper(JsonFactory jf) {
        super(jf);
        customProps();
    }

    public CustomObjectMapper(JsonFactory jf, DefaultSerializerProvider sp, DefaultDeserializationContext dc) {
        super(jf, sp, dc);
        customProps();
    }

    /**
     * 定制属性
     */
    private void customProps() {
        // 设置输出时包含属性的风格
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        this.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        // 所有日期格式都统一为以下样式
        this.setDateFormat(new SimpleDateFormat(DEFAULT_DATA_FORMAT));
    }
}
