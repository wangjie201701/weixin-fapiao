/**
 * 
 */
package com.wangjie.bill.base.xml.writer;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.regex.Pattern;

/**
 * CDATA处理流
 * @author liyinglong@hanyun.com
 * @date 2016年8月10日 下午8:18:57
 */
public class CDataXMLStreamWriter extends DelegatingXMLStreamWriter {
    private static final String CDATA_START = "<![CDATA[";
    private static final Pattern XML_CHARS = Pattern.compile("[&<>]");
    
    private String charset;

    public CDataXMLStreamWriter(XMLStreamWriter del, String charset) {
        super(del);
        this.charset = charset;
    }

    @Override
    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        super.writeStartDocument(charset, version);
    }
    @Override
    public void writeStartDocument(String version) throws XMLStreamException {
        super.writeStartDocument(charset, version);
    }
    @Override
    public void writeStartDocument() throws XMLStreamException {
        super.writeStartDocument(charset, "1.0");
    }
    
    @Override
    public void writeCharacters(String text) throws XMLStreamException {
        text = text.trim();
        boolean useCData = XML_CHARS.matcher(text).find();
        if (useCData) {
            if(text.startsWith(CDATA_START)){
                text = text.substring(9, text.length() - 3);
            }
            super.writeCData(text);
        } else {
            super.writeCharacters(text);
        }
    }
}
