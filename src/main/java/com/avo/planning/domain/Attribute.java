package com.avo.planning.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.avo.planning.domain.enumeration.AttributeEntityType;
import com.avo.planning.domain.enumeration.AttributeTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * A Attribute.
 */
@Document(collection = "attribute")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Field("name")
    private String name;

    @Field("value")
    private String value;

    @Field("type")
    private AttributeTypeEnum type;

    @Field("entity_type")
    private AttributeEntityType entityType;

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

    public Attribute name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public Attribute value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeTypeEnum getType() {
        return type;
    }

    public Attribute type(AttributeTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(AttributeTypeEnum type) {
        this.type = type;
    }

    public AttributeEntityType getEntityType() {
        return entityType;
    }

    public Attribute entityType(AttributeEntityType entityType) {
        this.entityType = entityType;
        return this;
    }

    public void setEntityType(AttributeEntityType entityType) {
        this.entityType = entityType;
    }

    /*
    public Campaign getCampaign() {
        return campaign;
    }

    public Attribute campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Attribute calendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Attribute instrument(Instrument instrument) {
        this.instrument = instrument;
        return this;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    */
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        if (attribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attribute{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", type='" + getType() + "'" +
            ", entityType='" + getEntityType() + "'" +
            "}";
    }
}
