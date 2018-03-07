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
public class FileUploadController {

    private final FileSystemStorageService storageService;
    private final GraphService graphService;

    @Autowired
    public FileUploadController(FileSystemStorageService storageService, GraphService graphService) {
        this.storageService = storageService;
        this.graphService = graphService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFileUpload(@ModelAttribute FileInput fileInput,
			HttpServletRequest request,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        storageService.store(fileInput.getFileInput());
        String fileName =  fileInput.getFileInput().getOriginalFilename().toString();
        String separator =  fileInput.getSeparator();
        String dataFormat =  fileInput.getDataFormat();
        Graph graph = graphService.processGraph(fileName, dataFormat, separator);
        String graphFileName = graphService.convertGraphToJsonFile(fileName, graph);
        
        HttpSession session =request.getSession();
        session.setAttribute("graph", graph);
        session.setAttribute("filename", graphFileName);

        redirectAttributes.addFlashAttribute("filename", graphFileName);
		return "redirect:/graph.html";
	}

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    

}