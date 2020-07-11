//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.11 at 01:06:03 PM CEST 
//


package com.example.voziloservice.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Zahtev complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Zahtev"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vozila" type="{http://example.com/voziloservice/xsd}Vozilo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="stanje" type="{http://example.com/voziloservice/xsd}Stanje"/&gt;
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="vremeOdobrenja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="vremeKreiranja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="podnosilac" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="izdavac" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="izdavacMail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zahtev", propOrder = {
    "vozila",
    "stanje",
    "datumOd",
    "datumDo",
    "vremeOdobrenja",
    "vremeKreiranja",
    "podnosilac",
    "izdavac",
    "izdavacMail"
})
public class Zahtev {

    protected List<Vozilo> vozila;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Stanje stanje;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumDo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vremeOdobrenja;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vremeKreiranja;
    protected long podnosilac;
    protected long izdavac;
    @XmlElement(required = true)
    protected String izdavacMail;

    /**
     * Gets the value of the vozila property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vozila property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVozila().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vozilo }
     * 
     * 
     */
    public List<Vozilo> getVozila() {
        if (vozila == null) {
            vozila = new ArrayList<Vozilo>();
        }
        return this.vozila;
    }

    /**
     * Gets the value of the stanje property.
     * 
     * @return
     *     possible object is
     *     {@link Stanje }
     *     
     */
    public Stanje getStanje() {
        return stanje;
    }

    /**
     * Sets the value of the stanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stanje }
     *     
     */
    public void setStanje(Stanje value) {
        this.stanje = value;
    }

    /**
     * Gets the value of the datumOd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumOd() {
        return datumOd;
    }

    /**
     * Sets the value of the datumOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumOd(XMLGregorianCalendar value) {
        this.datumOd = value;
    }

    /**
     * Gets the value of the datumDo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumDo() {
        return datumDo;
    }

    /**
     * Sets the value of the datumDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDo(XMLGregorianCalendar value) {
        this.datumDo = value;
    }

    /**
     * Gets the value of the vremeOdobrenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVremeOdobrenja() {
        return vremeOdobrenja;
    }

    /**
     * Sets the value of the vremeOdobrenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVremeOdobrenja(XMLGregorianCalendar value) {
        this.vremeOdobrenja = value;
    }

    /**
     * Gets the value of the vremeKreiranja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVremeKreiranja() {
        return vremeKreiranja;
    }

    /**
     * Sets the value of the vremeKreiranja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVremeKreiranja(XMLGregorianCalendar value) {
        this.vremeKreiranja = value;
    }

    /**
     * Gets the value of the podnosilac property.
     * 
     */
    public long getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     * 
     */
    public void setPodnosilac(long value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the izdavac property.
     * 
     */
    public long getIzdavac() {
        return izdavac;
    }

    /**
     * Sets the value of the izdavac property.
     * 
     */
    public void setIzdavac(long value) {
        this.izdavac = value;
    }

    /**
     * Gets the value of the izdavacMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIzdavacMail() {
        return izdavacMail;
    }

    /**
     * Sets the value of the izdavacMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIzdavacMail(String value) {
        this.izdavacMail = value;
    }

}
