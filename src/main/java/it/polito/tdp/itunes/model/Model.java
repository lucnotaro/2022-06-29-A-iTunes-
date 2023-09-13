package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private Graph<Album,DefaultWeightedEdge> grafo;
	private ItunesDAO dao;
	private List<Album> allNodes;
	private Map<Integer,Album> idMap;
	
	public Model() {
		this.dao=new ItunesDAO();
		this.allNodes=new ArrayList<>();
		this.idMap=new HashMap<>();
	}
	
	public void creaGrafo(Integer n) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.allNodes=this.dao.getNAlbums(n,idMap);
		Graphs.addAllVertices(this.grafo,this.allNodes);
		for(Album a1:this.allNodes) {
			for(Album a2:this.allNodes) {
				int d=a1.getNtrack()-a2.getNtrack();
				if(d>0) {
					Graphs.addEdge(this.grafo,a2,a1,d);
				}
			}
		}
		System.out.println(this.grafo.vertexSet().size());
		System.out.println(this.grafo.edgeSet().size());
	}
	
	public Integer getVSize() {
		return this.grafo.vertexSet().size();
	}
	
	public Integer getESize() {
		return this.grafo.edgeSet().size();
	}
	
	private int getBilancio(Album a) {
		int b=0;
		List<DefaultWeightedEdge> in=new ArrayList<>(this.grafo.incomingEdgesOf(a));
		List<DefaultWeightedEdge> out=new ArrayList<>(this.grafo.outgoingEdgesOf(a));
		for(DefaultWeightedEdge e:in)
			b+=this.grafo.getEdgeWeight(e);
		for(DefaultWeightedEdge e:out)
			b-=this.grafo.getEdgeWeight(e);
		return b;
	}
	
	public List<Album> getVerticiOrdinati(){
		List<Album> vertici=new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(vertici);
		return vertici;
	}
	
	public List<BilancioAlbum> getAdiacenti(Album a){
		List<Album> successori=Graphs.successorListOf(this.grafo,a);
		List<BilancioAlbum> bilancioSuccessori=new ArrayList<>();
		for(Album alb:successori) {
			bilancioSuccessori.add(new BilancioAlbum(alb,this.getBilancio(alb)));
		}
		Collections.sort(bilancioSuccessori);
		return bilancioSuccessori;
	}
	
}
