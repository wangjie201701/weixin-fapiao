/**
 * 
 */
package com.wangjie.bill.base.xml.adapter;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import com.wangjie.bill.base.date.DateFormatUtil;

/**
 * 日期时间适配，格式:yyyyMMddHHmmssSSS
 * @author liyinglong@hanyun.com
 * @date 2016年8月10日 下午4:40:19
 */
public class DatetimeAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        return DateFormatUtil.parseDateTimeNoSep(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return DateFormatUtil.formatDateTimeNoSep(v);
    }

}
