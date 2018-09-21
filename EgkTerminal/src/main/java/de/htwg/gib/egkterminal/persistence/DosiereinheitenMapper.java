package de.htwg.gib.egkterminal.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.dosiereinheit.Dosiereinheiten;
import de.htwg.gib.egkterminal.model.medikationsplan.dosiereinheit.Dosiereinheiten.Schluesseltabelle.Dosiereinheit;

public class DosiereinheitenMapper {

	private static final String DOSIEREINHEITEN_XML = "/model/medikationsplan/dosiereinheit/S_BMP_DOSIEREINHEIT_V1.01.xml";
	private List<Dosiereinheit> dosiereinheitKodierungen;

	public DosiereinheitenMapper() {
		dosiereinheitKodierungen = new ArrayList<>();
		Dosiereinheiten dosiereinheiten = null;
		JAXBContext jc;
		try (InputStream source = getClass().getResourceAsStream(DOSIEREINHEITEN_XML)) {
			jc = JAXBContext.newInstance(Dosiereinheiten.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			dosiereinheiten = (Dosiereinheiten) unmarshaller.unmarshal(source);
			dosiereinheitKodierungen = dosiereinheiten.getSchluesseltabelle().getKodierungen();
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getEinheit(String code) {
		return dosiereinheitKodierungen.stream().filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Dosiereinheit::getEinheit).findAny().orElse("");
	}

	public String getBedeutung(String code) {
		return dosiereinheitKodierungen.stream().filter(dosiereinheit -> code.equals(dosiereinheit.getCode()))
				.map(Dosiereinheit::getBedeutung).findAny().orElse("");
	}

}
