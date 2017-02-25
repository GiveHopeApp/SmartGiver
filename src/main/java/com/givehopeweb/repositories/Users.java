package com.givehopeweb.repositories;

import com.givehopeweb.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by David on 2/21/17.
 */
public interface Users extends CrudRepository<User, Integer>{

    User findByUsername (String username);

}
