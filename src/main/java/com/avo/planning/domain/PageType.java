package com.avo.planning.domain;

import com.avo.planning.domain.enumeration.PageTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A PageType.
 */
@Document(collection = "pageType")
public class PageType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field
    private String icon;

    @NotNull
    @Field("type")
    private PageTypeEnum type;


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

    public PageType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageTypeEnum getType() {
        return type;
    }

    public PageType type(PageTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(PageTypeEnum type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageType pageType = (PageType) o;
        if (pageType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pageType.getId());
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PageType.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("name='" + name + "'")
            .add("icon='" + icon + "'")
            .add("type=" + type)
            .toString();
    }
}
