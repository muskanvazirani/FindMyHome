package com.project.group17.prefNames.entity;

public class PrefValueSaveReq {
    private Long prefNameId;

    private Long prefOptionId;

    /**
     * Returns the ID of the preference name associated with the preference value.
     *
     * @return Long - the ID of the preference name associated with the preference value
     */
    public Long getPrefNameId() {
        return prefNameId;
    }

    /**
     * Sets the ID of the preference name associated with the preference value.
     *
     * @param prefNameId - the ID of the preference name associated with the preference value
     */
    public void setPrefNameId(Long prefNameId) {
        this.prefNameId = prefNameId;
    }

    /**
     * Returns the ID of the preference option associated with the preference value.
     *
     * @return Long - the ID of the preference option associated with the preference value
     */
    public Long getPrefOptionId() {
        return prefOptionId;
    }

    /**
     * Sets the ID of the preference option associated with the preference value.
     *
     * @param prefOptionId - the ID of the preference option associated with the preference value
     */
    public void setPrefOptionId(Long prefOptionId) {
        this.prefOptionId = prefOptionId;
    }

    @Override
    public String toString() {
        return "PrefValueSaveReq{" +
                "prefNameId=" + prefNameId +
                ", prefOptionId=" + prefOptionId +
                '}';
    }
}
