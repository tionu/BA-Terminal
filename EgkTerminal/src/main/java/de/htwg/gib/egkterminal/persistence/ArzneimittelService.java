package de.htwg.gib.egkterminal.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe;
import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel;

public class ArzneimittelService {

	private static final String ARZNEIMITTEL_XML = "/model/medikationsplan/arzneimittel/ArzneimittelListe.xml";
	private List<Arzneimittel> arzneimittelListe;

	public ArzneimittelService() {
		arzneimittelListe = new ArrayList<>();
		JAXBContext jc;
		try (InputStream source = getClass().getResourceAsStream(ARZNEIMITTEL_XML)) {
			jc = JAXBContext.newInstance(ArzneimittelListe.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			arzneimittelListe = ((ArzneimittelListe) unmarshaller.unmarshal(source)).getArzneimittelListe();
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	public Arzneimittel getArzneimittel(String pharmazentralnummer) {
		return arzneimittelListe.stream()
				.filter(arznei -> pharmazentralnummer.equalsIgnoreCase(arznei.getPharmazentralnummer())).findAny()
				.orElse(null);
	}

}
