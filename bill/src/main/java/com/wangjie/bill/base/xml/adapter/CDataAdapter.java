/**
 * 
 */
package com.wangjie.bill.base.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * CData适配
 * @author liyinglong@hanyun.com
 * @date 2016年8月10日 下午4:54:20
 */
public class CDataAdapter extends XmlAdapter<String, String> {

    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        return "<![CDATA[" + v + "]]>";
    }

}
