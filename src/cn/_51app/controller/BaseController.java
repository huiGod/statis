package cn._51app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn._51app.util.PropertiesUtil;

public abstract class BaseController {

	protected final int SUCESS = 200; // 操作成功
	protected final int FAIL = 300; // 操作失败
	protected final int EMPTY = 400; // 空数据
	protected final int SERVER_ERR = 500;// 服务器错误
	protected final int NO_LOGIN = 401; // 未登录
	private final String DEV = PropertiesUtil.getValue("project.dev"); // 0 正式 1
																		// 开发

	/**
	 * @param data
	 *            数据
	 * @param code
	 *            错误码
	 * @param msg
	 *            错误信息
	 * @return json数据格式
	 */
	protected ResponseEntity<String> resultInfo(String data) {
		// 设置httpHeaders请求头信息
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Access-Control-Allow-Origin", "*");// ajax跨域
		// 设置MediaType格式，请求头配置
		MediaType mediaType = new MediaType("text", "html", Charset.forName("UTF-8"));
		// 设置content-type请求头
		responseHeaders.setContentType(mediaType);

		// 1-任何数据，2-请求头对象。3-httpStatus
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(data, responseHeaders, HttpStatus.CREATED);
		return responseEntity;
	}

	/**
	 * tengh 2017年1月16日 下午7:35:04
	 * 
	 * @param request
	 * @param response
	 * @param ex
	 * @return TODO 异常处理
	 */
	@ExceptionHandler(Exception.class)
	public String exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		ex.printStackTrace();
		if (!"XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {// 不是ajax请求
			if (ex instanceof UnauthorizedException) {
				return "unauthorized";
			} else {
				return "error";
			}
		} else {// ajax请求
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (ex instanceof UnauthorizedException) {
				out.println("{flag:false,message:'没有权限'}");
			} else {
				out.println("{flag:false,message:'其他错误'}");
			}
			out.flush();
			out.close();
		}
		return null;
	}
}
