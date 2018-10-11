package com.wangjie.bill.vo;

import com.wangjie.bill.vo.base.PageRequest;

import java.util.Date;

/**
 * @description: 发票请求参数
 * @author: wangjie@jieshifinance.com
 * @create: 2018-09-20 11:30
 **/
public class BillRecordRequest extends PageRequest {

    private String billCode;

    private String billNum;

    private String amount;

    private Date createTime;

    private String billDate;

    private String inputType;
    /**
     * 1.今日  2.昨日  3.本周  4.本月
     */
    private String statisticsInputType;

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getStatisticsInputType() {
        return statisticsInputType;
    }

    public void setStatisticsInputType(String statisticsInputType) {
        this.statisticsInputType = statisticsInputType;
    }
}
