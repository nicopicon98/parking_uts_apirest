package com.company.parking.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String data) {
		HashMap<String, String> mapa = new HashMap<>();
		mapa.put("type", type);
		mapa.put("code", code);
		mapa.put("data", data);
		metadata.add(mapa);
	}

}
