package de.htwg.gib.egkterminal.logic;

import de.gecko.egkfeuer.model.ekg.DelegatingToPatientConverter;
import de.gecko.egkfeuer.model.ekg.v51.ToPatientConverterV51;
import de.gecko.egkfeuer.model.ekg.v52.ToPatientConverterV52;
import de.gecko.egkfeuer.service.CardReaderService;
import de.gecko.egkfeuer.service.CardReaderServiceImpl;
import de.gecko.egkfeuer.service.CardTerminalsServiceImpl;
import de.htwg.gib.egkterminal.model.Egk;
import de.htwg.gib.egkterminal.persistence.MedikationsplanProvider;

public class EgkInterpreter {

	private Egk egk;
	private CardReaderService cardReaderService;
	private MedikationsplanProvider medikationsplanProvider;

	public EgkInterpreter() {
		cardReaderService = new CardReaderServiceImpl(
				new DelegatingToPatientConverter(new ToPatientConverterV51(), new ToPatientConverterV52()),
				new CardTerminalsServiceImpl());
		medikationsplanProvider = new MedikationsplanProvider();
	}

	public Egk readEgk() {
		egk = new Egk();
		egk.setVersicherter(cardReaderService.read());
		egk.setMedikationsplan(medikationsplanProvider.getMedikationsplan());
		return egk;
	}

}
