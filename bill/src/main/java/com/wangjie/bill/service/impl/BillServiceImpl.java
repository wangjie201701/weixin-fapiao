package com.wangjie.bill.service.impl;

import com.wangjie.bill.base.date.DateCalcUtil;
import com.wangjie.bill.base.json.JsonUtil;
import com.wangjie.bill.base.protocol.http.HttpResponse;
import com.wangjie.bill.consts.BizResCode;
import com.wangjie.bill.dao.BillRecordMapper;
import com.wangjie.bill.domain.BillRecord;
import com.wangjie.bill.service.BillService;
import com.wangjie.bill.utils.DateFormatUtil;
import com.wangjie.bill.vo.BillRecordRequest;
import com.wangjie.bill.vo.base.PageResData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 发票
 * @author: wangjie@jieshifinance.com
 * @create: 2018-09-12 21:26
 **/
@Service
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

    @Resource
    private BillRecordMapper billRecordMapper;

    @Override
    public HttpResponse insertBillRecord(BillRecord billRecord) {
        BillRecord queryPara = new BillRecord();
        queryPara.setBillCode(billRecord.getBillCode());
        queryPara.setBillNum(billRecord.getBillNum());
        BillRecord byCodeAndNum = billRecordMapper.selectByCodeAndNum(queryPara);
        if(byCodeAndNum != null){
            return HttpResponse.failure(BizResCode.ALREADY_BILL,byCodeAndNum);
        }
        billRecord.setCreateTime(new Date());
        int insetcount = billRecordMapper.insertSelective(billRecord);
        if(insetcount >0){
            return HttpResponse.success();
        }else{
            return HttpResponse.failure(BizResCode.INSERT_FAIL);
        }

    }

    @Override
    public HttpResponse queryBillRecord(BillRecord billRecord) {
        BillRecord queryPara = new BillRecord();
        queryPara.setBillCode(billRecord.getBillCode());
        queryPara.setBillNum(billRecord.getBillNum());
        BillRecord byCodeAndNum = billRecordMapper.selectByCodeAndNum(queryPara);
        if(byCodeAndNum == null){
            return HttpResponse.failure(BizResCode.NOT_BILL_RECORD);
        }
        return HttpResponse.success(byCodeAndNum);
    }

    @Override
    public HttpResponse queryHistory(BillRecordRequest req) {
        PageResData pg = new PageResData();
        List<BillRecord>  billRecordList = billRecordMapper.queryHistory(req);
        Integer  count = billRecordMapper.queryHistoryCount(req);
        pg.setDataList(billRecordList);
        pg.setTotalCount(count);
        return HttpResponse.success(pg);
    }

    @Override
    public HttpResponse statisticsInputType(BillRecordRequest req) {
        /**
         * 1.今日  2.昨日  3.本周  4.本月 5. 本年
         */
        Date nowDate = new Date();//当前时间
        Date startTime;//开始时间
        Date endTime;//结束时间
        switch (req.getStatisticsInputType()){
            case "1":
                startTime = DateCalcUtil.getDayBegin(nowDate);
                endTime = DateCalcUtil.getDayEnd(nowDate);
                break;

            case "2":
                startTime = DateCalcUtil.getDayBegin(DateCalcUtil.addDays(nowDate,-1));
                endTime = DateCalcUtil.getDayEnd(DateCalcUtil.addDays(nowDate,-1));
                break;

            case "3":
                startTime = DateCalcUtil.getWeekBegin(nowDate);
                endTime = DateCalcUtil.getWeekEnd(nowDate);
                break;

            case "4":
                startTime = DateCalcUtil.getMonthBegin(nowDate);
                endTime = DateCalcUtil.getMonthEnd(nowDate);
                break;
            case "5":
                startTime = DateFormatUtil.parseDateTime(DateCalcUtil.getYear(nowDate)+"-"+"01-01"+" 00:00:00");
                endTime = nowDate;
                break;
            default:
                startTime = DateCalcUtil.getDayBegin(nowDate);
                endTime = DateCalcUtil.getDayEnd(nowDate);
        }
        LOGGER.info("开始时间：{}",startTime);
        LOGGER.info("结束时间：{}",endTime);

        req.setStartTime(startTime);
        req.setEndTime(endTime);
        LOGGER.info("请求信息 {}",JsonUtil.toJson(req));
        List list = billRecordMapper.statisticsInputType(req);
        Map resultMap = new HashMap();
        for(int i=0;i<list.size();i++){
           Map  map = (Map)list.get(i);
            resultMap.put(map.get("inputtype"),map.get("num"));
        }
        return HttpResponse.success(resultMap);
    }

    /**
     * 删除发票记录
     * @param id
     * @return
     */
    @Override
    public HttpResponse deleteRecord(Integer id) {
        int count = billRecordMapper.deleteByPrimaryKey(id);
        if(count >0){
            return HttpResponse.success();

        }else{
            return HttpResponse.failure(BizResCode.DELETE_BILL_RECORD_FAIL);
        }
    }
}
