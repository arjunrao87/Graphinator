package helpers.neo.framework;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * 
 * @author arjrao
 *
 */
public class NeoRetriever {
	
	private static final NeoConnection NEO_CONNECTION = new NeoConnection();
	private final NeoResponse neoResponse = new NeoResponse();
	
	//=============================== ENUM DECLARATIONS ==============================================//
	
	public enum RequestType{
		GET,
		POST,
		DELETE
	}
	
	//================================  NEO OPERATIONS ================================================//
	/**
	 * 
	 * @param nodeId : Entry node or just the actual node you want to query
	 * @param traversalAlgorithm : To define traversal to achieve end goal
	 * @return JSON response
	 */
	public String retrieve( int nodeId, String traversalAlgorithm ){
		
		
//        ExecutionEngine engine = new ExecutionEngine( null );
//        //tx = graphDb.beginTx();
////        ExecutionResult result = engine.execute( "start n=node(*) where n.value = 'PG1' return n, n.value" );
//        ExecutionResult result = engine.execute( " START n=node(1) "//where n.value = 'PG1' return n, n.value" );//+
//												+ " MATCH n-[?*]->()-[:BENCHMARKS]->d"+
//												 " RETURN d, d.value" );
//        System.out.println("Result1 = "+ result.dumpToString());
		
		return traversalAlgorithm;
//		try{
//			if ( StringUtils.isEmpty( traversalAlgorithm ) ){
//				return retrieveById( nodeId );
//			}
//				return retrieveByQuery( nodeId, traversalAlgorithm );
//			} catch( URISyntaxException e ){
//				System.err.println( e.getMessage() );
//		}
//		return "";
	}
	
	/**
	 * 
	 * @param id
	 * @return JSON
	 * @throws URISyntaxException 
	 */
	public String retrieveById( int nodeId ) throws URISyntaxException{
		final URI traverserURI = new URI(  getNodeURI( nodeId ) );
		return traverse( traverserURI, null, RequestType.GET );
	}
	
	public String retrieveByQuery( int nodeId, String traversalAlgorithm ) throws URISyntaxException{
		final URI traverserURI = new URI(  getNodeURI( nodeId ) + "/traverse/node" );
		return traverse( traverserURI, traversalAlgorithm, RequestType.POST );
	}
	
	public String traverse( URI traverserURI, String traversalAlgorithm, RequestType requestType ){
		final ClientResponse response = sendRequest( traverserURI, traversalAlgorithm, requestType );	
		neoResponse.setResponseStatus( response );
		neoResponse.setJsonResponse( response );
		return neoResponse.getJSONResponse();		
	}

	private ClientResponse sendRequest( URI traverserURI, String jsonPayload, RequestType requestType ) {
		Builder requestBuilder = null;
		try {
			requestBuilder = getWebResource(new URI("http://localhost:7474/db/data/cypher?includeStats=true"))// traverserURI )
												.accept( MediaType.APPLICATION_JSON )
												.type( MediaType.APPLICATION_JSON );
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
											
		if( !StringUtils.isEmpty( jsonPayload ) ){
			requestBuilder.entity( jsonPayload );
		}
		
		ClientResponse response = null;
		if( requestType == RequestType.POST ){
			response = requestBuilder.post( ClientResponse.class );
		} else if ( requestType == RequestType.GET){
			response = requestBuilder.get( ClientResponse.class );
		} else if ( requestType == RequestType.DELETE ){
			response = requestBuilder.delete( ClientResponse.class );
		}
		
		return response;
	}

	private WebResource getWebResource( URI uri ){
		return Client.create().resource( uri );
	}
	
	private String getNodeURI( int nodeId ){
		return NEO_CONNECTION.getServerURI() + "node"+ "/" + nodeId;
	}
	
	//================================= GETTERS ======================================================//
	
	public NeoResponse getNeoResponse(){
		return this.neoResponse;
	}
	
	//================================= STATIC INNER CLASS ======================================================//

	public static class NeoResponse {

		private int responseStatus;
		private String jsonResponse;
		
		//================================ SETTERS AND GETTERS ========================================//
		
		public void setResponseStatus( ClientResponse response ){
			this.responseStatus = response.getStatus();
		}
		
		public void setJsonResponse( ClientResponse response ){
			this.jsonResponse = response.getEntity( String.class );
		}
		
		public boolean getResponseStatus(){
			return this.responseStatus == 0 ? false : true;
		}

		public String getJSONResponse(){
			return jsonResponse;
		}

	}


}
