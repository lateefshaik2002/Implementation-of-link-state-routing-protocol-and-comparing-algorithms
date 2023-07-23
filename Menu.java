import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class Menu{

public static void main(String[] args) {
    
	Dijkstra algorithm=new Dijkstra();
	String fname = new String();
	int[][] toplogy_arr = null;
	
	//Reading entire file
	Scanner sc = new Scanner(System.in);
	
	try{
	fname =  JOptionPane.showInputDialog(null, "enter the network topology ", "input\\topology.txt");
    List<String> complete_file = Files.readAllLines(Paths.get(fname));
    
    for (int i = complete_file.size() - 1; i >= 0; i--) {
        if (complete_file.get(i).isEmpty()) {
            complete_file.remove(i);
        }
    }

   toplogy_arr = new int[complete_file.size()][];

    for (int i = 0; i < complete_file.size(); i++) {
        String[] split_arr = complete_file.get(i).split("\\s");

        toplogy_arr[i] = new int[split_arr.length]; 
        for (int j = 0; j < split_arr.length; j++) {
            toplogy_arr[i][j] = Integer.parseInt(split_arr[j]);
        }
    }

	}
	catch(IOException e){
			System.out.println("Error!File not found!");	
			System.out.println("Exiting");
			System.exit(0);
	}
	catch(NullPointerException e){
		System.out.println("Exiting");
		System.exit(0);
	}
	
    int choice;
    int src_router;
    int dest_router;
    
    try{
        while(true)
        {
        	
        	 	System.out.println("\t\t\tLink State Routing");
        	    System.out.println("1.Display current topology\n2.Create connection table\n3.determine shortest path\n4.delete router\n5.add router\n6.exit ");
        	    System.out.println("Enter choice:");
        	    choice=sc.nextInt();
        	    
        	    switch (choice) {
        	    
        	    case 1:
        	    	algorithm.Print_Topology(toplogy_arr);
        	    	break;
        	    
    			case 2:
    		    	 System.out.println("Enter router number:");
    		    	 src_router=sc.nextInt();
    		    	 System.out.println("Building connection table for the router "+ src_router);
    		    	 algorithm.build_conn_table(src_router, toplogy_arr);
    				break;
    				
    			case 3:
    				System.out.println("Shortest Path");
    				System.out.println("Enter source router:");
    				src_router = sc.nextInt();
    				System.out.println("Enter destination router:");
    				dest_router = sc.nextInt();
    				algorithm.shortest_path(src_router, dest_router, toplogy_arr);
    				algorithm.shortest_path_display();
    				break;
    				
    			case 4:
    				
    				System.out.println("Router deletion");
    				System.out.println("Enter router to  deleted");
    				int del = sc.nextInt();
    				toplogy_arr = algorithm.router_delete(del, toplogy_arr);
    				System.out.println("Toplogy after deletion");
    				algorithm.Print_del(toplogy_arr);
    				break;
    	
    			case 5:
    				int after_add[][] = new int[toplogy_arr.length][];
    				System.out.println("Router dddition");
    				System.out.println("Router will be added at position: "+(toplogy_arr.length+1));
    				toplogy_arr = algorithm.router_add(toplogy_arr);
    				algorithm.Print_add(toplogy_arr);
    				break;
    				
    			case 6:
    				JOptionPane.showMessageDialog(null, "Exiting");
    				System.out.println("\t\t\tTerminated");
    				System.exit(0);
    			default:
    				break;
    			}
        }
    }
    catch(Exception e){
    	System.out.println("No Such Element found");
    	System.out.println("Exiting");
    	System.exit(0);}}}
