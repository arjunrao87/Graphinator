package helpers.nlp.parser;

import helpers.neo.query.path.Traversal2Path;

import java.io.IOException;
import java.util.LinkedList;

import redis.clients.jedis.Jedis;


/**
 * 
 * @author arjrao
 *
 */
public class QueryParser{
	
	//TODO: Need to get the the entry into the graph in another way besides node ID.
	// TODO: !!!!IMPORTANTTTTT!!!!!Also need to map out how to retrieve node Id from node definition or any other parameter that is specified
   //	in the input

	private String rawString;
	private String traversalAlgorithm;
	private int nodeId;
	
    public QueryParser( String queryString ) {
    	this.rawString = queryString;
	}
    
    public int parse() {
    	QueryStripper stripper = new QueryStripper( rawString );
    	LinkedList<String> keyWords = stripper.strip();
    	//Creates algorithm and gives entry point.
    	transmogrify( keyWords );
    	return 14;//getNodeId();
	}

	private void transmogrify(LinkedList<String> keyWords) {
		this.traversalAlgorithm = "{ 'query' : 'START a=node(3) MATCH a-[?*]->()-[:LOCALITY]->d RETURN d,"+ "params"+" : {} "+ "}";
	//new Traversal2Path().generatePath();
	}

	public String getRawQuery(){
    	return this.rawString;
    }
    
    public String getTraversalAlgorithm(){
    	return this.traversalAlgorithm;
    }
    
    public int getNodeId(){
    	return nodeId;
    }
    
    //Strip off common words but preserve order
    private static class QueryStripper{
    	
    	private String stringToStrip;
    	private Jedis redis;
    	
    	QueryStripper( String stringToStrip ){
    		this.stringToStrip = stringToStrip;
    		redis = new Jedis("localhost", 6379);
            redis.connect();
    		try {
				warmUpCommonWords();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	private void warmUpCommonWords() throws IOException {
            AutoComplete autoComplete = new AutoComplete(redis);
            autoComplete.loadCommonCorpus("H:\\apps\\xp\\Desktop\\commonCorpus.txt");            
		}

		public LinkedList<String> strip(){
    		LinkedList<String> filteredList = new LinkedList<String>();
    		
    		return filteredList;
    	}
		
		Jedis getRedisInstance(){
			return redis;
		}
    	
    }
}