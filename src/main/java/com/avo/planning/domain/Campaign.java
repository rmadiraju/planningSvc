package com.avo.planning.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Campaign.
 */
@Document(collection = "campaign")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("recurring")
    private Boolean recurring;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field("start_date")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field("end_date")
    private LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field("create_date")
    private LocalDate createDate;

    @Field("last_modified")
    private LocalDate lastModified;

    @DBRef
    @Field("sourceTemplate")
    private Template sourceTemplate;

    @NotNull
    @Field("is_active")
    private Boolean isActive;

    @NotNull
    @Field("is_template")
    private Boolean isTemplate;

    @DBRef
    @Field("attribute")
    private Set<Attribute> attributes = new HashSet<>();

    @Field("instrument")
    private Set<Instrument> instruments = new HashSet<>();

    @DBRef
    @Field("campaignType")
    @JsonIgnoreProperties("")
    private CampaignType campaignType;


    @Field("calendar")
    @JsonIgnoreProperties("campaigns")
    private String calendar;



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

    public Campaign name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Campaign description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Campaign startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Campaign endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Campaign createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public Campaign lastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public Template getSourceTemplate() {
        return sourceTemplate;
    }

    public Campaign sourceTemplate(Template template) {
        this.sourceTemplate = template;
        return this;
    }

    public void setSourceTemplate(Template template) {
        this.sourceTemplate = template;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public Campaign attributes(Set<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    /*
    public Campaign addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.setCampaign(this);
        return this;
    }

    public Campaign removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.setCampaign(null);
        return this;
    }
    */

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public Campaign instruments(Set<Instrument> instruments) {
        this.instruments = instruments;
        return this;
    }

    public Campaign addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
        instrument.setCampaign(this);
        return this;
    }

    public Campaign removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
        instrument.setCampaign(null);
        return this;
    }

    public void setInstruments(Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public Campaign campaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
        return this;
    }

    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
    }

    public String getCalendar() {
        return calendar;
    }

    public Campaign calendar(String calendar) {
        this.calendar = calendar;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getTemplate() {
        return isTemplate;
    }

    public void setTemplate(Boolean template) {
        isTemplate = template;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
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
        Campaign campaign = (Campaign) o;
        if (campaign.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaign.getId());
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Campaign{" +
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
