package com.example.jsonschema.component;

import com.example.jsonschema.dto.MaritalStatus;
import com.example.jsonschema.dto.Person;
import com.example.jsonschema.dto.Qualification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

//@Component
public class JsonSchemaValidation {
    public static void main(final String[] args) throws IOException {
        validateJson();
    }

    private static void validateJson() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        //Reader reader = new FileReader(new File("src/main/resources/JSONSchema.schema"));

        //org.json.simple.JSONObject jsonSchema = (JSONObject) jsonParser.parse(reader);
        // Changing from FileReader to Files.newInputStream(Paths.get(fileName)) to avoid the performance issue
        // pointed out by PMD
        /*final JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileReader(new File("src/main/resources/JSONSchema.schema"))));*/
        // the following instantiations help prevent Garbage Collection pauses, no finalization
        final JSONObject jsonSchema =
                new JSONObject(new JSONTokener(
                Files.newInputStream(Paths.get("src/main/resources/JSONSchema.schema"))));

        //JSONObject jsonSubject = new JSONObject(new JSONTokener(JSONSchemaUnitTest.class.getResourceAsStream(
        //        "/product_invalid.json")));

        final Schema schema = SchemaLoader.load(jsonSchema);

        final Person person = getPersonDetails();
        final String personJSON = objectMapper.writeValueAsString(person);
        //System.out.println("personJSON - "+personJSON);
        schema.validate(new JSONObject(personJSON));
    }

    private static Person getPersonDetails() {
        final Qualification qualification1 = Qualification.builder()
                .qualificationName("B.E")
                .passingYear(2011)
                .build();

        final Qualification qualification2 = Qualification.builder()
                .qualificationName("M.Tech")
                //.passingYear(2014)
                .build();

        return Person.builder()
                .id(12_121)
                .age(26)
                .citizen(true)
                .dob(LocalDate.now().minusYears(26))
                .maritalStatus(MaritalStatus.MARRIED)
                .name("Guru").skills(Arrays.asList("JAVA", "PYTHON", "JS"))
                .qualification(Arrays.asList(qualification1, qualification2))
                .build();
    }
}
