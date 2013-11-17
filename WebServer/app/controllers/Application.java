package controllers;

import helpers.nlp.parser.QueryToCypher;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * 
 * @author arjrao
 *
 */
public class Application extends Controller {

	public final static QueryToCypher QUERY_PARAMS = new QueryToCypher();
	
	// Landing page view
    public static Result index() {
        return ok(views.html.index.render());
    }
    
    // Query result page view
    public static Result parse( String querytext ){
    	// Step 1 : Get query
//        final QueryParser query = new QueryParser( querytext );// Do some string manipulation/sanitization/normalization
//        int entryNode = query.parse();
        
        // Step 2 : Process query
       // NeoWalker walker = new NeoWalker( entryNode, query.getTraversalAlgorithm() );
        
        
        
        // Step 3 : Render results
        return ok( views.html.result.render() );
    }
}
