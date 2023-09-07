package com.project.group17.prefNames.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.group17.prefNames.entity.PrefNamesEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "pref_options")
public class PrefOptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long prefId;

    @ManyToOne()
    @JoinColumn(name = "pref_name_id", nullable = false)
    private PrefNamesEntity prefName;

    @Column(nullable = false)
    private String option_name;

    /**
     * Returns the ID of the preference option.
     *
     * @return Long - the ID of the preference option
     */
    public Long getPrefId() {
        return prefId;
    }

    /**
     * Sets the ID of the preference option.
     *
     * @param prefId - the ID of the preference option
     */
    public void setPrefId(Long prefId) {
        this.prefId = prefId;
    }

    /**
     * Returns the preference name of the preference option.
     *
     * @return PrefNamesEntity - the preference name of the preference option
     */
    @JsonBackReference
    public PrefNamesEntity getPrefName() {
        return prefName;
    }

    /**
     * Sets the preference name of the preference option.
     *
     * @param prefName - the preference name of the preference option
     */
    public void setPrefName(PrefNamesEntity prefName) {
        this.prefName = prefName;
    }

    /**
     * Returns the name of the preference option.
     *
     * @return String - the name of the preference option
     */
    public String getOption() {
        return option_name;
    }

    /**
     * Sets the name of the preference option.
     *
     * @param option - the name of the preference option
     */
    public void setOption(String option) {
        this.option_name = option;
    }
}
