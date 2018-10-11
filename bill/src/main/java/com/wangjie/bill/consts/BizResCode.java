package com.wangjie.bill.consts;

import com.wangjie.bill.base.protocol.MessageId;
import com.wangjie.bill.base.protocol.Project;

/*
 * @Description: 业务结果码
 * @Author      wangjie@jieshifinance.com
 * @Date        2018/8/9 16:37
 * @param
 * @return
 */
public interface BizResCode {

    MessageId ALREADY_BILL = MessageId.create(Project.bill, 1, "此发票已存在！");
    MessageId INSERT_FAIL = MessageId.create(Project.bill, 2, "新增发票失败！");
    MessageId BILL_CODE_NUM_NOT_EMPTY = MessageId.create(Project.bill, 3, "发票代码或号码不能为空！");
    MessageId NOT_BILL_RECORD = MessageId.create(Project.bill, 4, "无此发票记录！");
    MessageId STATISTICS_NOT_EMPTY = MessageId.create(Project.bill, 5, "统计时间段不能为空");
    MessageId DELETE_BILL_RECORD_FAIL = MessageId.create(Project.bill, 6, "删除发票记录失败");

}
