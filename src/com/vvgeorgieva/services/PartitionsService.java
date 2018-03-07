package com.vvgeorgieva.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

import classic_algorithms.FiducciaMattheysesAlgorithm;
import classic_algorithms.KernighanLinAlgorithm;
import entity.Graph;
import entity.VertexGroup;
import evolutionary_approach.EvolutionaryApproach;
import helpers.VertexGroupToGraphConverterService;
import jsonConverter.ObjectToJsonService;
import naive_approach.NaiveApproach;
@Service
public class PartitionsService {

	static String kernighanLinApproach = new String("klin");
	static String fiducciaMattheysesApproach = new String("fmat");
	static String evolutionaryApproach = new String("evol");
	static String naiveApproach = new String("naive");
	
    private final Path rootLocation;
    public String location = "D:\\saved\\";
    
    public PartitionsService() {
        this.rootLocation = Paths.get(getLocation());
    }
    

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
	public Graph convertVertexGroupToGraph(VertexGroup vertexGroup) {
		return VertexGroupToGraphConverterService.convertVertexGroupToGraph(vertexGroup);
	}
    
	public String convertGraphToJsonFile(String fileName, Graph graph) {
		String graphFileName = fileName.substring(0, fileName.indexOf('.')) + ".json";
		ObjectToJsonService.convertObjectToJson(location, graphFileName, graph);
		return graphFileName;		
	}
	
	public KernighanLinAlgorithm kernighanLin(Graph graph) {
		return new KernighanLinAlgorithm(graph);		
	}
	
	public FiducciaMattheysesAlgorithm fidMattheyses(Graph graph) {
		return new FiducciaMattheysesAlgorithm(graph);		
	}
	
	public EvolutionaryApproach evolApproach(Graph graph) {
		return new EvolutionaryApproach(graph, 5);	
	}
	
	public NaiveApproach naiveApproach(Graph graph) throws IOException {
		return new NaiveApproach(graph);		
	}

   
}