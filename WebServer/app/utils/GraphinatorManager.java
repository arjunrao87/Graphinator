package utils;

import helpers.nlp.parser.QueryToCypher;

public class GraphinatorManager {

	//All static utilities to be used throughout the application
	//since we do not have beans to pass around
	
	private static QueryToCypher queryToCypher;
	
	// DO NOT INITIALIZE
	private GraphinatorManager(){}
	
	public static QueryToCypher getQueryParams(){
		if( queryToCypher == null ){
			queryToCypher = new QueryToCypher();
		}
		return queryToCypher;
	}
}
