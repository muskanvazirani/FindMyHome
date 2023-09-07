package com.project.group17.prefNames.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * PrefNamesEntity represents the "pref_names" table in the database.
 * It contains information about each preference name and its associated options.
 */
@Entity
@Table(name = "pref_names")
public class PrefNamesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long prefId;

    private String name;

    private String type;

    private Boolean isRequired;

    // One-to-many relationship between PrefNamesEntity and PrefOptionsEntity
    @OneToMany(mappedBy = "prefName")
    private List<PrefOptionsEntity> options;

    public Boolean getIs_required() {
        return isRequired;
    }

    public void setIs_required(Boolean is_required) {
        this.isRequired = is_required;
    }

    public Long getPrefId() {
        return prefId;
    }

    public void setPrefId(Long prefId) {
        this.prefId = prefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the associated preference options.
     *
     * @return List of PrefOptionsEntity objects associated with this preference name.
     */
    @JsonManagedReference
    public List<PrefOptionsEntity> getOptions() {
        return options;
    }

    /**
     * Set the associated preference options.
     *
     * @param options List of PrefOptionsEntity objects to be associated with this preference name.
     */
    public void setOptions(List<PrefOptionsEntity> options) {
        this.options = options;
    }
}
