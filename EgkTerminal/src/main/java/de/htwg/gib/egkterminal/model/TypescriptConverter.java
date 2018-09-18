package de.htwg.gib.egkterminal.model;

import java.io.File;

import cz.habarta.typescript.generator.Input;
import cz.habarta.typescript.generator.JsonLibrary;
import cz.habarta.typescript.generator.Output;
import cz.habarta.typescript.generator.Settings;
import cz.habarta.typescript.generator.TypeScriptGenerator;
import cz.habarta.typescript.generator.TypeScriptOutputKind;
import de.htwg.gib.egkterminal.model.medikationsplan.Freitextzeile;
import de.htwg.gib.egkterminal.model.medikationsplan.Medikation;
import de.htwg.gib.egkterminal.model.medikationsplan.Rezeptur;

public class TypescriptConverter {

	public static void main(String[] args) {
		Input input = Input.from(Egk.class, Medikation.class, Rezeptur.class, Freitextzeile.class);
		Output output = Output.to(new File("./src/main/resources/de/htwg/gib/egkterminal/model/model.d.ts"));
		Settings settings = new Settings();
		settings.outputKind = TypeScriptOutputKind.global;
		settings.jsonLibrary = JsonLibrary.jackson2;
		settings.customTypeMappings.put("javax.xml.datatype.XMLGregorianCalendar", "Date");
		TypeScriptGenerator generator = new TypeScriptGenerator(settings);
		generator.generateTypeScript(input, output);
	}

}
