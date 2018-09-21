package de.htwg.gib.egkterminal.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift.Zwischenueberschriften;
import de.htwg.gib.egkterminal.model.medikationsplan.zwischenueberschrift.Zwischenueberschriften.Schluesseltabelle.Zwischenueberschrift;

public class ZwischenueberschriftenMapper {

	private static final File ZWISCHENUEBERSCHRIFTEN_XML = new File(
			"src/main/resources/de/htwg/gib/egkterminal/model/medikationsplan/zwischenueberschrift/S_BMP_ZWISCHENUEBERSCHRIFT_V1.01.xml");
	private List<Zwischenueberschrift> zwischenueberschriftKodierungen;

	public ZwischenueberschriftenMapper() {
		zwischenueberschriftKodierungen = new ArrayList<>();
		Zwischenueberschriften zwischenueberschriften = null;
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Zwischenueberschriften.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			zwischenueberschriften = (Zwischenueberschriften) unmarshaller.unmarshal(ZWISCHENUEBERSCHRIFTEN_XML);
			zwischenueberschriftKodierungen = zwischenueberschriften.getSchluesseltabelle().getKodierungen();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public String getUeberschrift(String code) {
		return zwischenueberschriftKodierungen.stream()
				.filter(zwischenueberschrift -> code.equals(zwischenueberschrift.getCode()))
				.map(Zwischenueberschrift::getUeberschrift).findAny().orElse("");
	}

}
