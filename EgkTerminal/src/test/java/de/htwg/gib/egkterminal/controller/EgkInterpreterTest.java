package de.htwg.gib.egkterminal.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.Egk;

class EgkInterpreterTest {

	private static EgkInterpreter egkInterpreter;
	private static Egk egk;

	@BeforeAll
	static void setup() {
		egkInterpreter = new EgkInterpreter();
		egk = egkInterpreter.readEgk();
	}

	@Test
	void testVersicherungsId9stellig() {
		assertTrue(String.valueOf(egk.getVersicherter().getHealthInsuranceProviderNumber()).length() == 9);
	}

	@Test
	void testVersicherterName() {
		assertTrue(egk.getVersicherter().getGivenName().length() > 0);
		assertTrue(egk.getVersicherter().getSurname().length() > 0);
	}

	@Test
	void testMedikationsplanEnthaeltMedikationsliste() {
		assertTrue(egk.getMedikationsplan().getBlock().size() > 0);
	}

	@Test
	void testMedikationsplanErstellerVorhanden() {
		assertNotNull(egk.getMedikationsplan().getErsteller());
	}

	@Test
	void testMedikationsplanInstanzIdVorhanden() {
		assertTrue(egk.getMedikationsplan().getInstanzId().length() == 32);
	}

}
