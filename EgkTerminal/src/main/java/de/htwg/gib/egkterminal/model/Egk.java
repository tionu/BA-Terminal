package de.htwg.gib.egkterminal.model;

import de.gecko.egkfeuer.model.EgkPatient;
import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

public class Egk {

	private EgkPatient versicherter;
	private MedikationsPlan medikationsplan;

	public EgkPatient getVersicherter() {
		return versicherter;
	}

	public void setVersicherter(EgkPatient versicherter) {
		this.versicherter = versicherter;
	}

	public MedikationsPlan getMedikationsplan() {
		return medikationsplan;
	}

	public void setMedikationsplan(MedikationsPlan medikationsplan) {
		this.medikationsplan = medikationsplan;
	}

}
