package de.htwg.gib.egkterminal.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.swing.ImageIcon;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;

import de.gecko.egkfeuer.exception.smartcard.EgkNotFoundException;
import de.htwg.gib.egkterminal.config.PropertyConfig;
import de.htwg.gib.egkterminal.logging.Logging;
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

	public QrCodeViewController() {
		super();
		this.egkInterpreter = new EgkInterpreter();
		this.qrCodeView = new QrCodeView();
		this.gatewayTransmitter = new GatewayTransmitter();
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
					String egkJson = mapper.writeValueAsString(egk);

					log.info("transmit egk to gateway server: " + egkJson);

					UUID uuid = gatewayTransmitter.transmit(new CipherObject(egkJson));

					qrCodeView.getLblEgkData()
							.setText("<html>" + egk.getVersicherter().getSurname() + ", "
									+ egk.getVersicherter().getGivenName() + "<br>Versichertennummer: "
									+ egk.getVersicherter().getHealthInsuranceNumber() + "</html>");

					String appServer = propertyConfig.getTerminal().getProperty("app.server");
					URI qrCodeUrl = new URIBuilder(appServer).setFragment(uuid.toString()).build();

					qrCode = new ImageIcon(QRCodeGenerator.createQRImage(qrCodeUrl.toString()));
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