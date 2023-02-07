package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = siteService.getSite();
		System.out.println(vo);
		model.addAttribute("siteVo", vo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String update(SiteVo vo, MultipartFile file) {
		String url = fileuploadService.restore(file);
		vo.setProfile(url);
		siteService.updateSite(vo);
		return "redirect:/admin";
	}
	
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}