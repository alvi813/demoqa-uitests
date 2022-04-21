package com.demoqa;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Slf4j
public abstract class CommonConfigurationTest {

    public Document document;
    public static String baseUrl;
    public static String userName;
    public static String password;


    @BeforeSuite(alwaysRun = true)
    public void initialize() {
        document = parseConfigFile();
        baseUrl = document.getElementsByTagName("baseURL").item(0).getTextContent();
        userName = document.getElementsByTagName("userName").item(0).getTextContent();
        password = document.getElementsByTagName("password").item(0).getTextContent();
    }

    public Document parseConfigFile() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            log.info("Unable to get builder which parse xml file.");
        }
        try {
            document = builder.parse(new File("src/main/resources/config.xml"));
        } catch (IOException | SAXException ex) {
            log.info("Unable to parse xml file.");
        }
        return document;
    }
}
