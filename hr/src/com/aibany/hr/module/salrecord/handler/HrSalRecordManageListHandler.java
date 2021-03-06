package com.aibany.hr.module.salrecord.handler;

import com.agileai.domain.DataParam;
import com.agileai.domain.DataRow;
import com.agileai.hotweb.domain.core.User;
import com.agileai.hotweb.renders.LocalRenderer;
import com.agileai.hotweb.renders.ViewRenderer;
import com.agileai.util.DateUtil;
import com.aibany.hr.common.CommonHandler;
import com.aibany.hr.common.CommonListHandler;
import com.aibany.hr.common.PrivilegeHelper;
import com.aibany.hr.module.salrecord.service.HrSalRecordManage;
import java.util.Date;
import java.util.List;

public class HrSalRecordManageListHandler
  extends CommonListHandler
{
  public HrSalRecordManageListHandler()
  {
    this.serviceId = buildServiceId(HrSalRecordManage.class);
  }
  
  public ViewRenderer prepareDisplay(DataParam param)
  {
    mergeParam(param);
    initParameters(param);
    setAttributes(param);
    
    User user = (User)getUser();
    PrivilegeHelper privilegeHelper = new PrivilegeHelper(user);
    if (!privilegeHelper.isSalMaster()) {
      param.put("userName", user.getUserCode());
    }
    
    String eti = param.getString("eti");
    if ("ec".equals(eti)) {
      String ec_efn = param.get("ec_efn");
      CommonHandler.writeSystemLog("导出" + ec_efn, getActionType(), this);
    }
    
    List<DataRow> rsList = getService().findRecords(param);
    setRsList(rsList);
    processPageAttributes(param);
    return new LocalRenderer(getPage());
  }
  
  protected void processPageAttributes(DataParam param) {}
  
  protected void initParameters(DataParam param)
  {
    initParamItem(
      param, 
      "sdate", 
      DateUtil.getDateByType(9, 
      DateUtil.getBeginOfYear(new Date())));
    initParamItem(
      param, 
      "edate", 
      DateUtil.getDateByType(9, 
      DateUtil.getDateAdd(new Date(), 1, 1)));
  }
  
  protected HrSalRecordManage getService() {
    return (HrSalRecordManage)lookupService(getServiceId());
  }
}
