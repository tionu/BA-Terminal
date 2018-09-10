package de.htwg.gib.egkterminal.persistence;

import java.io.File;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.htwg.gib.egkterminal.model.medikationsplan.MedikationsPlan;

public class MedikationsplanProvider {

	private static MedikationsPlan testPlan;

	private static String testPaketPath = "src/test/resources/de/htwg/gib/egkterminal/model/medikationsplan/testpaket";

	public static MedikationsPlan getMedikationsplan() {

		File[] testFiles = new File(testPaketPath).listFiles();
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(MedikationsPlan.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			testPlan = (MedikationsPlan) unmarshaller.unmarshal(testFiles[new Random().nextInt(testFiles.length)]);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return testPlan;
	}

}
