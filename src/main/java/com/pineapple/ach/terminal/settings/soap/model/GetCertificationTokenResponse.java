//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.11.28 at 05:32:52 PM IST
//

package com.pineapple.ach.terminal.settings.soap.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetCertificationTokenResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "getCertificationTokenResult"
})
@XmlRootElement(name = "GetCertificationTokenResponse")
public class GetCertificationTokenResponse {

    @XmlElement(name = "GetCertificationTokenResult")
    protected String getCertificationTokenResult;

    /**
     * Gets the value of the getCertificationTokenResult property.
     *
     * @return possible object is {@link String }
     * 
     */
    public String getGetCertificationTokenResult() {
        return getCertificationTokenResult;
    }

    /**
     * Sets the value of the getCertificationTokenResult property.
     *
     * @param value allowed object is {@link String }
     * 
     */
    public void setGetCertificationTokenResult(String value) {
        getCertificationTokenResult = value;
    }

}