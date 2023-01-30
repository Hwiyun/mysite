package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class BoardWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardVo vo = new BoardVo();
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		UserVo authUser = (UserVo)request.getSession().getAttribute("authUser");
		Long authUserNo = authUser.getNo();
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(authUserNo);
		
		if(request.getParameter("gno") == null) {
			// 게시글
			new BoardDao().insert(vo);
		} else {
			// 댓글
			int gNo = Integer.parseInt(request.getParameter("gno"));
			int oNo = Integer.parseInt(request.getParameter("ono"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			
			vo.setgNo(gNo);
			vo.setoNo(oNo+1);
			vo.setDepth(depth+1);
			
			new BoardDao().replyInsert(vo);

		}
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);

	}

}
