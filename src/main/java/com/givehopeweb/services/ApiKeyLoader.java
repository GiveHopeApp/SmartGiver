package com.givehopeweb.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>The <code>ApiKeyLoader</code> class is a service that loads the secret PandaPay key from
 * the applications.properties file.</p>
 *
 * @author David Ryan Alviola
 * @since March 2017
 */
@Service
public class ApiKeyLoader {

    @Value("${pandaPay.apiKey}")
    private String pandaPayKey;

    /**
     * <p>Getter for the API key</p>
     *
     * @return String value representing the API key
     */
    public String getPandaPayKey() {
        return pandaPayKey;
    }
}