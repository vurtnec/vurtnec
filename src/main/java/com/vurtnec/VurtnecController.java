package com.vurtnec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VurtnecController
{

  @RequestMapping(value={"/welcome"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String welcome() {
	
    return "/welcome";
  }

}