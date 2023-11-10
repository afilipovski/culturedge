package models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Tag {
    @JacksonXmlProperty(isAttribute = true)
    public String k;

    @JacksonXmlProperty(isAttribute = true)
    public String v;
}
