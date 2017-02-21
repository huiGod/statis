package cn._51app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn._51app.entity.ParamEntity;
import cn._51app.entity.User;
import cn._51app.service.ActiveService;
import cn._51app.util.Constants;

@Controller
@RequestMapping("/active")
public class ActiveController extends BaseController{
	
	@Autowired
	private ActiveService activeService;
	
	
	/**
	 * tengh 2016年11月17日 下午6:30:25
	 * @return
	 * TODO 按渠道、应用查询激活的数据
	 */
	@ResponseBody
	@RequestMapping(value="/channelData",method=RequestMethod.GET)
	public ResponseEntity<String> getChannelData(@ModelAttribute ParamEntity paramEntity) throws Exception{
		return this.resultInfo(this.activeService.getChannelData(paramEntity));
	}
	
	/**
	 * tengh 2017年1月5日 下午5:07:06
	 * @return
	 * TODO 查询账号的渠道
	 */
	@ResponseBody
	@RequestMapping(value="/channel",method=RequestMethod.GET)
	public String getChannelByuser(HttpServletRequest request){
		User user=(User)request.getAttribute(Constants.CURRENT_USER);
		return this.activeService.getChannelByuser(user.getOrganizationId());
	}
	
	/**
	 * tengh 2017年1月5日 下午8:10:29
	 * @param channelId
	 * @return
	 * TODO 按渠道查询应用
	 */
	@ResponseBody
	@RequestMapping(value="/app",method=RequestMethod.GET)
	public String getAppByChannelId(@RequestParam(value="channelId")String channelId){
		return this.activeService.getAppByChannelId(channelId);
	}
	
	/**
	 * tengh 2017年2月13日 上午10:36:59
	 * @param id
	 * @param num
	 * @return
	 * TODO 修改应用对应比例
	 */
	@RequestMapping(value="/editLevel",method=RequestMethod.GET)
	@ResponseBody
	public String editLevel(@RequestParam(value="id")String id,@RequestParam(value="num")String num){
		this.activeService.editLevel(id,num);
		return "true";
	}
}
