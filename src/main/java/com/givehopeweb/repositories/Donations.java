package com.givehopeweb.repositories;

import com.givehopeweb.models.Donation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David on 2/21/17.
 */
public interface Donations extends CrudRepository <Donation, Integer> {

}
