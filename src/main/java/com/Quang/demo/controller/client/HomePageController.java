package com.Quang.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import ch.qos.logback.core.model.Model;

@Controller
public class HomePageController {

  @GetMapping("")
  public String getHomePage() {
    return "client/homepage/show";
  }

  @GetMapping("/product/{id}")
  public String getDetail(Model model, @PathVariable long id) {

    return "client/homepage/detail";
  }

}
