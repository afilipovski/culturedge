package com.example.heritagemicroservice.helpers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

// Helper class representing a tag with key-value pairs.
public class Tag {
    // Key attribute for the tag.
    @JacksonXmlProperty(isAttribute = true)
    public String k;

    // Value attribute for the tag.
    @JacksonXmlProperty(isAttribute = true)
    public String v;
}
