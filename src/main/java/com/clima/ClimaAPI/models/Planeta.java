package com.clima.ClimaAPI.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Planeta {
	
	@Id
	public ObjectId _id;
	public String nombre;
	public float velocidadAngular;
	public float distanciaAlSol;
	public String sentido;
	
	public Planeta() {}

	public Planeta(ObjectId _id, String nombre, float velocidadAngular, float distanciaAlSol, String sentido) {
		super();
		this._id = _id;
		this.nombre = nombre;
		this.velocidadAngular = velocidadAngular;
		this.distanciaAlSol = distanciaAlSol;
		this.sentido = sentido;
	}
	
	public Planeta(String nombre, float velocidadAngular, float distanciaAlSol, String sentido) {
		super();
		this.nombre = nombre;
		this.velocidadAngular = velocidadAngular;
		this.distanciaAlSol = distanciaAlSol;
		this.sentido = sentido;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getVelocidadAngular() {
		return velocidadAngular;
	}

	public void setVelocidadAngular(float velocidadAngular) {
		this.velocidadAngular = velocidadAngular;
	}

	public float getDistanciaAlSol() {
		return distanciaAlSol;
	}

	public void setDistanciaAlSol(float distanciaAlSol) {
		this.distanciaAlSol = distanciaAlSol;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}
}
