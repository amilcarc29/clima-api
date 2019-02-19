package com.clima.Utils;

public class Recta {
	int a;
	int b;

	public Recta(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public boolean rectaIncluyePunto(Punto punto) {
		return punto.getY() == (a * punto.getX()) + b;
	}

	@Override
	public String toString() {
		return String.format("%dx + %d = y", a, b);
	}
}
