package cn._51app.service;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn._51app.dao.ActiveDao;
import cn._51app.dao.OrganizationDao;
import cn._51app.entity.Organization;
import cn._51app.entity.ParamEntity;
import cn._51app.entity.ResultEntity;
import cn._51app.util.DateUtil;
import cn._51app.util.JSONUtil; 

@Service
public class ActiveServiceImpl implements ActiveService {
	
	@Autowired
	private ActiveDao activeDao;
	@Autowired
	private OrganizationDao organizationDao;
	
	@Override
	public String getChannelData(ParamEntity paramEntity) throws ParseException {
		ResultEntity resultEntity=new ResultEntity();
		int page=paramEntity.getStart();
		int number=paramEntity.getLength();
		List<Map<String, Object>> data=new ArrayList<>();
		if(StringUtils.isNotBlank(paramEntity.getChannel())){
			data=this.activeDao.getChannelData(page,number,paramEntity);
		}
		if(data!=null && !data.isEmpty()){
			resultEntity.setRecordsTotal(data.size());
			resultEntity.setRecordsFiltered(data.size());
			for (Map<String, Object> map : data) {
				if(StringUtils.isBlank(paramEntity.getStartTime())){
					map.put("time", DateUtil.date2String(new Date(), DateUtil.FORMAT_DATE));
				}else if(paramEntity.getStartTime().equals(paramEntity.getEndTime())){
					map.put("time", paramEntity.getEndTime());
				}else{
					//没有应用显示时间段
					if(StringUtils.isBlank(paramEntity.getAppId())){
						map.put("time", paramEntity.getStartTime()+" to "+paramEntity.getEndTime());
					}
				}
			}
		}
		resultEntity.setDraw(paramEntity.getDraw());
		resultEntity.setData(data);
		return JSONUtil.convertObjectToJSON(resultEntity);
	}
	
	@Override
	public String getChannelByuser(long organizationId) {
		List<Organization> result=new ArrayList<>();
		if(organizationId!=-1){
			Organization organization=this.organizationDao.findOne(organizationId);
			result.add(organization);
		}else{
			result=this.organizationDao.findAll();
		}
		return JSONUtil.convertArrayToJSON(result);
	}
	
	@Override
	public String getAppByChannelId(String channelId) {
		List<Map<String, Object>> data=this.activeDao.getAppByChannelId(channelId);
		return JSONUtil.convertArrayToJSON(data);
	}
	
	@Override
	public void editLevel(String id, String num) {
		this.activeDao.editLevel(id,num);
	}
}
