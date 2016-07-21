package com.jantosz.graph;

import java.util.*;


/**
 * Created by jarema.antosz on 21.07.2016.
 */
public class CityGraph
{
	final Map<City, Set<City>> adjacencyList = new HashMap<>();

	public void addCity(City city){
		if(!adjacencyList.containsKey(city)){
			adjacencyList.put(city, new HashSet<>());
		}
	}

	public void addNeighbors(City city, City ... neighbors){
		if(adjacencyList.containsKey(city)){
			adjacencyList.get(city).addAll(Arrays.asList(neighbors));
		}else{
			adjacencyList.put(city, new HashSet<>(Arrays.asList(neighbors)));
		}

		for (City neighbor : neighbors)
		{
			if (adjacencyList.containsKey(neighbor))
			{
				adjacencyList.get(neighbor).add(city);
			}
			else
			{
				adjacencyList.put(neighbor, new HashSet<>(Arrays.asList(city)));
			}
		}
	}

	public static void main(String[] args)
	{
		City wroclaw = new City("Wrocław");
		City olawa = new City("Oława");
		City oborniki = new City("Oborniki");
		City zmigrod = new City("Żmigród");
		City brzeg = new City("Brzeg");
		City opole = new City("Opole");
		City namyslow = new City("Namysłów");

		CityGraph graph = new CityGraph();
		graph.addCity(wroclaw);
		graph.addCity(olawa);
		graph.addCity(oborniki);
		graph.addCity(zmigrod);
		graph.addCity(brzeg);
		graph.addCity(opole);
		graph.addCity(namyslow);

		graph.addNeighbors(wroclaw, olawa, oborniki, namyslow);
		graph.addNeighbors(olawa, brzeg);
		graph.addNeighbors(oborniki, zmigrod);
		graph.addNeighbors(brzeg, opole, namyslow);
		graph.addNeighbors(namyslow, olawa, zmigrod);

		System.out.println("Wrocław neighbors: " + graph.getNeighbors(wroclaw));
		System.out.println("Oława neighbors: " + graph.getNeighbors(olawa));
		System.out.println("Oborniki neighbors: " + graph.getNeighbors(oborniki));
		System.out.println("Żmigród neighbors: " + graph.getNeighbors(zmigrod));
		System.out.println("Brzeg neighbors: " + graph.getNeighbors(brzeg));
		System.out.println("Opole neighbors: " + graph.getNeighbors(opole));
		System.out.println("Namysłów neighbors: " + graph.getNeighbors(namyslow));

		System.out.println("path Opole -> Wrocław: " + graph.findPathDfs(opole, wroclaw));
		System.out.println("path Wrocław -> Żmigród: " + graph.findPathDfs(wroclaw, zmigrod));
		System.out.println("path Żmigród -> Brzeg: " + graph.findPathDfs(zmigrod, brzeg));
	}

	public Set<City> getNeighbors(City city){

		if(adjacencyList.containsKey(city)){
			return adjacencyList.get(city);
		}else{
			return Collections.emptySet();
		}
	}

	//dsf algorithm
	public List<City> findPathDfs(City from, City to){

		Stack<City> cityStack = new Stack<>();
		List<City> visited = new ArrayList<>();
		cityStack.push(from);
		visited.add(from);

		while(!cityStack.isEmpty()){
			City city = cityStack.peek();

			if(city.equals(to)){
				//found!!!
				return new ArrayList<>(cityStack);
			}

			//get unvisited child and push to stack
			//if not found unvisited child then pop from stack

			City unvisitedChild = getUnvisitedChild(visited, city);

			if(unvisitedChild != null){
				cityStack.push(unvisitedChild);
				visited.add(unvisitedChild);
			}else{
				cityStack.pop();
			}
		}

		return Collections.emptyList();
	}

	private City getUnvisitedChild(List<City> visited, City city)
	{
		City unvisitedChild = null;

		for(City childCity: getNeighbors(city)){
			if(!visited.contains(childCity)){
				unvisitedChild = childCity;
				break;
			}
		}
		return unvisitedChild;
	}
}
