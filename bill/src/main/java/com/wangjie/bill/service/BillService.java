package com.wangjie.bill.service;

import com.wangjie.bill.base.protocol.http.HttpResponse;
import com.wangjie.bill.domain.BillRecord;
import com.wangjie.bill.vo.BillRecordRequest;

public interface BillService {
    HttpResponse insertBillRecord(BillRecord billRecord);
    HttpResponse queryBillRecord(BillRecord billRecord);
    HttpResponse queryHistory(BillRecordRequest req);
    HttpResponse statisticsInputType(BillRecordRequest req);

    HttpResponse deleteRecord(Integer id);
}
