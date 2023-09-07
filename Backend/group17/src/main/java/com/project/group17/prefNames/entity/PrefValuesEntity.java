package com.project.group17.prefNames.entity;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.entity.PrefOptionsEntity;
import com.project.group17.user.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "pref_value")
public class PrefValuesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long prefValueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "pref_option_id", nullable = true)
    private PrefOptionsEntity prefOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pref_name_id", nullable = false)
    private PrefNamesEntity prefName;

    /**
     * Returns the ID of the preference value.
     *
     * @return Long - the ID of the preference value
     */
    public Long getPrefValueId() {
        return prefValueId;
    }

    /**
     * Sets the ID of the preference value.
     *
     * @param prefValueId - the ID of the preference value
     */
    public void setPrefValueId(Long prefValueId) {
        this.prefValueId = prefValueId;
    }

    /**
     * Returns the user associated with the preference value.
     *
     * @return User - the user associated with the preference value
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the preference value.
     *
     * @param user - the user associated with the preference value
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the preference option of the preference value.
     *
     * @return PrefOptionsEntity - the preference option of the preference value
     */
    public PrefOptionsEntity getPrefOption() {
        return prefOption;
    }

    /**
     * Sets the preference option of the preference value.
     *
     * @param prefOption - the preference option of the preference value
     */
    public void setPrefOption(PrefOptionsEntity prefOption) {
        this.prefOption = prefOption;
    }

    /**
     * Returns the preference name of the preference value.
     *
     * @return PrefNamesEntity - the preference name of the preference value
     */
    public PrefNamesEntity getPrefName() {
        return prefName;
    }

    /**
     * Sets the preference name of the preference value.
     *
     * @param prefName - the preference name of the preference value
     */
    public void setPrefName(PrefNamesEntity prefName) {
        this.prefName = prefName;
    }
}
