//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.10 at 05:16:36 PM MESZ 
//


package de.htwg.gib.egkterminal.model.medikationsplan;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ai">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;minLength value="1"/>
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="p">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="1"/>
 *             &lt;enumeration value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="b">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="1"/>
 *             &lt;enumeration value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="w">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;fractionDigits value="1"/>
 *             &lt;maxInclusive value="999"/>
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="h">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="10"/>
 *             &lt;maxInclusive value="299"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="c">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *             &lt;fractionDigits value="2"/>
 *             &lt;minInclusive value="0"/>
 *             &lt;maxExclusive value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="x">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="77"/>
 *             &lt;minLength value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Parameter {

    @XmlAttribute(name = "ai")
    protected String allergienUnvertraeglichkeiten;
    @XmlAttribute(name = "p")
    protected String schwanger;
    @XmlAttribute(name = "b")
    protected String stillend;
    @XmlAttribute(name = "w")
    protected BigDecimal gewicht;
    @XmlAttribute(name = "h")
    protected Integer groesse;
    @XmlAttribute(name = "c")
    protected BigDecimal kreatininwert;
    @XmlAttribute(name = "x")
    protected String parameterfreitext;

    /**
     * Gets the value of the allergienUnvertraeglichkeiten property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergienUnvertraeglichkeiten() {
        return allergienUnvertraeglichkeiten;
    }

    /**
     * Sets the value of the allergienUnvertraeglichkeiten property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergienUnvertraeglichkeiten(String value) {
        this.allergienUnvertraeglichkeiten = value;
    }

    /**
     * Gets the value of the schwanger property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchwanger() {
        return schwanger;
    }

    /**
     * Sets the value of the schwanger property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchwanger(String value) {
        this.schwanger = value;
    }

    /**
     * Gets the value of the stillend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStillend() {
        return stillend;
    }

    /**
     * Sets the value of the stillend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStillend(String value) {
        this.stillend = value;
    }

    /**
     * Gets the value of the gewicht property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGewicht() {
        return gewicht;
    }

    /**
     * Sets the value of the gewicht property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGewicht(BigDecimal value) {
        this.gewicht = value;
    }

    /**
     * Gets the value of the groesse property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGroesse() {
        return groesse;
    }

    /**
     * Sets the value of the groesse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGroesse(Integer value) {
        this.groesse = value;
    }

    /**
     * Gets the value of the kreatininwert property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKreatininwert() {
        return kreatininwert;
    }

    /**
     * Sets the value of the kreatininwert property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKreatininwert(BigDecimal value) {
        this.kreatininwert = value;
    }

    /**
     * Gets the value of the parameterfreitext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterfreitext() {
        return parameterfreitext;
    }

    /**
     * Sets the value of the parameterfreitext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterfreitext(String value) {
        this.parameterfreitext = value;
    }

}
