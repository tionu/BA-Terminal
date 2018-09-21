package de.htwg.gib.egkterminal.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.Medikation;
import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;
import de.htwg.gib.egkterminal.model.medikationsplan.Wirkstoff;
import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel;
import de.htwg.gib.egkterminal.model.medikationsplan.arzneimittel.ArzneimittelListe.Arzneimittel.Arzneistoff;
import tools.Tools;

public class MedikationsplanProvider {

	private static final String TESTPAKET_PATH = "/model/medikationsplan/testpaket/";
	private ZwischenueberschriftenMapper zwischenueberschriften;
	private DosiereinheitenMapper dosiereinheiten;
	private DarreichungsformenMapper darreichungsformen;
	private ArzneimittelService arzneimittelService;

	public MedikationsplanProvider() {
		zwischenueberschriften = new ZwischenueberschriftenMapper();
		dosiereinheiten = new DosiereinheitenMapper();
		darreichungsformen = new DarreichungsformenMapper();
		arzneimittelService = new ArzneimittelService();
	}

	public MedikationsPlan getMedikationsplan() {

		List<String> testFiles = Tools.listResourceFiles(TESTPAKET_PATH);
		String randomTestFile = testFiles.get(new Random().nextInt(testFiles.size()));
		MedikationsPlan testPlan = new MedikationsPlan();
		JAXBContext jc;

		try (InputStream source = getClass().getResourceAsStream(TESTPAKET_PATH + randomTestFile)) {
			jc = JAXBContext.newInstance(MedikationsPlan.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			testPlan = (MedikationsPlan) unmarshaller.unmarshal(source);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		decodeZwischenueberschriften(testPlan);
		decodeArzneimittel(testPlan);
		return testPlan;
	}

	private void decodeZwischenueberschriften(MedikationsPlan plan) {
		plan.getBlock().stream().filter(block -> (block.getZwischenueberschrift() != null)).forEach(block -> {
			block.setZwischenueberschriftFreitext(
					zwischenueberschriften.getUeberschrift(block.getZwischenueberschrift()));
			block.setZwischenueberschrift(null);
		});
	}

	private void decodeArzneimittel(MedikationsPlan plan) {
		plan.getBlock().forEach(block -> {
			block.getMedikationFreitextRezeptur().stream().filter(item -> (item instanceof Medikation))
					.collect(Collectors.toSet()).forEach(item -> {
						Medikation medikation = (Medikation) item;
						decodeDosiereinheit(medikation);
						decodeDarreichungsform(medikation);
						try {
							decodePharmazentralnummer(medikation);
						} catch (NoSuchElementException e) {
							block.getMedikationFreitextRezeptur().remove(item);
						}
					});
		});
	}

	private void decodePharmazentralnummer(Medikation medikation) {
		if (medikation.getPharmazentralnummer() != null) {
			Arzneimittel arzneimittel = arzneimittelService
					.getArzneimittel(medikation.getPharmazentralnummer().toString());
			if (arzneimittel == null) {
				throw new NoSuchElementException("Pharmazentralnummer nicht im Arzneimittelverzeichnis gefunden: "
						+ medikation.getPharmazentralnummer());
			}
			medikation.setHandelsname(arzneimittel.getHandelsname());
			List<Wirkstoff> wirkstoffe = arzneimittel.getArzneistoffe().stream()
					.map(arzneistoff -> convertToWirkstoff(arzneistoff)).collect(Collectors.toList());
			medikation.getWirkstoff().addAll(wirkstoffe);
		}
	}

	private void decodeDarreichungsform(Medikation medikation) {
		if (medikation.getDarreichungsformCode() != null) {
			medikation.setDarreichungsformFreitext(
					darreichungsformen.getBezeichnungIFA(medikation.getDarreichungsformCode()));
			medikation.setDarreichungsformCode(null);
		}
	}

	private void decodeDosiereinheit(Medikation medikation) {
		if (medikation.getDosiereinheitCode() != null) {
			medikation.setDosiereinheitFreitext(dosiereinheiten.getEinheit(medikation.getDosiereinheitCode()));
			medikation.setDosiereinheitCode(null);
		}
	}

	private Wirkstoff convertToWirkstoff(Arzneistoff arzneistoff) {
		Wirkstoff wirkstoff = new Wirkstoff();
		wirkstoff.setWirkstoff(arzneistoff.getWirkstoff());
		wirkstoff.setWirkstaerke(arzneistoff.getWirkstaerke());
		return wirkstoff;
	}

}
