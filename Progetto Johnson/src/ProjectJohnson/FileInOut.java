package ProjectJohnson;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileInOut
{
	private static File currentFile = null ;

    public static File getFile()
    {
        return currentFile ;
    }
    
    public static void saveGraph(Graph graph, File file) throws IOException
    {
        if (file == null)
        {
            throw new IOException() ;
        }
        
        if (graph == null) {
        	throw new IOException();
        }
        currentFile = file ;
 
        FileWriter fileout = new FileWriter(currentFile) ;

        for (Node n : graph.getNodes())
        {
            writeWS(fileout, n.getLabel()) ;
        }
        fileout.write("\r\n");

        for (Edge e : graph.getEdges())
        {
            writeWS(fileout, e.getN1().getLabel()) ;
            writeWS(fileout, e.getN2().getLabel()) ;
            writeWS(fileout, String.valueOf(e.getWeight())) ;
            fileout.write("\r\n") ;
        }

        fileout.close() ;
    }
    
    public static Graph loadGraph(File file) throws IOException
    {
        if (file == null)
        {
            throw new IOException() ;
        }
        
        currentFile = file ;

        BufferedReader fileIn = new BufferedReader(new FileReader(file)) ;
        String line ;

        if ((line = fileIn.readLine()) == null) 
        {
        	fileIn.close() ;
            throw new IOException() ;
        }
        Graph graph = new Graph() ;
        
        for (String label : line.split(" "))
        {
            graph.addNode(label) ;
        }

        while ((line = fileIn.readLine()) != null)
        {
            String[] parts = line.split(" ") ;
            Edge edge = graph.addEdge(parts[0], parts[1]) ;
            if (edge != null) 
            {
                edge.setWeight(Integer.parseInt(parts[2])) ;
            }
        }
        fileIn.close() ;
        
        return graph ;
    }
    
    private static void writeWS(FileWriter fileOut, String s)
    {
        try
        {
            fileOut.write(s) ;
            fileOut.write(" ") ;
        } 
        catch (IOException e)
        {
            e.printStackTrace() ;
        }
    }
    
   
    
}