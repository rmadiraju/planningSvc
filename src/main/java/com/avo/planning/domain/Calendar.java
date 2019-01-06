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
import java.util.*;

/**
 * A Calendar.
 */
@Document(collection = "calendar")
public class Calendar implements Serializable {

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
    @Field("create_date")
    private LocalDate createDate;

    @Field("last_modified")
    private LocalDate lastModified;

    @Field("icon")
    private String icon;

    @Field("colour")
    private String colour;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field("end_date")
    private LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field("start_date")
    private LocalDate startDate;

    @Field("active")
    private Boolean active;

    @DBRef
    @Field("sourceTemplate")
    private Template sourceTemplate;

    @DBRef
    @Field("attribute")
    private Set<Attribute> attributes = new HashSet<>();

    @DBRef
    @Field("calendarType")
    @JsonIgnoreProperties("")
    private CalendarType calendarType;

    // StandAlone Campaign List, populated by Calendar service up on request
    private Set<Campaign> campaigns = null;

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

    public Calendar name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Calendar description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Calendar createDate(LocalDate createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public Calendar lastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public Template getSourceTemplate() {
        return sourceTemplate;
    }

    public Calendar sourceTemplate(Template template) {
        this.sourceTemplate = template;
        return this;
    }

    public void setSourceTemplate(Template template) {
        this.sourceTemplate = template;
    }


    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public Calendar attributes(Set<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    /*
    public Calendar addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.setCalendar(this);
        return this;
    }

    public Calendar removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.setCalendar(null);
        return this;
    }
    */

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public CalendarType getCalendarType() {
        return calendarType;
    }

    public Calendar calendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
        return this;
    }

    public Set<Campaign> getCampaigns() {
        if (campaigns == null)
            campaigns = new HashSet<>();
        return campaigns;
    }


    public void setCalendarType(CalendarType calendarType) {
        this.calendarType = calendarType;
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
        Calendar calendar = (Calendar) o;
        if (calendar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendar.getId());
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }



    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Calendar.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("name='" + name + "'")
            .add("description='" + description + "'")
            .add("createDate=" + createDate)
            .add("lastModified=" + lastModified)
            .add("icon='" + icon + "'")
            .add("colour='" + colour + "'")
            .add("startDate=" + startDate)
            .add("endDate=" + endDate)
            .add("active=" + active)
            .add("sourceTemplate=" + sourceTemplate)
            .add("attributes=" + attributes)
            .add("calendarType=" + calendarType)
            .add("campaigns=" + campaigns)
            .toString();
    }
}
