package helpers.neo;

import helpers.neo.framework.NeoRetriever;

/**
 * 
 * @author arjrao
 *
 */
public class NeoWalker {
 
	private int nodeId;
	private String traversalAlgorithm;
	
    public NeoWalker( int nodeId, String traversalAlgorithm ){
    	this.nodeId = nodeId;
    	this.traversalAlgorithm = traversalAlgorithm;
    }
    
    public String walk(){
    	final NeoRetriever retriever = new NeoRetriever();
		return retriever.retrieve( nodeId, traversalAlgorithm );
    }
}