package com.aibany.hr.module.workovertime.service;

import com.agileai.domain.DataParam;
import com.agileai.domain.DataRow;
import com.agileai.hotweb.bizmoduler.core.StandardServiceImpl;
import com.agileai.hotweb.common.DaoHelper;
import java.util.List;

public class HrWorkOvertimeManageImpl
  extends StandardServiceImpl
  implements HrWorkOvertimeManage
{
  public void updateRecord(DataParam param)
  {
    String statementId = this.sqlNameSpace + "." + "updateRecord";
    processDataType(param, this.tableName);
    this.daoHelper.updateRecord(statementId, param);
  }
  
  public DataRow getNowRecord(DataParam param)
  {
    String statementId = this.sqlNameSpace + "." + "getNowRecord";
    DataRow result = this.daoHelper.getRecord(statementId, param);
    return result;
  }
  
  public void approveRecord(DataParam param) { String statementId = this.sqlNameSpace + "." + "approveRecord";
    processDataType(param, this.tableName);
    this.daoHelper.updateRecord(statementId, param);
  }
  
  public List<DataRow> findOvertimeList(DataParam param)
  {
    String statementId = this.sqlNameSpace + "." + "findOvertimeList";
    List<DataRow> result = this.daoHelper.queryRecords(statementId, param);
    return result;
  }
  
  public void submitRecord(DataParam param)
  {
    String statementId = this.sqlNameSpace + "." + "submitRecord";
    this.daoHelper.updateRecord(statementId, param);
  }
}
