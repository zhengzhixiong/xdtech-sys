package com.xdtech.sys.controller;

import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/session.do")
public class SessionController {
	@Autowired
    private SessionDAO sessionDAO;
    @RequestMapping(params = "sessionList")
    public String sessionList(Model model) {
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        model.addAttribute("sessions", sessions);
        model.addAttribute("sessionCount", sessions.size());
        return "sys/session/list";
    }
}
