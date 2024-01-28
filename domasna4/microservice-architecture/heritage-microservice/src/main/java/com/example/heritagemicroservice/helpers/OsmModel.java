package com.example.heritagemicroservice.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

// Root element for the XML representation of OpenStreetMap data.
@JacksonXmlRootElement(localName = "osm")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsmModel {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "node")
    public List<Node> nodes;

    public List<Node> getNodes() {
        return nodes;
    }
}

