package cn._51app.dao;

import java.util.List;
import java.util.Map;

import cn._51app.entity.ParamEntity;

public interface ActiveDao {

	/**
	 * tengh 2017年1月5日 下午5:08:51
	 * @param page
	 * @param number
	 * @param paramEntity
	 * @return
	 * TODO 按渠道、应用查询报表数据
	 */
	List<Map<String, Object>> getChannelData(int page, int number, ParamEntity paramEntity);

	/**
	 * tengh 2017年1月5日 下午8:11:26
	 * @param channelId
	 * @return
	 * TODO 查询渠道的应用
	 */
	List<Map<String, Object>> getAppByChannelId(String channelId);

	/**
	 * tengh 2017年2月13日 上午10:40:33
	 * @param id
	 * @param num
	 * @return
	 * TODO 修改应用比例
	 */
	void editLevel(String id, String num);
}

