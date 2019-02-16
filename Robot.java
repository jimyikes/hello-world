package amazon.prep;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import amazon.prep.AllNodesDistance.TreeNode;

public class Robot {

	public static void main(String[] args) {

		Robot r = new Robot();
		
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	ArrayList<Integer> a = new ArrayList<Integer>();a.add(1); a.add(1); a.add(0); a.add(1);
    	list.add(a);
    	
    	ArrayList<Integer> b = new ArrayList<Integer>(); b.add(1); b.add(1);b.add(0);b.add(9);
    	list.add(b);
    	
    	ArrayList<Integer> c = new ArrayList<Integer>(); c.add(1); c.add(1);c.add(1);c.add(1);
    	list.add(c);

    	int result = r.removeObstacle(3, 4,list);
        System.out.println(result);

	}

	int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
    {
		GraphNode startNode = populateGraph(numRows, numColumns, lot);
    	int minDist = 0;
    	
        
    	return 0;
    }
    
    private GraphNode populateGraph(int numRows, int numColumns, List<List<Integer>> lot) 
    {
    	
    	List<List<AbstractMap.SimpleImmutableEntry<Integer, GraphNode>>> nodes =
    		new ArrayList<List<AbstractMap.SimpleImmutableEntry<Integer, GraphNode>>>();
    	GraphNode startNode = null;
    	
    	for (int row=0; row < numRows; row++)
    	{
        	List<AbstractMap.SimpleImmutableEntry<Integer, GraphNode>> rowList =
            	new ArrayList<AbstractMap.SimpleImmutableEntry<Integer, GraphNode>>();
    		
        	for (int col=0; col < numColumns; col++)
    		{    	    	
    	    	boolean down = false;
    	    	boolean right = false;
    	    	boolean left = false;
    	    	boolean up = false;
    	    	
    	    	int currentValue = lot.get(row).get(col);
   
  	    		GraphNode gn = new GraphNode(currentValue);

    	        rowList.add(new AbstractMap.SimpleImmutableEntry<Integer, GraphNode>(currentValue,gn));
    		}
        	
        	nodes.add(rowList);
    	}
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {

				int val = nodes.get(row).get(col).getKey();

				GraphNode cn = nodes.get(row).get(col).getValue();
				
				if (val != 0) {
					// check down
					if ((row < numRows - 1) && lot.get(row + 1).get(col) != 0) {
						cn.down = nodes.get(row+1).get(col).getValue();
					}

					if ((row > 0) && lot.get(row - 1).get(col) != 0) {
						cn.up = nodes.get(row-1).get(col).getValue();

					}

					if ((col < numColumns - 1) && lot.get(row).get(col + 1) != 0) {
						cn.right = nodes.get(row).get(col+1).getValue();

					}

					if ((col > 0) && lot.get(row).get(col - 1) != 0) {
						cn.left = nodes.get(row).get(col-1).getValue();

					}
				}
			}
		}
    	
    	return startNode;
	}

	int tryMove(int xLoc, int yLoc, int numRows, int numColumns, List<List<Integer>> lot)
    {
    	boolean down = false;
    	boolean right = false;
    	boolean left = false;
    	boolean up = false;
    	int minDist=0;
    	
    	int currentValue = lot.get(xLoc).get(yLoc);
    	
    	if (currentValue == 9)
    		return minDist;
    	
    	// check down
    	if ((xLoc < numRows-1) && lot.get(xLoc+1).get(yLoc) != 0)
    	{
    		tryMove(xLoc+1, yLoc, numRows, numColumns, lot);
    	}
    		
    		
        if ((xLoc > 0) && lot.get(xLoc-1).get(yLoc) != 0)
        {
    		tryMove(xLoc-1, yLoc, numRows, numColumns, lot);
        }
        		
        if ((yLoc < numColumns-1) && lot.get(xLoc).get(yLoc+1) != 0)
        {
    		tryMove(xLoc, yLoc+1, numRows, numColumns, lot);
        }
            		
        if ((yLoc > 0) && lot.get(xLoc).get(yLoc-1) != 0)
        {
    		tryMove(xLoc, yLoc-1, numRows, numColumns, lot);
        }

        	return minDist;
    }
}

class GraphNode 
{
	    int val = -1;
	    public GraphNode up=null;
	    GraphNode right=null;
	    GraphNode down=null;
	    GraphNode left=null;

	    GraphNode(int x) { val = x; }
	    public String toString() 
	    {
	    	StringBuilder sb = new StringBuilder();
	    	sb.append(val + " {" + ((up==null)?null:up.val));
	    	sb.append(", " + ((right==null)?null:right.val));
	    	sb.append(", " + ((down==null)?null:down.val));
	    	sb.append(", " + ((left==null)?null:left.val) + "}");
	    	
	    	return sb.toString();
	    }
}
