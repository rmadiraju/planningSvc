package com.avo.planning.domain;

import com.avo.planning.domain.enumeration.CampaignTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CampaignType.
 */
@Document(collection = "campaignType")
public class CampaignType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("type")
    private CampaignTypeEnum type;

    @Field("recurring")
    private Boolean recurring;

    @Field("day_of_month")
    private Integer dayOfMonth;

    @Field("day_of_week")
    private Integer dayOfWeek;

    @Field("month")
    private Integer month;

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

    public CampaignType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CampaignTypeEnum getType() {
        return type;
    }

    public CampaignType type(CampaignTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(CampaignTypeEnum type) {
        this.type = type;
    }

    public Boolean isRecurring() {
        return recurring;
    }

    public CampaignType recurring(Boolean recurring) {
        this.recurring = recurring;
        return this;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public CampaignType dayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public CampaignType dayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getMonth() {
        return month;
    }

    public CampaignType month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
        CampaignType campaignType = (CampaignType) o;
        if (campaignType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", recurring='" + isRecurring() + "'" +
            ", dayOfMonth=" + getDayOfMonth() +
            ", dayOfWeek=" + getDayOfWeek() +
            ", month=" + getMonth() +
            "}";
    }
}
