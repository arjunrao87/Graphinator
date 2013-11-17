package helpers.nlp.parser;

import java.util.HashMap;
import java.util.Map;

public final class QueryToCypher {

	public enum NeoParam{
		NODE_ID, 
		MATCH_RELATIONSHIP,
		WHERE,
		RETURN_LAST_NODE,
		LIMIT	
	}
	
	public Map<NeoParam, Object> queryParams;
	
	public QueryToCypher(){
		this.queryParams = new HashMap<NeoParam, Object>();
	}
	
	public void addQueryParam( NeoParam key, Object value ){
		queryParams.put(key, value);
	}
	
	public void setQueryParams( Map<NeoParam, Object> map ){
		this.queryParams = map;
	}

	public Object getQueryParam( NeoParam key ){
		return this.queryParams.get( key );
	}
	public Map<NeoParam, Object> getQueryParams(){
		return this.queryParams;
	}

}

