package org.utopiavip.easycms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CmsProperties.class)
public class CmsAutoConfigure {

    @Autowired
    private CmsProperties cmsProperties;
}
