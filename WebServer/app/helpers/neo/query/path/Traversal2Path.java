package helpers.neo.query.path;

import helpers.neo.query.TraversalPath;
import helpers.neo.query.framework.Relation;
import helpers.neo.query.framework.TraversalDefinition;

import java.util.HashMap;
import java.util.Map;

public class Traversal2Path extends TraversalPath{

	public Traversal2Path(){
	}

	@Override
	public String generatePath(){
		final Map<String, String> relations = new HashMap<String, String>();
		relations.put( "LOCALITY", Relation.OUT );
		
		final TraversalPath path = new TraversalPath( TraversalDefinition.DEPTH_FIRST, 
													  10, 
													  TraversalDefinition.NODE, 
													  TraversalDefinition.ALL, 
													  relations );
		return path.generatePath();
	}
}
