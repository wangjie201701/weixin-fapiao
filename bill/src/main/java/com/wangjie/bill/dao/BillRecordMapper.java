package com.wangjie.bill.dao;

import com.wangjie.bill.domain.BillRecord;
import com.wangjie.bill.vo.BillRecordRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BillRecordMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BillRecord record);

    int insertSelective(BillRecord record);

    BillRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BillRecord record);

    int updateByPrimaryKey(BillRecord record);

    BillRecord selectByCodeAndNum(BillRecord record);

    List<BillRecord> queryHistory(BillRecordRequest request);

    Integer queryHistoryCount(BillRecordRequest request);

   List statisticsInputType(BillRecordRequest request);
}