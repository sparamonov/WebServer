package sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import reflection.ReflectionHelper;

public class SaxEmptyHandler extends DefaultHandler {
    private static String CLASSNAME = "class";
    private Object object;
    private String element;

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName != CLASSNAME)
            element = qName;
        else {
            String className = attributes.getValue(0);
            System.out.println("Class name = " + className);
            object = ReflectionHelper.createInstance(className);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        element = null;
    }

    public void characters(char ch[], int start, int length) {
        if (element != null) {
            String value = new String(ch, start, length);
            System.out.println(element + "=" + value);
            ReflectionHelper.setField(object, element, value);
        }
    }

}