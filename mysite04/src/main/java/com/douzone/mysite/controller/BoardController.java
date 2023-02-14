package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	@RequestMapping("/")
//	public String list(Model model) {
//		List<BoardVo> list = boardService.getContents();
//		model.addAttribute("list", list);
//		return "board/list";
//	}
	
	@RequestMapping("/")
	public String index(Model model) {
		Map<String, Object> map = boardService.getContentsList(1, "");
		
		model.addAttribute("map", map);
		model.addAllAttributes(map);
		
		return "board/list";
		
		
	}
	
	@Auth
	@RequestMapping(value =  "/write", method=RequestMethod.GET )
	public String write() {
		
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value =  "/write", method=RequestMethod.POST )
	public String write(Model model, BoardVo vo, HttpSession session) {
		
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		Long authUserNo = authUser.getNo();
		
		vo.setUserNo(authUserNo);
		
//		if(request.getParameter("gno") == null) {
			// 게시글
			boardService.addContents(vo);
//		} else {
//			// 댓글
//			int gNo = Integer.parseInt(request.getParameter("gno"));
//			int oNo = Integer.parseInt(request.getParameter("ono"));
//			int depth = Integer.parseInt(request.getParameter("depth"));
//			
//			vo.setgNo(gNo);
//			vo.setoNo(oNo+1);
//			vo.setDepth(depth+1);
//			
//			new BoardDao().replyInsert(vo);
		return "redirect:/board/";
		
		
	
	}
	
	@RequestMapping("/view")
	public String view(Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/modify", method=RequestMethod.GET)
	public String update(Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/modify";

		
	}
	
	@Auth
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String update(BoardVo vo) {
		boardService.updateContents(vo);
		return "redirect:/board/";
	}
	
	@Auth
	@RequestMapping("/delete")
	public String delete(BoardVo vo) {
		boardService.deleteContents(vo);
		return "redirect:/board/";
	}
}
