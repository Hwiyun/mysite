package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			action = new BoardWriteForm();
		} else if ("write".equals(actionName)) {
			action = new BoardWrite();
		} else if ("list".equals(actionName)) {
			action = new BoardList();
		} else if ("viewform".equals(actionName)) {
			action = new BoardViewForm();
		} else if ("modifyform".equals(actionName)) {
			action = new BoardModifyForm();
		} else if ("modify".equals(actionName)) {
			action = new BoardModifyAction();
		} else if ("delete".equals(actionName)) {
			action = new BoardDelete();		
		} else if ("replyform".equals(actionName)) {
			action = new BoardReplyForm();		
		} else {
			action = new BoardList();
		}
		return action;
	}

}
