package helpers.nlp.parser;

import utils.GraphinatorManager;

public class CypherBuilder {

	private String cypherQuery;
	private StringBuilder cypherBuilder;
	private QueryToCypher queryParams;
	
	public CypherBuilder(){
		this.cypherBuilder = new StringBuilder();
		this.queryParams = GraphinatorManager.getQueryParams();
	}

//	public String build(){
//		this.cypherBuilder.append()
//	}


	public String getCypherQuery(){
		return this.cypherQuery;
	}
}
