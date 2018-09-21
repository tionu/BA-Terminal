package de.htwg.gib.egkterminal.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

class MedikationsplanProviderTest {

	private MedikationsplanProvider medikationsplanProvider;

	@BeforeEach
	void setUp() throws Exception {
		medikationsplanProvider = new MedikationsplanProvider();
	}

	@Test
	void testLoadRandomMedikationsplan() {
		assertNotNull(medikationsplanProvider.getMedikationsplan());
	}

	@Test
	void testMedikationsplanValid() throws JAXBException {
		MedikationsPlan plan = medikationsplanProvider.getMedikationsplan();
		assertTrue(plan.getInstanzId().length() == 32);
		assertNotNull(plan.getPatient());
		assertNotNull(plan.getErsteller());
		assertNotNull(plan.getBlock());
		assertNotNull(plan.getVersionsnummer());
		assertNotNull(plan.getSprachLaenderkennzeichen());
	}

}
