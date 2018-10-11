package com.wangjie.bill.web;

import com.wangjie.bill.base.json.JsonUtil;
import com.wangjie.bill.base.protocol.http.HttpResponse;
import com.wangjie.bill.consts.BizResCode;
import com.wangjie.bill.domain.BillRecord;
import com.wangjie.bill.service.BillService;
import com.wangjie.bill.vo.BillRecordRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 发票控制器
 * @author: wangjie@jieshifinance.com
 * @create: 2018-09-12 21:24
 **/
@RestController
@RequestMapping("/bill")
public class BillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillController.class);


    @Resource
    private BillService billService;

    /**
     * 新增发票记录
     */
    @RequestMapping("/insert")
    public HttpResponse insertBill(@RequestBody BillRecord req){
        LOGGER.info("请求参数 {}",JsonUtil.toJson(req));
        if(StringUtils.isBlank(req.getBillCode()) || StringUtils.isBlank(req.getBillNum())
                || StringUtils.isBlank(req.getInputType())){
            return HttpResponse.failure(BizResCode.BILL_CODE_NUM_NOT_EMPTY);
        }
        return billService.insertBillRecord(req);
    }

    /**
     * 查询发票记录
     */
    @RequestMapping("/query")
    public HttpResponse query(@RequestBody BillRecord req){
        LOGGER.info("请求参数 {}",JsonUtil.toJson(req));
        if(StringUtils.isBlank(req.getBillCode()) || StringUtils.isBlank(req.getBillNum())){
            return HttpResponse.failure(BizResCode.BILL_CODE_NUM_NOT_EMPTY);
        }
        return billService.queryBillRecord(req);
    }


    /**
     * 查询发票历史记录
     */
    @RequestMapping("/queryHistory")
    public HttpResponse queryHistory(@RequestBody BillRecordRequest req){
        LOGGER.info("请求参数 {}",JsonUtil.toJson(req));
        return billService.queryHistory(req);
    }


    /**
     * 统计类型 图形统计
     */
    @RequestMapping("/statistics-input-type")
    public HttpResponse statisticsInputType(@RequestBody BillRecordRequest req){
        LOGGER.info("请求参数 {}",JsonUtil.toJson(req));
        if(StringUtils.isBlank(req.getStatisticsInputType())){
            return HttpResponse.failure(BizResCode.STATISTICS_NOT_EMPTY);
        }
        return billService.statisticsInputType(req);
    }

    /**
     * 删除发票记录
     */
    @RequestMapping("/delete-record/{id}")
    public HttpResponse deleteRecord(@PathVariable Integer id){
        LOGGER.info("请求参数 {}",id);
        return billService.deleteRecord(id);
    }
}
