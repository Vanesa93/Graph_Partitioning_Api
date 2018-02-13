package com.vvgeorgieva.entities;

import java.util.Random;

import entity.Vertex;

public class VertexEntity extends Vertex {
	
	public int id;
	public int label;	
	public int x;
	public int y;
	public int size;
	private static Random rand;
	
	public VertexEntity(int label, int verticesSize) {
		super(label);
		int vertexPosition = randInt(verticesSize-1);
		this.id = label;
		this.label = label;
		this.x = vertexPosition;
		this.y = vertexPosition + 1;
		this.size = 3;
	}
	
	public static int randInt(int verticesNumber) {
	    rand = null;
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt(verticesNumber);
	    return randomNum;
	}
	
	
}
