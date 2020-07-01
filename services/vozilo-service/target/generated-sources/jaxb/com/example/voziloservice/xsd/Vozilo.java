//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.01 at 09:14:57 PM CEST 
//


package com.example.voziloservice.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Vozilo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Vozilo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="markaAutomobila" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="modelAutomobila" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipGoriva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipMenjaca" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="klasaVozila" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cenovnikId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="predjenaKilometraza" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ogranicenaKilometraza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CDWProtection" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="brojSedistaDeca" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="iznajmljivacId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="vaziOd" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="vaziDo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vozilo", propOrder = {
    "markaAutomobila",
    "modelAutomobila",
    "tipGoriva",
    "tipMenjaca",
    "klasaVozila",
    "cenovnikId",
    "predjenaKilometraza",
    "ogranicenaKilometraza",
    "cdwProtection",
    "brojSedistaDeca",
    "iznajmljivacId",
    "vaziOd",
    "vaziDo",
    "mesto"
})
public class Vozilo {

    @XmlElement(required = true)
    protected String markaAutomobila;
    @XmlElement(required = true)
    protected String modelAutomobila;
    @XmlElement(required = true)
    protected String tipGoriva;
    @XmlElement(required = true)
    protected String tipMenjaca;
    @XmlElement(required = true)
    protected String klasaVozila;
    @XmlElement(required = true)
    protected String cenovnikId;
    protected double predjenaKilometraza;
    @XmlElement(required = true)
    protected String ogranicenaKilometraza;
    @XmlElement(name = "CDWProtection")
    protected boolean cdwProtection;
    protected int brojSedistaDeca;
    protected long iznajmljivacId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vaziOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar vaziDo;
    @XmlElement(required = true)
    protected String mesto;

    /**
     * Gets the value of the markaAutomobila property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarkaAutomobila() {
        return markaAutomobila;
    }

    /**
     * Sets the value of the markaAutomobila property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarkaAutomobila(String value) {
        this.markaAutomobila = value;
    }

    /**
     * Gets the value of the modelAutomobila property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelAutomobila() {
        return modelAutomobila;
    }

    /**
     * Sets the value of the modelAutomobila property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelAutomobila(String value) {
        this.modelAutomobila = value;
    }

    /**
     * Gets the value of the tipGoriva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipGoriva() {
        return tipGoriva;
    }

    /**
     * Sets the value of the tipGoriva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipGoriva(String value) {
        this.tipGoriva = value;
    }

    /**
     * Gets the value of the tipMenjaca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipMenjaca() {
        return tipMenjaca;
    }

    /**
     * Sets the value of the tipMenjaca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipMenjaca(String value) {
        this.tipMenjaca = value;
    }

    /**
     * Gets the value of the klasaVozila property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKlasaVozila() {
        return klasaVozila;
    }

    /**
     * Sets the value of the klasaVozila property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKlasaVozila(String value) {
        this.klasaVozila = value;
    }

    /**
     * Gets the value of the cenovnikId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCenovnikId() {
        return cenovnikId;
    }

    /**
     * Sets the value of the cenovnikId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCenovnikId(String value) {
        this.cenovnikId = value;
    }

    /**
     * Gets the value of the predjenaKilometraza property.
     * 
     */
    public double getPredjenaKilometraza() {
        return predjenaKilometraza;
    }

    /**
     * Sets the value of the predjenaKilometraza property.
     * 
     */
    public void setPredjenaKilometraza(double value) {
        this.predjenaKilometraza = value;
    }

    /**
     * Gets the value of the ogranicenaKilometraza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOgranicenaKilometraza() {
        return ogranicenaKilometraza;
    }

    /**
     * Sets the value of the ogranicenaKilometraza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOgranicenaKilometraza(String value) {
        this.ogranicenaKilometraza = value;
    }

    /**
     * Gets the value of the cdwProtection property.
     * 
     */
    public boolean isCDWProtection() {
        return cdwProtection;
    }

    /**
     * Sets the value of the cdwProtection property.
     * 
     */
    public void setCDWProtection(boolean value) {
        this.cdwProtection = value;
    }

    /**
     * Gets the value of the brojSedistaDeca property.
     * 
     */
    public int getBrojSedistaDeca() {
        return brojSedistaDeca;
    }

    /**
     * Sets the value of the brojSedistaDeca property.
     * 
     */
    public void setBrojSedistaDeca(int value) {
        this.brojSedistaDeca = value;
    }

    /**
     * Gets the value of the iznajmljivacId property.
     * 
     */
    public long getIznajmljivacId() {
        return iznajmljivacId;
    }

    /**
     * Sets the value of the iznajmljivacId property.
     * 
     */
    public void setIznajmljivacId(long value) {
        this.iznajmljivacId = value;
    }

    /**
     * Gets the value of the vaziOd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVaziOd() {
        return vaziOd;
    }

    /**
     * Sets the value of the vaziOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVaziOd(XMLGregorianCalendar value) {
        this.vaziOd = value;
    }

    /**
     * Gets the value of the vaziDo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVaziDo() {
        return vaziDo;
    }

    /**
     * Sets the value of the vaziDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVaziDo(XMLGregorianCalendar value) {
        this.vaziDo = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

}
