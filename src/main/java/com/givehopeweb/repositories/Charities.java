package com.givehopeweb.repositories;

import com.givehopeweb.models.Charity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David on 2/21/17.
 */
public interface Charities extends CrudRepository <Charity, Integer> {

        @Query ("select c from Charity c where c.user.id = ?1")
        List<Charity> findUserFavorites (int userId);

        List<Charity>
        findByCharityNameContainingOrCategoryContainingOrStateContainingOrCityContainingOrDescriptionContaining
                (String name, String category, String state, String city, String keyword);

        List<Charity> findByCategoryContaining (String catgory);

        Charity findByEin (String ein);
}
