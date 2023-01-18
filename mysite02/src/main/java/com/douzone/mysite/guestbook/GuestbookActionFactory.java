package com.douzone.mysite.guestbook;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.mysite.web.mvc.user.JoinAction;
import com.douzone.mysite.web.mvc.user.JoinSuccessAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new GuestbooklistAction();
		} else if("insert".equals(actionName)) {
			action = new GuestbookInsertAction();
		} else if("deleteform".equals(actionName)) {
			action = new GuestbookDeleteForm();
		} else if("delete".equals(actionName)) {
			action = new GuestbookDelete();
		} else {
			action = new MainAction();
		}
		return action;
	}

}
