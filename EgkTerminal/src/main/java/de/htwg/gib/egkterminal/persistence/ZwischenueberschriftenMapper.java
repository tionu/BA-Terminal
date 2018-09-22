package de.htwg.gib.egkterminal.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift.Zwischenueberschriften;
import de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift.Zwischenueberschriften.Schluesseltabelle.Zwischenueberschrift;

public class ZwischenueberschriftenMapper {

	private static final String ZWISCHENUEBERSCHRIFTEN_XML = "/model/medikationsplan/zwischenueberschrift/S_BMP_ZWISCHENUEBERSCHRIFT_V1.01.xml";
	private List<Zwischenueberschrift> zwischenueberschriftKodierungen;

	public ZwischenueberschriftenMapper() {
		zwischenueberschriftKodierungen = new ArrayList<>();
		Zwischenueberschriften zwischenueberschriften = null;
		JAXBContext jc;
		try (InputStream source = getClass().getResourceAsStream(ZWISCHENUEBERSCHRIFTEN_XML)) {
			jc = JAXBContext.newInstance(Zwischenueberschriften.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			zwischenueberschriften = (Zwischenueberschriften) unmarshaller.unmarshal(source);
			zwischenueberschriftKodierungen = zwischenueberschriften.getSchluesseltabelle().getKodierungen();
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getUeberschrift(String code) {
		return zwischenueberschriftKodierungen.stream()
				.filter(zwischenueberschrift -> code.equalsIgnoreCase(zwischenueberschrift.getCode()))
				.map(Zwischenueberschrift::getUeberschrift).findAny().orElse("");
	}

}
