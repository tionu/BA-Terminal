package de.htwg.gib.egkterminal.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfig {

	private static final String TERMINAL_PATH = "/terminal.properties";

	private static final PropertyConfig instance = new PropertyConfig();

	private Properties terminal;

	protected PropertyConfig() {
		terminal = loadPropertyFile(TERMINAL_PATH);
	}

	public static PropertyConfig getInstance() {
		return instance;
	}

	private Properties loadPropertyFile(String path) {
		Properties properties = new Properties();
		InputStream source = null;
		try {
			source = getClass().getResourceAsStream(path);
			properties.load(source);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;
	}

	public Properties getTerminal() {
		return terminal;
	}

}