package helpers.nlp.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import redis.clients.jedis.Jedis;

public class AutoComplete {

    private final Jedis redis;

    AutoComplete(final Jedis redis) throws IOException {
        this.redis = redis;
    }

    public void loadCommonCorpus( final String path ) throws IOException{
    	BufferedReader reader = new BufferedReader(new FileReader(path));//inputStreamReader);
        String word;
        while((word = reader.readLine()) != null) {
            word = word.trim();
            // Add the word if the word does not start with #
            if(!word.isEmpty() && !word.startsWith("#")) {
            	addCommonWord(word, "common");
            }
        }
        reader.close();
    }

    private void addCommonWord(final String word, String key) {
        // Add all the possible prefixes of the given word and also the given
        // word with a * suffix.
        redis.zadd(key, 0, word);
    }

}
