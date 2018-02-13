
package com.vvgeorgieva.controller;
 
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vvgeorgieva.models.PartitionsInput;
import com.vvgeorgieva.services.PartitionsService;

import classic_algorithms.FiducciaMattheysesAlgorithm;
import classic_algorithms.KernighanLinAlgorithm;
import entity.Graph;
import evolutionary_approach.EvolutionaryApproach;
import naive_approach.NaiveApproach;

@Controller
public class PartitionsController {
 
	static String kernighanLinApproach = new String("klin");
	static String fiducciaMattheysesApproach = new String("fmat");
	static String evolutionaryApproach = new String("evol");
	static String naiveApproach = new String("naive");
    public String location = "D:\\saved\\";

    private final PartitionsService partitionsService;

    @Autowired
    public PartitionsController(PartitionsService partitionsService) {
        this.partitionsService = partitionsService;
    }
    
    @RequestMapping("/partitions")
	public String graph(@ModelAttribute("firstPartitionFileName") String firstPartitionFileName, @ModelAttribute("secondPartitionFileName") String secondPartitionFileName,
			Model model) { 
		model.addAttribute("firstPartitionFileName", firstPartitionFileName);
		model.addAttribute("secondPartitionFileName", secondPartitionFileName);
	    return "partitions";
	}

	 @RequestMapping(value = "/partitioning", method = RequestMethod.POST)
		public String handlePartitioning(@ModelAttribute PartitionsInput partitionsInput,
				HttpServletRequest request,
	            HttpServletResponse response,
				RedirectAttributes redirectAttributes) {
		 
				HttpSession session =request.getSession();
		        Graph graph = (Graph)session.getAttribute("graph");
         		String approach = partitionsInput.getApproach();
		 		Graph firstPartition = new Graph();
		 		Graph secondPartition = new Graph();	
		 		
		  		if(approach.equalsIgnoreCase(kernighanLinApproach))	{
		  			KernighanLinAlgorithm kl = partitionsService.kernighanLin(graph);	
		  			firstPartition = partitionsService.convertVertexGroupToGraph(kl.getGroupA());
		  			secondPartition = partitionsService.convertVertexGroupToGraph(kl.getGroupB());
		  		}
				else if (approach.equalsIgnoreCase(fiducciaMattheysesApproach)) {
					FiducciaMattheysesAlgorithm fm = partitionsService.fidMattheyses(graph);	
					firstPartition = partitionsService.convertVertexGroupToGraph(fm.getBestPartitionA());
		  			secondPartition = partitionsService.convertVertexGroupToGraph(fm.getBestPartitionB());
				}
				else if (approach.equalsIgnoreCase(evolutionaryApproach)) {
					EvolutionaryApproach ea = partitionsService.evolApproach(graph);	
					firstPartition = partitionsService.convertVertexGroupToGraph(ea.partition1);
		  			secondPartition = partitionsService.convertVertexGroupToGraph(ea.partition2);
				}
				else if (approach.equalsIgnoreCase(naiveApproach)) {
					NaiveApproach na = null;
					try {
						na = partitionsService.naiveApproach(graph);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					firstPartition = partitionsService.convertVertexGroupToGraph(na.getGroupA());
		  			secondPartition = partitionsService.convertVertexGroupToGraph(na.getGroupB());
				}
		  		
		  		String partitionAFileName = "partition" + 1 + ".json";
		        String firstPartitionFileName = partitionsService.convertGraphToJsonFile(partitionAFileName, firstPartition);
		        redirectAttributes.addFlashAttribute("firstPartitionFileName", firstPartitionFileName);
		        session.setAttribute("partition1FileName", firstPartitionFileName);

		        String partitionBFileName = "partition" + 2 + ".json";
		        String secondPartitionFileName = partitionsService.convertGraphToJsonFile(partitionBFileName, secondPartition);
		        redirectAttributes.addFlashAttribute("secondPartitionFileName", secondPartitionFileName);
		        session.setAttribute("partition2FileName", secondPartitionFileName);

		        return "redirect:/partitions.html";
		}
	 
	 @RequestMapping(value = "/partitioning/export", method = RequestMethod.POST)
		public void handleExport(HttpServletRequest request,
	            HttpServletResponse response,
				RedirectAttributes redirectAttributes) throws IOException {
		 
		 	HttpSession session =request.getSession();	 	
		    
		 	//Create list for file URLs - these are files from all different locations
		    List<String> filenames = new ArrayList<String>();
		    
		    filenames.add((String) session.getAttribute("filename"));      		
		    filenames.add((String) session.getAttribute("partition2FileName"));
		    filenames.add((String) session.getAttribute("partition1FileName"));
		   
		    byte[] buf = new byte[2048];
		    // Create the ZIP file
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ZipOutputStream out = new ZipOutputStream(baos);
		    // Compress the files
		    for (int i=0; i<filenames.size(); i++) {
			    FileInputStream fis = new FileInputStream(location + filenames.get(i).toString());
			    BufferedInputStream bis = new BufferedInputStream(fis);
			    // Add ZIP entry to output stream.
			    File file = new File(location + filenames.get(i).toString());
			    String entryname = file.getName();
			    out.putNextEntry(new ZipEntry(entryname));
			    int bytesRead;
			    while ((bytesRead = bis.read(buf)) != -1) {
			    	out.write(buf, 0, bytesRead);
			    }
			    out.closeEntry();
			    bis.close();
			    fis.close();
		    }
		    out.flush();
		    baos.flush();
		    out.close();
		    baos.close();
		    ServletOutputStream sos =  response.getOutputStream();
		    response.setContentType("application/zip");
		    response.setHeader("Content-Disposition", "attachment; filename=\"Results.ZIP\"");
		    sos.write(baos.toByteArray());
		    out.flush();
		    out.close();
		    sos.flush();
		    

//		    return "redirect:/partitions.html";
		}
}