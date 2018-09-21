package de.htwg.gib.egkterminal.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DosiereinheitenMapperTest {

	@Test
	void testCodeMequalsMioIe() {
		DosiereinheitenMapper dosiereinheiten = new DosiereinheitenMapper();

		String einheit = dosiereinheiten.getEinheit("m");
		String bedeutung = dosiereinheiten.getBedeutung("m");

		assertEquals("Mio IE", einheit);
		assertEquals("Million Internationale Einheiten", bedeutung);
	}

}
