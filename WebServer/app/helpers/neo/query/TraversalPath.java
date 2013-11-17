package helpers.neo.query;

import helpers.neo.query.framework.Relation;
import helpers.neo.query.framework.TraversalDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TraversalPath implements Path{

	//TODO: Define enums for these params
	//TODO: See what other params need to be defined for having a traversal algo
	
	private String traversalType;
	private int traversalDepth;
	private String traversalUniqueness;
	private String traversalFilter;
	/**
	 * Relation name -> Relation direction
	 */
	private Map<String, String> relationships;
	
	public TraversalPath() {
		
	}

	public TraversalPath( String traversalType, int traversalDepth, String traversalUniqueness, String traversalFilter, Map<String, String> relationships  ){
		this.traversalType = traversalType;
		this.traversalDepth = traversalDepth;
		this.traversalUniqueness = traversalUniqueness;
		this.traversalFilter = traversalFilter;
		this.relationships = relationships;
	}
	
	//====================================== INTERFACE METHOD ===============================================//
	
	@Override
	public String generatePath() {
        final TraversalDefinition t = new TraversalDefinition();
        t.setOrder( traversalType );
        t.setUniqueness( traversalUniqueness );
        t.setMaxDepth( traversalDepth );
        t.setReturnFilter( traversalFilter );
        
        final List<Relation> relations = new ArrayList<Relation>();
        for( Entry<String, String> entry : relationships.entrySet() ){
        	relations.add( new Relation( entry.getKey(), entry.getValue() ) );
        }
        t.setRelationships( relations );
        
		return t.toJson();
	}
	
	//========================================== GETTERS AND SETTERS ==========================================//

	public String getTraversalType() {
		return traversalType;
	}
	public void setTraversalType(String traversalType) {
		this.traversalType = traversalType;
	}
	public int getTraversalDepth() {
		return traversalDepth;
	}
	public void setTraversalDepth(int traversalDepth) {
		this.traversalDepth = traversalDepth;
	}
	public String getTraversalUniqueness() {
		return traversalUniqueness;
	}
	public void setTraversalUniqueness(String traversalUniqueness) {
		this.traversalUniqueness = traversalUniqueness;
	}
	public String getReturnFilter() {
		return traversalFilter;
	}
	public void setTraversalFilter(String traversalFilter) {
		this.traversalFilter = traversalFilter;
	}
	public Map<String, String> getRelationships() {
		return relationships;
	}
	public void setRelationships(Map<String, String> relationships) {
		this.relationships = relationships;
	}
}
