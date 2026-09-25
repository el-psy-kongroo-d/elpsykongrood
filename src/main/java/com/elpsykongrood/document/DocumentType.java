package com.elpsykongrood.document;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DocumentType {

    RESUME("resume.pdf"),
    PORTFOLIO("portfolio.pdf");

    private final String filename;

    public String filename() {
        return filename;
    }

}
