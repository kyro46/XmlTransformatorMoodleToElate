//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.28 at 10:41:44 AM MESZ 
//


package de.thorstenberger.taskmodel.complex.complextaskdef;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *       &lt;sequence>
 *         &lt;element name="config">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="time" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tries">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="tasksPerPage">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="kindnessExtensionTime" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="correctionMode" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="regular">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="correctOnlyProcessedTasks">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="numberOfTasks" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                           &lt;minInclusive value="1"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="multipleCorrectors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="numberOfCorrectors" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *                                           &lt;minInclusive value="2"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="isExercise" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="showHandlingHintsBeforeStart" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element ref="{http://complex.taskmodel.thorstenberger.de/complexTaskDef}category" maxOccurs="unbounded"/>
 *         &lt;element name="revisions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="revision" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="serialNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *                           &lt;attribute name="author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}long" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "config",
    "id",
    "title",
    "description",
    "startText",
    "showHandlingHintsBeforeStart",
    "category",
    "revisions"
})
@XmlRootElement(name = "complexTaskDef")
public class ComplexTaskDef {

    @XmlElement(required = true)
    protected ComplexTaskDef.Config config;
    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String description;
    protected String startText;
    @XmlElement(defaultValue = "true")
    protected Boolean showHandlingHintsBeforeStart;
    @XmlElement(required = true)
    protected List<Category> category;
    protected ComplexTaskDef.Revisions revisions;

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexTaskDef.Config }
     *     
     */
    public ComplexTaskDef.Config getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexTaskDef.Config }
     *     
     */
    public void setConfig(ComplexTaskDef.Config value) {
        this.config = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the startText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartText() {
        return startText;
    }

    /**
     * Sets the value of the startText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartText(String value) {
        this.startText = value;
    }

    /**
     * Gets the value of the showHandlingHintsBeforeStart property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowHandlingHintsBeforeStart() {
        return showHandlingHintsBeforeStart;
    }

    /**
     * Sets the value of the showHandlingHintsBeforeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowHandlingHintsBeforeStart(Boolean value) {
        this.showHandlingHintsBeforeStart = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Category }
     * 
     * 
     */
    public List<Category> getCategory() {
        if (category == null) {
            category = new ArrayList<Category>();
        }
        return this.category;
    }

    /**
     * Gets the value of the revisions property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexTaskDef.Revisions }
     *     
     */
    public ComplexTaskDef.Revisions getRevisions() {
        return revisions;
    }

    /**
     * Sets the value of the revisions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexTaskDef.Revisions }
     *     
     */
    public void setRevisions(ComplexTaskDef.Revisions value) {
        this.revisions = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="time" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tries">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="tasksPerPage">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="kindnessExtensionTime" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="correctionMode" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="regular">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="correctOnlyProcessedTasks">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="numberOfTasks" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                                 &lt;minInclusive value="1"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="multipleCorrectors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="numberOfCorrectors" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
     *                                 &lt;minInclusive value="2"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="isExercise" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Config {

        protected Integer time;
        @XmlElement(defaultValue = "1")
        protected int tries;
        @XmlElement(defaultValue = "2")
        protected int tasksPerPage;
        @XmlElement(defaultValue = "0")
        protected Integer kindnessExtensionTime;
        protected ComplexTaskDef.Config.CorrectionMode correctionMode;
        @XmlElement(defaultValue = "false")
        protected boolean isExercise;

        /**
         * Gets the value of the time property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTime() {
            return time;
        }

        /**
         * Sets the value of the time property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTime(Integer value) {
            this.time = value;
        }

        /**
         * Gets the value of the tries property.
         * 
         */
        public int getTries() {
            return tries;
        }

        /**
         * Sets the value of the tries property.
         * 
         */
        public void setTries(int value) {
            this.tries = value;
        }

        /**
         * Gets the value of the tasksPerPage property.
         * 
         */
        public int getTasksPerPage() {
            return tasksPerPage;
        }

        /**
         * Sets the value of the tasksPerPage property.
         * 
         */
        public void setTasksPerPage(int value) {
            this.tasksPerPage = value;
        }

        /**
         * Gets the value of the kindnessExtensionTime property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getKindnessExtensionTime() {
            return kindnessExtensionTime;
        }

        /**
         * Sets the value of the kindnessExtensionTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setKindnessExtensionTime(Integer value) {
            this.kindnessExtensionTime = value;
        }

        /**
         * Gets the value of the correctionMode property.
         * 
         * @return
         *     possible object is
         *     {@link ComplexTaskDef.Config.CorrectionMode }
         *     
         */
        public ComplexTaskDef.Config.CorrectionMode getCorrectionMode() {
            return correctionMode;
        }

        /**
         * Sets the value of the correctionMode property.
         * 
         * @param value
         *     allowed object is
         *     {@link ComplexTaskDef.Config.CorrectionMode }
         *     
         */
        public void setCorrectionMode(ComplexTaskDef.Config.CorrectionMode value) {
            this.correctionMode = value;
        }

        /**
         * Gets the value of the isExercise property.
         * 
         */
        public boolean isIsExercise() {
            return isExercise;
        }

        /**
         * Sets the value of the isExercise property.
         * 
         */
        public void setIsExercise(boolean value) {
            this.isExercise = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element name="regular">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="correctOnlyProcessedTasks">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="numberOfTasks" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *                       &lt;minInclusive value="1"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="multipleCorrectors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="numberOfCorrectors" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
         *                       &lt;minInclusive value="2"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "regular",
            "correctOnlyProcessedTasks",
            "multipleCorrectors"
        })
        public static class CorrectionMode {

            protected ComplexTaskDef.Config.CorrectionMode.Regular regular;
            protected ComplexTaskDef.Config.CorrectionMode.CorrectOnlyProcessedTasks correctOnlyProcessedTasks;
            protected ComplexTaskDef.Config.CorrectionMode.MultipleCorrectors multipleCorrectors;

            /**
             * Gets the value of the regular property.
             * 
             * @return
             *     possible object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.Regular }
             *     
             */
            public ComplexTaskDef.Config.CorrectionMode.Regular getRegular() {
                return regular;
            }

            /**
             * Sets the value of the regular property.
             * 
             * @param value
             *     allowed object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.Regular }
             *     
             */
            public void setRegular(ComplexTaskDef.Config.CorrectionMode.Regular value) {
                this.regular = value;
            }

            /**
             * Gets the value of the correctOnlyProcessedTasks property.
             * 
             * @return
             *     possible object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.CorrectOnlyProcessedTasks }
             *     
             */
            public ComplexTaskDef.Config.CorrectionMode.CorrectOnlyProcessedTasks getCorrectOnlyProcessedTasks() {
                return correctOnlyProcessedTasks;
            }

            /**
             * Sets the value of the correctOnlyProcessedTasks property.
             * 
             * @param value
             *     allowed object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.CorrectOnlyProcessedTasks }
             *     
             */
            public void setCorrectOnlyProcessedTasks(ComplexTaskDef.Config.CorrectionMode.CorrectOnlyProcessedTasks value) {
                this.correctOnlyProcessedTasks = value;
            }

            /**
             * Gets the value of the multipleCorrectors property.
             * 
             * @return
             *     possible object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.MultipleCorrectors }
             *     
             */
            public ComplexTaskDef.Config.CorrectionMode.MultipleCorrectors getMultipleCorrectors() {
                return multipleCorrectors;
            }

            /**
             * Sets the value of the multipleCorrectors property.
             * 
             * @param value
             *     allowed object is
             *     {@link ComplexTaskDef.Config.CorrectionMode.MultipleCorrectors }
             *     
             */
            public void setMultipleCorrectors(ComplexTaskDef.Config.CorrectionMode.MultipleCorrectors value) {
                this.multipleCorrectors = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="numberOfTasks" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
             *             &lt;minInclusive value="1"/>
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
            public static class CorrectOnlyProcessedTasks {

                @XmlAttribute(required = true)
                protected int numberOfTasks;

                /**
                 * Gets the value of the numberOfTasks property.
                 * 
                 */
                public int getNumberOfTasks() {
                    return numberOfTasks;
                }

                /**
                 * Sets the value of the numberOfTasks property.
                 * 
                 */
                public void setNumberOfTasks(int value) {
                    this.numberOfTasks = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="numberOfCorrectors" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
             *             &lt;minInclusive value="2"/>
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
            public static class MultipleCorrectors {

                @XmlAttribute(required = true)
                protected int numberOfCorrectors;

                /**
                 * Gets the value of the numberOfCorrectors property.
                 * 
                 */
                public int getNumberOfCorrectors() {
                    return numberOfCorrectors;
                }

                /**
                 * Sets the value of the numberOfCorrectors property.
                 * 
                 */
                public void setNumberOfCorrectors(int value) {
                    this.numberOfCorrectors = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Regular {

                @XmlAttribute
                protected String value;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="revision" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="serialNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
     *                 &lt;attribute name="author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}long" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "revision"
    })
    public static class Revisions {

        protected List<ComplexTaskDef.Revisions.Revision> revision;

        /**
         * Gets the value of the revision property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the revision property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRevision().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ComplexTaskDef.Revisions.Revision }
         * 
         * 
         */
        public List<ComplexTaskDef.Revisions.Revision> getRevision() {
            if (revision == null) {
                revision = new ArrayList<ComplexTaskDef.Revisions.Revision>();
            }
            return this.revision;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="serialNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
         *       &lt;attribute name="author" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}long" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Revision {

            @XmlAttribute(required = true)
            protected long serialNumber;
            @XmlAttribute(required = true)
            protected String author;
            @XmlAttribute
            protected Long date;

            /**
             * Gets the value of the serialNumber property.
             * 
             */
            public long getSerialNumber() {
                return serialNumber;
            }

            /**
             * Sets the value of the serialNumber property.
             * 
             */
            public void setSerialNumber(long value) {
                this.serialNumber = value;
            }

            /**
             * Gets the value of the author property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAuthor() {
                return author;
            }

            /**
             * Sets the value of the author property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAuthor(String value) {
                this.author = value;
            }

            /**
             * Gets the value of the date property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setDate(Long value) {
                this.date = value;
            }

        }

    }

}
