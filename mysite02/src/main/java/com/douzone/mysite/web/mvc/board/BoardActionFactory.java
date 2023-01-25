package com.douzone.mysite.web.mvc.board;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {
	
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("list".equals(actionName)) {
			action = new ListAction();
		} else if("등록".equals(actionName)) {
			action = new NewBookAction();
		}else {
			action = new ListAction();
		}
		return action;
	}
}
