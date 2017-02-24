package com.givehopeweb.repositories;

import com.givehopeweb.models.Charity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David on 2/21/17.
 */
public interface Charities extends CrudRepository <Charity, Integer> {

}
