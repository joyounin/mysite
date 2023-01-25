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
		} else {
			action = new ListAction();
		}
		return action;
	}
}
