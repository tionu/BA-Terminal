package de.htwg.gib.egkterminal.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe;
import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel;

public class ArzneimittelService {

	private static final File ARZNEIMITTEL_XML = new File(
			"src/main/resources/de/htwg/gib/egkterminal/model/medikationsplan/arzneimittel/ArzneimittelListe.xml");
	private List<Arzneimittel> arzneimittelListe;

	public ArzneimittelService() {
		arzneimittelListe = new ArrayList<>();
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(ArzneimittelListe.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			arzneimittelListe = ((ArzneimittelListe) unmarshaller.unmarshal(ARZNEIMITTEL_XML)).getArzneimittelListe();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Arzneimittel getArzneimittel(String pharmazentralnummer) {
		return arzneimittelListe.stream().filter(arznei -> pharmazentralnummer.equals(arznei.getPharmazentralnummer()))
				.findAny().orElse(null);
	}

}
