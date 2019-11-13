package br.com.structure.graph;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Graph {
	private int [] grades;
	private int [][] edges;
	private int [][] weights;

	private int [] depths;
	private int [] spreads;

	public int [] getDepths() {
		return depths;
	}

	public int [] getSpreads() {
		return spreads;
	}

	
	private void initAtributes(int numberOfVertex, int maxGrade) {
		grades  = new int [numberOfVertex];
		edges   = new int [numberOfVertex][maxGrade];
		weights = new int [numberOfVertex][maxGrade];
	}
	
	public Graph(int numberOfVertex, int maxGrade) {
		initAtributes(numberOfVertex, maxGrade);
	}

	public Graph() {
		int numberOfVertex = Integer.parseInt(JOptionPane.showInputDialog("Número de vértices:"));
		int numberOfEdges = Integer.parseInt(JOptionPane.showInputDialog("Número de ligações:"));
		int maxGrade = Integer.parseInt(JOptionPane.showInputDialog("Número máximo de ligações para um vertice:"));

		initAtributes(numberOfVertex, maxGrade);
		
		for (int i = 0; i < numberOfEdges; i++) {
			int beginVertex = Integer.parseInt(JOptionPane.showInputDialog("Ligiação " + (i + 1) + ", vértice origem:"));
			int endVertex = Integer.parseInt(JOptionPane.showInputDialog("Ligiação " + (i + 1) + ", vértice destino:"));
			int weight = Integer.parseInt(JOptionPane.showInputDialog("Ligiação " + (i + 1) + ", peso:"));

			addEdge(beginVertex, endVertex, weight);
		}
	}
	
	
	boolean areValidEdges(int beginVertex, int endVertex) {
		return (beginVertex < 0 || beginVertex >= grades.length) || 
			   (endVertex   < 0 || endVertex   >= grades.length);
	}
	
	public void addEdge(int beginVertex, int endVertex, int weight) {
		addEdge(beginVertex, endVertex, weight, false);
	}

	public void addEdge(int beginVertex, int endVertex, int weight, boolean isDigraph) {
		if (areValidEdges(beginVertex, endVertex)) 
			return;
		
		edges  [beginVertex][grades[beginVertex]] = endVertex;
		weights[beginVertex][grades[beginVertex]] = weight;
		grades [beginVertex]++;
		
		if (isDigraph)
			addEdge(endVertex, beginVertex, weight, false);
	}
	
	
	public void removeEdge(int beginVertex, int endVertex) {
		removeEdge(beginVertex, endVertex, false);
	}

	public void removeEdge(int beginVertex, int endVertex, boolean isDigraph) {
		if (areValidEdges(beginVertex, endVertex)) 
			return;

		int cursor = 0;
		while (cursor < grades[beginVertex] && edges[beginVertex][cursor] != endVertex) {
			cursor ++;
		}
		
		if (cursor == grades[beginVertex]) 
			return;
		
		grades [beginVertex]--;
		edges  [beginVertex][cursor] = edges[beginVertex][grades[beginVertex]];
		weights[beginVertex][cursor] = weights[beginVertex][grades[beginVertex]];

		if (isDigraph)
			removeEdge(endVertex, beginVertex, false);
	}


	public void showAll() {
		for (int i = 0; i < edges.length; i++) {
			System.out.print(i + "| ");
			
			for (int j = 0; j < edges[i].length; j++) {
				System.out.print(edges[i][j] + "(" + weights[i][j] + ") ");
			}

			System.out.println();
		}

		System.out.println();
	}

	
	private int [] fillVisitedEdges(){
		int visited [] = new int [grades.length];
		
		for (int i = 0; i < visited.length; i++) {
			visited [i] = 0;
		}
		
		return visited;
	}
	
	public int depthFirstSearch(int index, int edge) {
		int [] visited = fillVisitedEdges();

		return depthFirstSearch(index, visited, 1, edge);
	}

	private int depthFirstSearch(int index, int [] visited, int cursor, int edge) {
		visited[index] = cursor;

		for (int i = 0; i < grades[index]; i++) {
			if (visited[edges[index][i]] == 0) {
				depthFirstSearch(edges[index][i], visited, cursor + 1, edge);	
			}
		}
		
		depths = visited;
		
		return visited[edge];
	}
	
	public int breadthFirstSearch(int index, int edge) {
		int counter = 1;
		int beginQueue = 0, endQueue = 0;

		int [] visited = fillVisitedEdges();
		
		int [] queue = new int[grades.length];
		
		endQueue++;
		
		queue[endQueue]  = index;
		visited[index] = counter;

		int vertex;
		while (beginQueue != endQueue) {
			beginQueue = (beginQueue + 1) % grades.length;
			
			vertex = queue[beginQueue];
			
			counter++;

			for (int i = 0; i < grades[vertex]; i++) {
				if (visited[edges[vertex][i]] == 0) {
					endQueue = (endQueue + 1) % grades.length;
					
					queue[endQueue] = edges[vertex][i];
					
					visited[edges[vertex][i]] = counter;
				}
			}
		}

		spreads = visited;
		
		return visited[edge];
	}

	
	public int [] shortestPathFirstByVertex(int begin, int end, SorterBy sorterBy) {
		return shortestPathFirst(begin, end, SorterBy.VERTEX);
	}
	
	public int [] shortestPathFirstByWeght(int begin, int end, SorterBy sorterBy) {
		return shortestPathFirst(begin, end, SorterBy.WEIGHT);
	}
	
	private int seekShorterDistance(int [] distance, int [] visited) {
		int lowest = -1;
		boolean isFirst = true;

		for (int i = 0; i < grades.length; i++) {
			if (distance[i] >= 0 && visited[i] == 0) {
				if (isFirst) {
					isFirst = false;
					lowest = i;
				} else if (distance[lowest] > distance[i]) {
					lowest = i;
				}
			}
		}

		return lowest;
	}
	
	public int [] shortestPathFirst(int begin, int end, SorterBy sorterBy) {
		int index, lowest;

		int [] visited = new int[grades.length];
		int [] predecessor = new int[grades.length];
		int [] lowestDistances = new int[grades.length];
		
		for (int i = 0; i < visited.length; i++) {
			predecessor[i] = -1;
			lowestDistances[i] = -1;
			visited[i] = 0;
		}

		lowestDistances[begin] = 0;
		
		int counter = grades.length;
		while (counter > 0) {
			lowest = seekShorterDistance(lowestDistances, visited);
			
			if (lowest == -1) break;

			visited[lowest] = 1;
			counter--;

			for (int i = 0; i < grades[lowest]; i++) {
				index = edges[lowest][i];

				int sortFactor = (sorterBy.equals(SorterBy.VERTEX))? 1 : weights[lowest][i];
				
				if (lowestDistances[index] < 0) {
					lowestDistances[index] = lowestDistances[lowest] + sortFactor;
					predecessor[index] = lowest;
					
				} else if (lowestDistances[index] > lowestDistances[lowest] + sortFactor) {
					lowestDistances[index] = lowestDistances[lowest] + sortFactor;
					predecessor[index] = lowest;
				}
			}
		}

		ArrayList<Integer> pathList = new ArrayList<>();
		pathList.add(end);

		int cursor = predecessor[end];
		while (cursor != -1) {
			pathList.add(cursor);
			cursor = predecessor[cursor];
		}

		Collections.reverse(pathList);

		int [] finalShortestPath = new int[pathList.size() + 1];
		
		for (int i = 0; i < finalShortestPath.length; i++) {
			finalShortestPath[i] = (i == finalShortestPath.length - 1)? lowestDistances[end] : pathList.get(i);
		}

		return finalShortestPath;
		
	}
	
	
	public int [] minimumSpanningTree(int begin, SpanningTreeAlgorithm algorithm){
		return algorithm.equals(SpanningTreeAlgorithm.KRUSKAL_ALGORTHM)? primAlgorithm(begin) : kruskalAlgorithm(begin);
	}

	private int[] primAlgorithm (int begin) {
		int whither = 0; 
		int numberOfVertex = grades.length;
		
		double lowestWeight = 2_147_483_647;

		int [] finalTree = new int[grades.length];
		for (int i = 0; i < numberOfVertex; i++) {
			finalTree[i] = -1;
		}
		
		finalTree[begin] = begin;

		boolean isFirst;
		while (true) {
			isFirst = true;

			for (int i = 0; i < numberOfVertex; i++) {
				if (finalTree[i] != -1) {
					
					for (int j = 0; j < grades[i]; j++) {
						if (finalTree[edges[i][j]] == -1) {
							
							if (isFirst) {
								lowestWeight = weights[i][j];
								
								begin = i;
								
								whither = edges[i][j];
								
								isFirst = false;
							} else if (lowestWeight > weights[i][j]) {
								lowestWeight = weights[i][j];
							
								begin = i;
								
								whither = edges[i][j];
							}
						}
					}
				}
			}

			if (isFirst) break;

			finalTree[whither] = begin;
		}

		return finalTree;
	}
	
	private int[] kruskalAlgorithm(int begin) {
		int [] tree = new int[grades.length];

		int whither = 0; 
		int numberOfVertex = grades.length;
		
		double lowestWeight = 2_147_483_647;

		int [] finalTree = new int[grades.length];
		for (int i = 0; i < numberOfVertex; i++) {
			tree[i] = i;
			finalTree[i] = -1;
		}
		
		finalTree[begin] = begin;

		boolean isFirst;
		while (true) {
			isFirst = true;

			for (int i = 0; i < numberOfVertex; i++) {
				for (int j = 0; j < grades[i]; j++) {
					
					if (tree[i] != tree[edges[i][j]]) {
						if (isFirst) {
							lowestWeight = weights[i][j];
							
							begin = i;
							
							whither = edges[i][j];
							
							isFirst = false;
						} else if (lowestWeight > weights[i][j]) {
							lowestWeight = weights[i][j];
							
							begin = i;
							
							whither = edges[i][j];
						}
					}
				}
			}

			if (isFirst) break;
			
    		if (finalTree[begin] == -1) {
    			finalTree[begin] = whither;
    		} else {
    			finalTree[whither] = begin;
    		}
			
			for (int i = 0; i < numberOfVertex; i++) {
				if (tree[i] == tree[whither]) {
					tree[i] = tree[begin];
				}
			}
		}

		return finalTree;
	}

	public static void main(String args[]) {
		Graph grafo = new Graph(6, 5);
		grafo.addEdge(0, 1, 6, true);
		grafo.addEdge(0, 2, 1, true);
		grafo.addEdge(0, 3, 5, true);
		grafo.addEdge(1, 2, 2, true);
		grafo.addEdge(1, 4, 5, true);
		grafo.addEdge(2, 3, 2, true);
		grafo.addEdge(2, 4, 6, true);
		grafo.addEdge(2, 5, 4, true);
		grafo.addEdge(3, 5, 4, true);
		grafo.addEdge(4, 5, 3, true);

		grafo.showAll();

		System.out.println("Profundidade: " + grafo.depthFirstSearch(0, 3));
		for (int i = 0; i < grafo.getDepths().length; i++) {
			System.out.print("|" + grafo.getDepths()[i]);
		}

		System.out.println("\n\nLargura: " + grafo.breadthFirstSearch(0, 3));
		for (int i = 0; i < grafo.getSpreads().length; i++) {
			System.out.print("|" + grafo.getSpreads()[i]);
		}

//		System.out.println("\n\nMenor camino por pesos: ");
//		int menorCaminhoPeso[] = grafo.shortestPathFirstByWeights(0, 5);
//		for (int i = 0; i < menorCaminhoPeso.length; i++) {
//			System.out.print("|" + menorCaminhoPeso[i]);
//		}
//
//		System.out.println("\n\nMenor camino entre vertices: ");
//		int menorCaminhoVertice[] = grafo.shortestPathFirstByVertex(0, 5);
//		for (int i = 0; i < menorCaminhoVertice.length; i++) {
//			System.out.print("|" + menorCaminhoVertice[i]);
//		}
		
		System.out.println("\n\nMenor camino por pesos: ");
		int menorCaminhoPeso[] = grafo.shortestPathFirst(0, 5, SorterBy.WEIGHT);
		for (int i = 0; i < menorCaminhoPeso.length; i++) {
			System.out.print("|" + menorCaminhoPeso[i]);
		}
		
		System.out.println("\n\nMenor camino entre vertices: ");
		int menorCaminhoVertice[] = grafo.shortestPathFirst(0, 5, SorterBy.VERTEX);
		for (int i = 0; i < menorCaminhoVertice.length; i++) {
			System.out.print("|" + menorCaminhoVertice[i]);
		}

		System.out.println("\n\nArvore geradora minima (PRIM): ");
		int pai[] = grafo.minimumSpanningTree(0, SpanningTreeAlgorithm.PRIM_ALGORTHM);
		for (int i = 0; i < pai.length; i++) {
			System.out.print("|" + pai[i]);
		}

		System.out.println("\n\nArvore geradora minima (Kruskal): ");
		int arv[] = grafo.minimumSpanningTree(0, SpanningTreeAlgorithm.KRUSKAL_ALGORTHM);
		for (int i = 0; i < arv.length; i++) {
			System.out.print("|" + arv[i]);
		}
	}
}
