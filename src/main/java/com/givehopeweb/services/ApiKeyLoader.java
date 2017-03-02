package com.givehopeweb.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by David on 2/28/17.
 */
@Service
public class ApiKeyLoader {

    @Value("${pandaPay.apiKey}")
    private String pandaPayKey;

    public String getPandaPayKey() {
        return pandaPayKey;
    }
}
