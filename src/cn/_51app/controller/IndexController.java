package cn._51app.controller;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn._51app.entity.Resource;
import cn._51app.entity.User;
import cn._51app.service.ResourceService;
import cn._51app.service.UserService;
import cn._51app.util.Constants;


/**
 * @author Administrator
 * 首页菜单
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
    	User user=(User)request.getAttribute(Constants.CURRENT_USER);
    	Set<String> permissions = userService.findPermissions(user.getUsername());
    	List<Resource> menus = resourceService.findMenus(permissions);
    	model.addAttribute("menus", menus);
        return "index";
    }
    
    @RequestMapping("/channelData")
    public String channelData(HttpServletRequest request, Model model) {
    	User user=(User)request.getAttribute(Constants.CURRENT_USER);
    	Set<String> permissions = userService.findPermissions(user.getUsername());
    	List<Resource> menus = resourceService.findMenus(permissions);
    	model.addAttribute("menus", menus);
        return "channelData";
    }

    
}
