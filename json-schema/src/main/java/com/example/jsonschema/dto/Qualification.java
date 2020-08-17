package com.example.jsonschema.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;

@Builder
public class Qualification {

    private int passingYear;
    private String qualificationName;

    @JsonGetter("passing-year")
    public int getPassingYear() {
        return passingYear;
    }

    @JsonSetter("passing-year")
    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    @JsonGetter("qualification-name")
    public String getQualificationName() {
        return qualificationName;
    }

    @JsonSetter("qualification-name")
    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}
