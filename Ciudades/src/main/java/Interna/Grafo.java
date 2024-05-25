package Interna;

import java.util.*;

public class Grafo {
	
	private Map<String, Map<String, Integer>> adjList;
	
	public Grafo() {
		adjList = new HashMap<>();
	}
	
	public void añadirCiudad(String nombre) {
		adjList.putIfAbsent(nombre, new HashMap<>());
	}
	
	public void añadirRuta(String ciudadOrigen, String ciudadDestino, int distancia) {
		añadirCiudad(ciudadOrigen);
		añadirCiudad(ciudadDestino);
		adjList.get(ciudadOrigen).put(ciudadDestino, distancia);
	}
	
	public void eliminarRuta(String ciudadOrigen, String ciudadDestino) {
		Map<String, Integer> destinos = adjList.get(ciudadOrigen);
		if(destinos != null) {
			destinos.remove(ciudadDestino);
		}
	}
	
	//List all rutes 
	
	public List<String> listarRutasDesdeCiudad(String ciudad){
		
		List<String> rutas = new ArrayList<>();
		Map<String, Integer> destinos = adjList.get(ciudad);
		if(destinos != null) {
			for(String destino : destinos.keySet()) {
				rutas.add(ciudad + " -> " + destino + " (Distancia: " + destinos.get(destino) + " ) ");
			}
		}
		return rutas;
	}
	
	
	
	public void recorridoDFS(String ciudadInicio) {
		Set<String> visitados = new HashSet<>();
		recorridoDFSUtil(ciudadInicio, visitados);
	}
	
	
	
	private void recorridoDFSUtil(String ciudad, Set<String> visitados) {
		if(!visitados.contains(ciudad)) {
			visitados.add(ciudad);
			System.out.println(ciudad + "  ");
			Map<String, Integer> destinos = adjList.get(ciudad);
			if(destinos != null) {
				for(String destino : destinos.keySet()) {
					recorridoDFSUtil(destino, visitados);
				}
			}
		}
	}
	
	
	
	public void recorridoBFS(String ciudadInicio) {
		Set<String> visitados = new HashSet<>();
		Queue<String> cola = new LinkedList<>();
		cola.add(ciudadInicio);
		visitados.add(ciudadInicio);
		
		while(!cola.isEmpty()) {
			String ciudad = cola.poll();
			System.out.println(ciudad + "   ");
			Map<String, Integer> destinos = adjList.get(ciudad);
			if(destinos != null) {
				for(String destino : destinos.keySet()) {
					if(!visitados.contains(destino)) {
						cola.add(destino);
						visitados.add(destino);
					}
				}
			}
		}
	}
	
	
	
	public Map<String, Integer> dijkstra(String inicio){
		
		Map<String, Integer> distancias = new HashMap<>();
		
		PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
		distancias.put(inicio, 0);
		pq.add(inicio);
		
		while(!pq.isEmpty()) {
			String ciudadActual = pq.poll();
			
			int distanciaActual = distancias.get(ciudadActual);
			
		
			Map<String, Integer> destinos = adjList.get(ciudadActual);
			
			if(destinos != null) {
				
				for(Map.Entry<String, Integer> entry : destinos.entrySet()) {
					String vecino = entry.getKey();
					int distancia = entry.getValue();
					
					int nuevaDistancia = distanciaActual + distancia;
					if(nuevaDistancia < distancias.getOrDefault(vecino, Integer.MAX_VALUE)) {
						distancias.put(vecino, nuevaDistancia);
						pq.add(vecino);
					}
				}
			}
		}
		
		return distancias;
	}
	
	
	
	//View Graph
	
	public void mostrarGrafo() {
		
		for(String ciudad : adjList.keySet()) {
			System.out.println(ciudad + " -> ");
			Map<String, Integer> destinos = adjList.get(ciudad);
			
			for(String destino : destinos.keySet()) {
				System.out.println(destino + " ( " + destinos.get(destino) + " ) ");
			}
		}
		
		System.out.println();
	}
}
