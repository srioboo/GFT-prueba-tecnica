package org.prueba.gft.prices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@AnalyzeClasses(packages = "org.prueba.gft")
class HexagonalArchitectureTest {

	private final JavaClasses importedClasses = new ClassFileImporter()
		.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
		.importPackages("org.prueba.gft");

	@DisplayName("Test domain layer dependencies")
	@Test
	void domainShouldNotDependOnAnything() {
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..adapters..", "..application..", "..mapper..")

			.check(importedClasses);
	}

	@DisplayName("Test application layer dependencies")
	@Test
	void applicationShouldNotDependOnAnything() {
		ArchRuleDefinition.noClasses()
			.that().resideInAPackage("..application..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..adapters..", "..mapper..")
			.check(importedClasses);
	}
}
