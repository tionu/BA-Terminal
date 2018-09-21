package de.htwg.gib.egkterminal.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.htwg.gib.egkterminal.model.CipherObject;
import de.htwg.gib.egkterminal.persistence.GatewayTransmitter;

class GatewayTransmitterTest {

	private static GatewayTransmitter gatewayTransmitter;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gatewayTransmitter = new GatewayTransmitter();
	}

	@Test
	void testReceiveValidUuidFromGatewayServer() throws IOException {
		UUID uuid = gatewayTransmitter.post(new CipherObject("cipherText", "nonce"));

		assertTrue(uuid.toString().length() == 36);
	}

}
