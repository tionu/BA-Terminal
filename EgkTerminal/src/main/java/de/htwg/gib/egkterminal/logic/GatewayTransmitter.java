package de.htwg.gib.egkterminal.logic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.htwg.gib.egkterminal.config.PropertyConfig;
import de.htwg.gib.egkterminal.logging.Logging;
import de.htwg.gib.egkterminal.model.CipherObject;

public class GatewayTransmitter {

	private static PropertyConfig propertyConfig;
	static {
		propertyConfig = PropertyConfig.getInstance();
	}

	private static Logger log;
	static {
		Logging.setup();
		log = LogManager.getLogger(propertyConfig.getTerminal().getProperty("logging"));
	}

	private URI gatewayServer;
	private ObjectMapper mapper;

	public GatewayTransmitter() {

		try {
			this.gatewayServer = new URI(propertyConfig.getTerminal().getProperty("gateway.server"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		mapper.setDateFormat(df);
	}

	public UUID transmit(CipherObject cipherObject) throws IOException {
		log.info("transmit cipher object to gateway server: " + gatewayServer.toString());

		String cipherJson = mapper.writeValueAsString(cipherObject);
		log.info("post: " + cipherJson);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(gatewayServer);
		StringEntity entity = new StringEntity(cipherJson);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("Accept", "application/json");
		CloseableHttpResponse response = httpClient.execute(httpPost);

		JsonReader jsonReader = Json.createReader(response.getEntity().getContent());
		JsonObject jsonReponse = jsonReader.readObject();

		log.info("response: " + jsonReponse);

		try {
			return UUID.fromString(jsonReponse.getString("uuid"));
		} catch (IllegalArgumentException e) {
			throw new IOException("Received invalid uuid.");
		}

	}

}
