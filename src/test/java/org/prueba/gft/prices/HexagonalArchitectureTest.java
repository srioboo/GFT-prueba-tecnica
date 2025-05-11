package org.prueba.gft.prices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;


@AnalyzeClasses(packages = "org.prueba.gft")
public class HexagonalArchitectureTest {

    @Test
    void domainShouldNotDependOnAnything() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.prueba.gft");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..adapters..", "..application..", "..springframework..");

        rule.check(importedClasses);
    }

    /*
    @Test
    void applicationShouldNotDependOnAnything() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("org.prueba.gft");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..adapters..", "..springframework..");

        rule.check(importedClasses);
    }*/

}
