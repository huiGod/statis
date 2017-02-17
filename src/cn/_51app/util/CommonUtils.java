package cn._51app.util;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

	/**
	 * tengh 2016年12月8日 下午2:23:48
	 * @param request
	 * @return
	 * TODO 返回ip地址
	 */
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * tengh 2016年11月22日 下午4:24:12
	 * @param min
	 * @param max
	 * @return
	 * TODO 范围内随机一个double
	 */
	public static double randDoubleByRange(int min,int max){
		return  Double.valueOf(new DecimalFormat("######0.0").format(Math.random()*(max-min)+min));
	}
}
