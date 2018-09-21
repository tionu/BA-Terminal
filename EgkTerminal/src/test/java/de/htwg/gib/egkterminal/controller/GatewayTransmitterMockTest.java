package de.htwg.gib.egkterminal.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.htwg.gib.egkterminal.logic.GatewayTransmitter;
import de.htwg.gib.egkterminal.model.CipherObject;

class GatewayTransmitterMockTest {

	@Mock
	private CloseableHttpClient httpClient;

	@InjectMocks
	private GatewayTransmitter gatewayTransmitter;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testReceiveValidUuidFromGatewayServer() throws IOException {
		CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
		HttpEntity entity = Mockito.mock(HttpEntity.class);
		when(entity.getContent())
				.thenReturn(new ByteArrayInputStream("{\"uuid\":\"f91c6f4e-069b-47f1-8c4f-e6f5e2f9e27f\"}".getBytes()));
		when(response.getEntity()).thenReturn(entity);
		when(httpClient.execute(any(HttpPost.class))).thenReturn(response);

		UUID uuid = gatewayTransmitter.post(new CipherObject("cipherText", "nonce"));

		assertTrue(uuid.toString().length() == 36);
	}

}
