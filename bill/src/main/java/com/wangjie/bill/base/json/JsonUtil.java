package com.wangjie.bill.base.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Json 工具类
 */
@SuppressWarnings("WeakerAccess")
public final class JsonUtil {

    public static final String DEFAULT_DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ObjectMapper MAPPER;

    static {
        MAPPER = internalJacksonGenerateMapper(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将json通过类型转换成对象
     * <p/>
     * <pre class="code">
     * {@link JsonUtil JsonUtil}.fromJson("{\"username\":\"username\", \"password\":\"password\"}", User.class);
     * </pre>
     *
     * @param json  json字符串
     * @param clazz 泛型类型
     * @return 返回对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return clazz.equals(String.class) ? (T) json : MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json通过类型转换成对象
     * <p/>
     * <pre class="code">
     * {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
     * </pre>
     *
     * @param json          json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     */
    @SuppressWarnings({"unchecked", "EqualsBetweenInconvertibleTypes"})
    public static <T> T fromJson(String json, com.fasterxml.jackson.core.type.TypeReference<?> typeReference) {
        if (typeReference.getType().equals(String.class)) {
            return (T) json;
        } else {
            try {
                return MAPPER.readValue(json, typeReference);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 将对象转换成json
     * <p/>
     * <pre>
     *     {@link JsonUtil JsonUtil}.toJacksonJson(user);
     * </pre>
     *
     * @param src 对象
     * @return 返回json字符串
     */
    public static <T> String toJson(T src) {
        return internalToJacksonJson(src);
    }

    private static <T> String internalToJacksonJson(T src) {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper internalJacksonGenerateMapper(JsonInclude.Include inclusion) {

        ObjectMapper customMapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        customMapper.setSerializationInclusion(inclusion);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        customMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        customMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        // 所有日期格式都统一为以下样式
        customMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATA_FORMAT));

        return customMapper;
    }
}
