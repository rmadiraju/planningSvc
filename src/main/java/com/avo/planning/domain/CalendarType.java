package com.avo.planning.domain;

import com.avo.planning.domain.enumeration.CalendarScopeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CalendarType.
 */
@Document(collection = "calendarType")
public class CalendarType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("scope")
    private CalendarScopeEnum scope;

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

    public CalendarType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CalendarScopeEnum getScope() {
        return scope;
    }

    public CalendarType scope(CalendarScopeEnum scope) {
        this.scope = scope;
        return this;
    }

    public void setScope(CalendarScopeEnum scope) {
        this.scope = scope;
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
        CalendarType calendarType = (CalendarType) o;
        if (calendarType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendarType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CalendarType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", scope='" + getScope() + "'" +
            "}";
    }
}
