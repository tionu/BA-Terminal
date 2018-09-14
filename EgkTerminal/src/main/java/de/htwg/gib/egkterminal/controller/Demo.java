package de.htwg.gib.egkterminal.controller;

import de.htwg.gib.egkterminal.model.Egk;

public class Demo {

	private static EgkInterpreter egkInterpreter;
	private static Egk egk;

	public static void main(String[] args) {
		egkInterpreter = new EgkInterpreter();
		egk = egkInterpreter.readEgk();
		System.out.println("Name Versicherter: " + egk.getVersicherter().getSurname());
	}

}
