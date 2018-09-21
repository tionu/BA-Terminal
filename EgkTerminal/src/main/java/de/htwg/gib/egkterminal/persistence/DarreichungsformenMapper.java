package de.htwg.gib.egkterminal.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.darreichungsform.Darreichungsformen;
import de.htwg.gib.egkterminal.model.medikationsplan.darreichungsform.Darreichungsformen.Schluesseltabelle.Darreichungsform;

public class DarreichungsformenMapper {

	private static final String DARREICHUNGSFORMEN_XML = "/model/medikationsplan/darreichungsform/S_BMP_DARREICHUNGSFORM_V1.02.xml";
	private List<Darreichungsform> darreichungsformKodierungen;

	public DarreichungsformenMapper() {
		darreichungsformKodierungen = new ArrayList<>();
		Darreichungsformen darreichungsformen = null;
		JAXBContext jc;
		try (InputStream source = getClass().getResourceAsStream(DARREICHUNGSFORMEN_XML)) {
			jc = JAXBContext.newInstance(Darreichungsformen.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			darreichungsformen = (Darreichungsformen) unmarshaller.unmarshal(source);
			darreichungsformKodierungen = darreichungsformen.getSchluesseltabelle().getKodierungen();
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getForm(String code) {
		return darreichungsformKodierungen.stream().filter(darreichungsform -> code.equals(darreichungsform.getCode()))
				.map(Darreichungsform::getForm).findAny().orElse("");
	}

	public String getBezeichnungIFA(String code) {
		return darreichungsformKodierungen.stream().filter(darreichungsform -> code.equals(darreichungsform.getCode()))
				.map(Darreichungsform::getBezeichnungIFA).findAny().orElse("");
	}

}
