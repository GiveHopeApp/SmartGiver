package com.givehopeweb.repositories;

import com.givehopeweb.models.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David on 2/21/17.
 */
public interface Donations extends CrudRepository <Donation, Integer> {

//        @Query("select d from Donation d where d.user_id like ?1")
//        List<Donation> findByUserId (int userId);

}
