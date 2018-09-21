package de.htwg.gib.egkterminal.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel;

class ArzneimittelServiceTest {

	@Test
	void testGetArzneimittelForPharmazentralnummer() {
		ArzneimittelService arzneimittelService = new ArzneimittelService();

		Arzneimittel arzneimittel = arzneimittelService.getArzneimittel("1725082");
		assertEquals("Metformin AbZ", arzneimittel.getHandelsname());
	}

}
