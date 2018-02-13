
package com.vvgeorgieva.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Graph;

@Controller
public class GraphController {
	
	@RequestMapping("/graph")
	public String graph(@ModelAttribute("filename") String filename, @ModelAttribute("graph") Graph graph,
			Model model) { 
		model.addAttribute("filename", filename);
		model.addAttribute("graph", graph);
	    return "graph";
	}
}