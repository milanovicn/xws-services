//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.20 at 01:55:03 PM CEST 
//


package com.example.voziloservice.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vozilo" type="{http://example.com/voziloservice/xsd}Vozilo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vozilo"
})
@XmlRootElement(name = "postVoziloRequest")
public class PostVoziloRequest {

    @XmlElement(required = true)
    protected Vozilo vozilo;

    /**
     * Gets the value of the vozilo property.
     * 
     * @return
     *     possible object is
     *     {@link Vozilo }
     *     
     */
    public Vozilo getVozilo() {
        return vozilo;
    }

    /**
     * Sets the value of the vozilo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vozilo }
     *     
     */
    public void setVozilo(Vozilo value) {
        this.vozilo = value;
    }

}