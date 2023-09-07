package com.project.group17.prefNames.mapper;

import com.project.group17.prefNames.entity.PrefNamesEntity;
import com.project.group17.prefNames.entity.PrefOptionsEntity;
import com.project.group17.prefNames.entity.PrefValuesEntity;
import com.project.group17.prefNames.entity.PrefValueSaveReq;
import com.project.group17.user.entity.User;
import org.springframework.util.ObjectUtils;

public class PrefValuesMapper {

    /**
     * Maps a PrefValueSaveReq object to a PrefValuesEntity object.
     *
     * @param prefValueSaveReq - a PrefValueSaveReq object
     * @param userId - the ID of the user associated with the preference value
     * @return PrefValuesEntity - a PrefValuesEntity object
     */
    public static PrefValuesEntity saveReqToEntityMapper(PrefValueSaveReq prefValueSaveReq, int userId) {
        PrefValuesEntity prefValuesEntity = new PrefValuesEntity();
        User user = new User();
        user.setId(userId);
        prefValuesEntity.setUser(user);

        PrefNamesEntity prefNames = new PrefNamesEntity();
        prefNames.setPrefId(prefValueSaveReq.getPrefNameId());
        prefValuesEntity.setPrefName(prefNames);

        if (prefValueSaveReq.getPrefOptionId() != null) {
            PrefOptionsEntity prefOptions = new PrefOptionsEntity();
            prefOptions.setPrefId(prefValueSaveReq.getPrefOptionId());
            prefValuesEntity.setPrefOption(prefOptions);
        }

        return prefValuesEntity;
    }

    /**
     * Maps a PrefValuesEntity object to a PrefValueSaveReq object.
     *
     * @param prefValuesEntity - a PrefValuesEntity object
     * @return PrefValueSaveReq - a PrefValueSaveReq object
     */
    public static PrefValueSaveReq entityToGetPrefCRes(PrefValuesEntity prefValuesEntity) {
        PrefValueSaveReq prefValueSaveReq = new PrefValueSaveReq();
        prefValueSaveReq.setPrefNameId(prefValuesEntity.getPrefName().getPrefId());

        if (ObjectUtils.isEmpty(prefValuesEntity.getPrefOption()) || prefValuesEntity.getPrefOption().getPrefId() == null) {
            prefValueSaveReq.setPrefOptionId(null);
        } else {
            prefValueSaveReq.setPrefOptionId(prefValuesEntity.getPrefOption().getPrefId());
        }

        return prefValueSaveReq;
    }
}
