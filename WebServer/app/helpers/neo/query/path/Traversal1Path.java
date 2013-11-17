package helpers.neo.query.path;

import helpers.neo.query.TraversalPath;
import helpers.neo.query.framework.Relation;
import helpers.neo.query.framework.TraversalDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author arjrao
 *
 */
public class Traversal1Path extends TraversalPath{

	public Traversal1Path(){
	}

	@Override
	public String generatePath(){
		final Map<String, String> relations = new HashMap<String, String>();
		relations.put( "singer", Relation.OUT );
		
		final TraversalPath path = new TraversalPath( TraversalDefinition.DEPTH_FIRST, 
													  10, 
													  TraversalDefinition.NODE, 
													  TraversalDefinition.ALL, 
													  relations );
		return path.generatePath();
	}
	
}
