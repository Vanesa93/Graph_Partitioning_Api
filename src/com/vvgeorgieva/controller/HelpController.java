package com.vvgeorgieva.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vvgeorgieva.errors.StorageFileNotFoundException;
import com.vvgeorgieva.models.FileInput;
import com.vvgeorgieva.services.FileSystemStorageService;
import com.vvgeorgieva.services.GraphService;

import entity.Graph;
import helpers.ProcessFileService;
import javafx.scene.shape.Path;

@Controller
public class HelpController {

    @Autowired
    public HelpController() {
    }

    @RequestMapping("/help")
   	public String help() { 
   		return "help";
   	}
    
    @RequestMapping("/contact")
   	public String contact() { 
   		return "contact";
   	}
}