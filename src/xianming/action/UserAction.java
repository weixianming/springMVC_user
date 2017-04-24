package xianming.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xianming.exception.UserException;
import xianming.model.User;
import xianming.service.IUserService;

@Controller("userAction")
@RequestMapping("/user")
public class UserAction {
	
	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute("user")User user){
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated User user,BindingResult br){
		if(br.hasErrors()){
			return "user/add";
		}
		userService.add(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("pages", userService.find());
		return "user/list";
	}
	
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		User u = userService.load(id);
		model.addAttribute("user", u);
		return "user/update";
	}
	
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	public String update(@Validated User user,BindingResult br){
		if(br.hasErrors()){
			return "user/update";
		}
		userService.update(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/{id}/show",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		User u = userService.load(id);
		model.addAttribute("user", u);
		return "user/show";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpSession session){
		User u = userService.login(username);
		if(u==null){
			throw new UserException("用户名不正确");
		}
		if(!u.getPassword().equals(password)){
			throw new UserException("密码不正确");
		}
		session.setAttribute("loginUser", u);
		return "redirect:/user/list";
	}
}
