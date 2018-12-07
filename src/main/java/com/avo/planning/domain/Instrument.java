package com.avo.planning.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Instrument.
 */
@Document(collection = "instrument")
public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @NotNull
    @Field("start_date")
    private LocalDate startDate;

    @NotNull
    @Field("end_date")
    private LocalDate endDate;

    @NotNull
    @Field("create_date")
    private LocalDate createDate;

    @Field("last_modified")
    private LocalDate lastModified;

    @DBRef
    @Field("sourceTemplate")
    private Template sourceTemplate;

    @DBRef
    @Field("attribute")
    private Set<Attribute> attributes = new HashSet<>();

    @DBRef
    @Field("campaign")
    @JsonIgnoreProperties("instruments")
    private Campaign campaign;

    @DBRef
    @Field("instrumentType")
    @JsonIgnoreProperties("")
    private InstrumentType instrumentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Instrument name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Instrument description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Instrument startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Instrument endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Instrument createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public Instrument lastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public Template getSourceTemplate() {
        return sourceTemplate;
    }

    public Instrument sourceTemplate(Template template) {
        this.sourceTemplate = template;
        return this;
    }

    public void setSourceTemplate(Template template) {
        this.sourceTemplate = template;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public Instrument attributes(Set<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    /*
    public Instrument addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.setInstrument(this);
        return this;
    }

    public Instrument removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.setInstrument(null);
        return this;
    }
*/
    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public Instrument campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public Instrument instrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
        return this;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instrument instrument = (Instrument) o;
        if (instrument.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instrument.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Instrument{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
