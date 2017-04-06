package com.practice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

  @RequestMapping(path = { "/", "/home" }, method = RequestMethod.GET)
  public String getHomePage(ModelMap model) {
    model.addAttribute("greeting", "Welcome to Home Page");
    return "welcome";
  }

  @RequestMapping(path = "/admin", method = RequestMethod.GET)
  public String getAdminPage(ModelMap model) {
    model.addAttribute("user", getPrincipal());
    return "/admin";
  }

  @RequestMapping(path = "/dev", method = RequestMethod.GET)
  public String getDevPage(ModelMap model) {
    model.addAttribute("user", getPrincipal());
    return "/dev";
  }

  @RequestMapping(path = "/db", method = RequestMethod.GET)
  public String getDbaPage(ModelMap model) {
    model.addAttribute("user", getPrincipal());
    return "/dba";
  }

  @RequestMapping(path = "/accessDenied", method = RequestMethod.GET)
  public String getAccessDeniedPage(ModelMap model) {
    model.addAttribute("user", getPrincipal());
    return "/access_denied";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String loginPage() {
    return "login";
  }

  @RequestMapping(path = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest req, HttpServletResponse res) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null) {
      new SecurityContextLogoutHandler().logout(req, res, auth);
    }
    return "redirect:/login?logout";
  }


  private String getPrincipal() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }

}
