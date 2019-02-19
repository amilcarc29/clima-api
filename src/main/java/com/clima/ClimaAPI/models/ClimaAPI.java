package com.clima.ClimaAPI.models;


import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;

public class ClimaAPI {

	@Id
	public ObjectId _id;
	
	public int dias;
	public String pronostico;
	 
	public ClimaAPI() {}

	public ClimaAPI(ObjectId _id, int dias, String pronostico) {
		this._id = _id;
		this.dias = dias;
		this.pronostico = pronostico;
	}
	
	public ClimaAPI(int dias, String pronostico) {
		this.dias = dias;
		this.pronostico = pronostico;
	}

	public String get_id() {
		return this._id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public String getPronostico() {
		return pronostico;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}

	@Override
	public String toString() {
		JSONObject clima = new JSONObject();
		clima.put("dias", dias);
		clima.put("clima", pronostico);
		return clima.toString();
	}
}
