package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = 1;
		int beginPage = 0;
		int endPage = 0;
		int maxPage = 0;
		
		BoardDao dao = new BoardDao();
		
		if(pageNo % 5 == 0) {
			beginPage = (pageNo/5 -1) * 5 + 1;
		} else {
			beginPage = (pageNo/5) * 5 + 1;
		}
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		} else {
			pageNo = 1;
		}
		
		endPage = beginPage + 4;
		
		if(dao.totalCount() % 5 == 0) {
			maxPage = dao.totalCount()/5;
		} else {
			maxPage = dao.totalCount()/5 + 1;
		}
		List<BoardVo> result = new BoardDao().findPageByNo(pageNo);
		for(BoardVo vo: result) {
			System.out.println(vo);
		}
		
		request.setAttribute("list", result);
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("currentPage", pageNo);
		System.out.println(beginPage + ":" + endPage + ":" + maxPage);
		
		MvcUtil.forward("/board/list", request, response);
	}

}
