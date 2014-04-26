package com.nascent.android.glass.glasshackto.greenpfinder;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.simple.JSONValue;

import android.content.Context;

public class JSONParser {
	// http://www.oracle.com/technetwork/articles/java/json-1973242.html
	
	// http://docs.oracle.com/javaee/7/api/javax/json/stream/JsonParser.html
	
	// Or we can use Google's simple-json toolkit: 
	// https://code.google.com/p/json-simple/wiki/DecodingExamples
	
	
	public Object readJSONFile(Context context, String filename) {

		// TODO: load JSON file into string
		InputStream contents = context.getResources().openRawResource(R.raw.greenp);

		return parseJSONContents(contents);
	}

	public Object parseJSONContents(Object contents) {
		// TODO: 
		Object obj = JSONValue.parse(contents.toString());
		
		System.out.println(obj);
		
		return null;
	}
	
}
