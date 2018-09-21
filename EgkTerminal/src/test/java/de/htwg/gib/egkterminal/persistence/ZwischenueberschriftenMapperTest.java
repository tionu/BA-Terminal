package de.htwg.gib.egkterminal.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ZwischenueberschriftenMapperTest {

	@Test
	void testCodeLutEqualsLutschtabletten() {
		ZwischenueberschriftenMapper zwischenueberschriftformen = new ZwischenueberschriftenMapper();

		String ueberschrift = zwischenueberschriftformen.getUeberschrift("416");
		assertEquals("Anwendung unter die Haut", ueberschrift);
	}

}
