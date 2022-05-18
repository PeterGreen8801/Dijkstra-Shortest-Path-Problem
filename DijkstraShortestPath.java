public class DijkstraShortestPath
{
   static class GraphBuilder
   {
      int vertices;
      int adjacencyMatrix[][];
      
      public GraphBuilder(int vertex)
      {
         vertices = vertex;
         adjacencyMatrix = new int[vertex][vertex];
      }
   
      public void addEdge(int source, int nextNode, int weight)
      {
         adjacencyMatrix[source][nextNode] = weight;
         adjacencyMatrix[nextNode][source] = weight;
      }
      
      public int getMinVertex(boolean[] min, int[] key)
      {
         int minKey = Integer.MAX_VALUE;
         int vertex = -1;
         for(int i = 0; i < vertices;i++)
         {
            if(min[i] == false && minKey > key[i])
            {
               minKey = key[i];
               vertex = i;
            }
         }
         return vertex;
      }
      
      public void dijkstra(int sourceVertex)
      {
         boolean[] shortestPath = new boolean[vertices];
         int[] distance = new int[vertices];
         int INFINITY = Integer.MAX_VALUE;
         
         for(int i =0;i < vertices; i++)
         {
            distance[i] = INFINITY;
         }
         
         distance[sourceVertex] = 0;
         
         for(int i = 0; i < vertices; i++)
         {
            int vertexMin = getMinVertex(shortestPath,distance);
            
            shortestPath[vertexMin] = true;
            
            for(int adjVertex = 0; adjVertex < vertices; adjVertex++)
            {
               if(adjacencyMatrix[vertexMin][adjVertex] > 0)
               {
                  if(shortestPath[adjVertex] == false && adjacencyMatrix[vertexMin][adjVertex]!= INFINITY)
                  {
                     int newKey = adjacencyMatrix[vertexMin][adjVertex] + distance[vertexMin];
                     if(newKey < distance[adjVertex])
                     {
                        distance[adjVertex] = newKey;
                     }
                  }
               }
            }
         }
         dumpDijkstra(sourceVertex, distance);
      }
      public void dumpDijkstra(int sourceVertex, int[] key)
      {
         for(int i = 0; i < vertices;i++)
         {
            System.out.println("Node Label " + i + " has minimum distance " + key[i] + " from source " + sourceVertex);
         }
      }
   }
   
   public static void main(String[] args)
   {
      int vertices = 6;
      GraphBuilder graph = new GraphBuilder(vertices);
      int sourceVertex = 0;
      graph.addEdge(0,1,6);
      graph.addEdge(0,2,10);
      graph.addEdge(0,4,1);
      graph.addEdge(1,3,3);
      graph.addEdge(3,2,3);
      graph.addEdge(4,1,4);
      graph.addEdge(4,5,2);
      graph.addEdge(5,1,1);
      graph.dijkstra(sourceVertex);
   }
}

//good times
