package com.avo.planning.domain;

import com.avo.planning.domain.enumeration.InstrumentTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InstrumentType.
 */
@Document(collection = "instrumentType")
public class InstrumentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;


    @NotNull
    @Field("type")
    private InstrumentTypeEnum type;

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

    public InstrumentType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstrumentTypeEnum getType() {
        return type;
    }

    public InstrumentType type(InstrumentTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(InstrumentTypeEnum type) {
        this.type = type;
    }

    public Boolean isRecurring() {
        return recurring;
    }

    public InstrumentType recurring(Boolean recurring) {
        this.recurring = recurring;
        return this;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public InstrumentType dayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public InstrumentType dayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getMonth() {
        return month;
    }

    public InstrumentType month(Integer month) {
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
        InstrumentType instrumentType = (InstrumentType) o;
        if (instrumentType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instrumentType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstrumentType{" +
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
