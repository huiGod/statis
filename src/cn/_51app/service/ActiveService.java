package cn._51app.service;

import cn._51app.entity.ParamEntity;

public interface ActiveService {

	/**
	 * tengh 2016年12月20日 下午7:28:45
	 * @return
	 * TODO 通过渠道查询激活的数据
	 */
	String getChannelData(ParamEntity paramEntity) throws Exception;

	/**
	 * tengh 2017年1月5日 下午5:07:22
	 * @param channelId 
	 * @return
	 * TODO 查询账号的渠道
	 */
	String getChannelByuser(long organizationId);

	/**
	 * tengh 2017年1月5日 下午8:10:47
	 * @param channelId
	 * @return
	 * TODO 按渠道查询应用
	 */
	String getAppByChannelId(String channelId);

	/**
	 * tengh 2017年2月13日 上午10:37:11
	 * @param id
	 * @param num
	 * @return
	 * TODO 修改应用对应比例
	 */
	void editLevel(String id, String num);

}
