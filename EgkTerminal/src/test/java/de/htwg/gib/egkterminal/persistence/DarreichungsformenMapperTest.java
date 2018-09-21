package de.htwg.gib.egkterminal.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DarreichungsformenMapperTest {

	@Test
	void testCodeLutEqualsLutschtabletten() {
		DarreichungsformenMapper darreichungsformen = new DarreichungsformenMapper();

		String form = darreichungsformen.getForm("LUT");
		String bezeichnungIfa = darreichungsformen.getBezeichnungIFA("LUT");

		assertEquals("LuTabl", form);
		assertEquals("Lutschtabletten", bezeichnungIfa);
	}
}
