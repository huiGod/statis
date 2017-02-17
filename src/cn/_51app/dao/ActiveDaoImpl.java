package cn._51app.dao;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn._51app.entity.ParamEntity;

@Repository
public class ActiveDaoImpl implements ActiveDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Map<String, Object>> getChannelData(int page, int number,ParamEntity entity) {
    	//时间默认今天
    	String time="";
    	if(StringUtils.isNotBlank(entity.getStartTime())){
    		time+=" `ci`.`time` >='"+entity.getStartTime()+" 00:00:00' AND `ci`.`time`<='"+entity.getEndTime()+" 23:59:59' ";
    	}else{
    		time+=" TIMESTAMPDIFF(day,CURDATE(),DATE_FORMAT(`ci`.`time`,'%Y-%m-%d'))=0 ";
    	}
    	//渠道 
    	String channel="";
    	if(StringUtils.isBlank(entity.getChannel())){
    		return null;
    	}else{
    		if("1".equals(entity.getChannel())){//51app
    			channel+=" AND `ci`.`channel`>10000 ";
    		}else if(!"-1".equals(entity.getChannel())){
    			channel+=" AND `ci`.`channel`="+entity.getChannel()+" ";
    		}
    	}
    	//应用
    	String appId="";
    	//分组
    	String groupBy="";
    	if(StringUtils.isNotBlank(entity.getAppId())){
    		appId+=" AND `ci`.`appid`="+entity.getAppId()+" ";
    		groupBy+=" GROUP BY DATE_FORMAT(`ci`.`time`, '%Y-%m-%d') ";
    	}else{
    		groupBy+=" GROUP BY `ca`.`id` ";
    	}
    	StringBuilder sql=new StringBuilder();
    	sql.append(" SELECT `ca`.`id`,`ca`.`name`, COUNT(*) AS `click`, SUM(`ci`.`status`) AS `active`, DATE_FORMAT(`ci`.`time`, '%Y-%m-%d') AS `time`,`ca`.`level` FROM `cpa_idfa` `ci` INNER JOIN `cpa_appid` `ca`  ON `ca`.`id` = `ci`.`appid` WHERE "+time+" "+channel+" "+appId+" "+groupBy+"");
    	sql.append(" LIMIT ?,? ");
    	return this.jdbcTemplate.queryForList(sql.toString(),new Object[]{page*number,number});
    }
    
    @Override
    public List<Map<String, Object>> getAppByChannelId(String channelId) {
    	//渠道 
    	String channel="";
    	if("1".equals(channelId)){//51app
			channel+=" AND `channel`>10000 ";
		}else if(!"-1".equals(channelId)){
			channel+=" AND `channel`="+channelId+" ";
		}
    	return this.jdbcTemplate.queryForList("SELECT `ca`.`id`,`ca`.`name` FROM `cpa_appid` `ca` WHERE EXISTS(SELECT 1 FROM `cpa_idfa` WHERE `appid`=`ca`.`id` "+channel+") ORDER BY `ca`.`id` DESC");
    }
    
    @Override
    public void editLevel(String id, String num) {
    	this.jdbcTemplate.update("UPDATE `cpa_appid` SET `level`=? WHERE `id`=?",new Object[]{num,id});
    }
}
