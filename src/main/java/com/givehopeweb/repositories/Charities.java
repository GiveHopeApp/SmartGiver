package com.givehopeweb.repositories;

import com.givehopeweb.models.Charity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David on 2/21/17.
 */
public interface Charities extends CrudRepository <Charity, Integer> {

        @Query("select c from Charity c where c.save_by_user_id = ?1")
        List<Charity> findUserFavorites (int userId);

}
