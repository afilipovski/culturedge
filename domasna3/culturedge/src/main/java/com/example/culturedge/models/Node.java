package com.example.culturedge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node {
    @JacksonXmlProperty(isAttribute = true)
    public long id;

    @JacksonXmlProperty(isAttribute = true)
    public double lat;

    @JacksonXmlProperty(isAttribute = true)
    public double lon;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "tag")
    public List<Tag> tags;

    public String getProperty(String key) {
        if (tags==null)
            return null;
        List<Tag> matchingTags = new ArrayList<>();
        matchingTags = tags.stream().filter(t -> t.k.equals(key)).collect(Collectors.toList());
        if (matchingTags.size() == 0)
            return null;
        return matchingTags.get(0).v;
    }
}
