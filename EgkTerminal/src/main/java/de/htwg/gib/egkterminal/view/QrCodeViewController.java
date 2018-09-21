package de.htwg.gib.egkterminal.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;

import de.gecko.egkfeuer.exception.smartcard.EgkNotFoundException;
import de.htwg.gib.egkterminal.config.PropertyConfig;
import de.htwg.gib.egkterminal.logging.Logging;
import de.htwg.gib.egkterminal.logic.CryptoController;
import de.htwg.gib.egkterminal.logic.EgkInterpreter;
import de.htwg.gib.egkterminal.logic.GatewayTransmitter;
import de.htwg.gib.egkterminal.logic.QRCodeGenerator;
import de.htwg.gib.egkterminal.model.CipherObject;
import de.htwg.gib.egkterminal.model.Egk;

public class QrCodeViewController {

	private static PropertyConfig propertyConfig;
	static {
		propertyConfig = PropertyConfig.getInstance();
	}

	private static Logger log;
	static {
		Logging.setup();
		log = LogManager.getLogger(propertyConfig.getTerminal().getProperty("logging"));
	}

	private QrCodeView qrCodeView;
	private EgkInterpreter egkInterpreter;
	private GatewayTransmitter gatewayTransmitter;
	private String appServer;

	public QrCodeViewController() {
		super();
		this.egkInterpreter = new EgkInterpreter();
		this.qrCodeView = new QrCodeView();
		this.gatewayTransmitter = new GatewayTransmitter();
		this.appServer = propertyConfig.getTerminal().getProperty("app.server");
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QrCodeViewController qrCodeViewController = new QrCodeViewController();
					qrCodeViewController.getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QrCodeView getMainFrame() {
		return qrCodeView;
	}

	private void initialize() {
		qrCodeView.getBtnEgkEinlesen().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Egk egk;
				ImageIcon qrCode = new ImageIcon();

				ObjectMapper mapper = new ObjectMapper();
				DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				mapper.setDateFormat(df);

				try {
					egk = egkInterpreter.readEgk();

					qrCodeView.getLblEgkData()
							.setText("<html>" + egk.getVersicherter().getSurname() + ", "
									+ egk.getVersicherter().getGivenName() + "<br>Versichertennummer: "
									+ egk.getVersicherter().getHealthInsuranceNumber() + "</html>");

					String egkJson = mapper.writeValueAsString(egk);

					CryptoController crypto = new CryptoController();
					byte[] key = crypto.createRandomKey();
					CipherObject cipherObject;

					try {
						cipherObject = crypto.encrypt(egkJson, key);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
						log.error("error encrypting egk object.");
						e1.printStackTrace();
						qrCodeView.getLblEgkData().setText("Verarbeitungsfehler.");
						return;
					}

					UUID uuid = gatewayTransmitter.post(cipherObject);

					String accessToken = uuid.toString() + Base64.getEncoder().encodeToString(key);
					log.info("accesToken=" + accessToken);
					URI qrCodeUri = new URIBuilder(appServer).setFragment(accessToken).build();
					qrCode = new ImageIcon(QRCodeGenerator.createQRImage(qrCodeUri.toString()));

				} catch (EgkNotFoundException e1) {
					qrCodeView.getLblEgkData().setText("Keine Karte gefunden.");
					log.error(e1.toString());
				} catch (WriterException | IOException | URISyntaxException e2) {
					qrCodeView.getLblEgkData().setText("");
					log.error(e2.toString());
				}

				qrCodeView.getLblQrCode().setIcon(qrCode);
			}

		});

	}

}