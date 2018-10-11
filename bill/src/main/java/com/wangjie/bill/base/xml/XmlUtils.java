/**
 *
 */
package com.wangjie.bill.base.xml;


import com.wangjie.bill.base.xml.writer.CDataXMLStreamWriter;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.Map;

/**
 * XML与java bean转换工具
 *
 * @author liyinglong@hanyun.com
 * @date 2016年8月10日 下午3:20:57
 */
public abstract class XmlUtils {
    private static final String DEFAULT_CHAR_SET = "UTF-8";
    private static final Map<String, JAXBContext> CONTEXT_MAP = new HashMap<>();
    private static final XMLOutputFactory XMLOUTPUTFACTORY = XMLOutputFactory.newInstance();

    /**
     * Java bean 转 xml
     * <br>默认编码为UTF-8，不格式化，不显示头部声明
     * @param bean
     * @return
     */
    public static <T> String marshal(T bean) throws Exception {
        return marshal(bean, DEFAULT_CHAR_SET, true);
    }
    
    /**
     * Java bean 转 xml
     *
     * @param bean 
     * @param charset 字符编码集
     * @param fragment 是否不显示头部声明
     * @return
     */
    public static <T> String marshal(T bean, String charset, boolean fragment) throws Exception {
        if (bean == null) {
            return null;
        }
        JAXBContext context = CONTEXT_MAP.get(bean.getClass().getName());
        if (context == null) {
            context = JAXBContext.newInstance(bean.getClass());
            CONTEXT_MAP.put(bean.getClass().getName(), context);
        }
        Marshaller marshal = context.createMarshaller();
        marshal.setProperty(Marshaller.JAXB_ENCODING, charset);
        marshal.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLStreamWriter sw = XMLOUTPUTFACTORY.createXMLStreamWriter(baos, charset);
        CDataXMLStreamWriter cdatasw = new CDataXMLStreamWriter(sw, charset);
        
        marshal.marshal(bean, cdatasw);
        
        return baos.toString(charset);
    }

    /**
     * xml 转 Java bean
     *
     * @param xmlstr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T unmarshal(Class<T> clazz, String xmlstr) throws Exception {
        if (clazz == null || StringUtils.isBlank(xmlstr)) {
            return null;
        }
        JAXBContext context = CONTEXT_MAP.get(clazz.getName());
        if (context == null) {
            context = JAXBContext.newInstance(clazz);
            CONTEXT_MAP.put(clazz.getName(), context);
        }
        Unmarshaller ums = context.createUnmarshaller();

        CharArrayReader careader = new CharArrayReader(xmlstr.toCharArray());
        return (T) ums.unmarshal(careader);
    }
}
