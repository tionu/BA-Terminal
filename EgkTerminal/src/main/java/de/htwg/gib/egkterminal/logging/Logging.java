package de.htwg.gib.egkterminal.logging;

import org.apache.logging.log4j.core.config.Configurator;

public class Logging {
	private static final String LOG_CONFIG = "log4j2.xml";

	public static void setup() {
		Configurator.initialize("log", LOG_CONFIG);
	}

}
