package Principal;

import java.util.List;
import java.util.Map;

import Interna.Grafo;

public class Main {

	public static void main(String[]args) {
		
		Grafo grafo = new Grafo();
		
		grafo.añadirCiudad("A");
		grafo.añadirCiudad("B");
		grafo.añadirCiudad("Z");
		grafo.añadirRuta("A", "B", 15);
		grafo.añadirRuta("A", "C", 70);
		grafo.añadirRuta("B", "C", 780);
		grafo.añadirRuta("C", "Z", 123485678);
		
		System.out.println("Rutas desde A");
		List<String> rutasDesdeA = grafo.listarRutasDesdeCiudad("A");
		for(String ruta : rutasDesdeA) {
			System.out.println(ruta);
		}
		
		System.out.println("Recorridos DFS desde A");
		grafo.recorridoDFS("A");
		System.out.println(" ");
		
		
		System.out.println("Recorridos BFS desde A");
		grafo.recorridoBFS("A");
		System.out.println(" ");
		
		System.out.println("Distancias desde B (DIJKSTRA): ");
		Map<String, Integer> distancias = grafo.dijkstra("B");
		
        for (Map.Entry<String, Integer> entry : distancias.entrySet()) {
            System.out.println(entry.getKey() + "  ->  " + entry.getValue());
        }
        
        System.out.println("GRAPH");
        grafo.mostrarGrafo();
	}
}
