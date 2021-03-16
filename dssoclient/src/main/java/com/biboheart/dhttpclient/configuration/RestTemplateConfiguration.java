package com.biboheart.dhttpclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    /*@Autowired
    private RestTemplateBuilder builder;*/

    @Bean
    public RestTemplate restTemplate(OAuth2ProtectedResourceDetails resource) {
        /*StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return builder.additionalMessageConverters(stringHttpMessageConverter).build();*/
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource);

        /*AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
        authCodeProvider.setStateMandatory(false);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(Collections.singletonList(authCodeProvider));
        template.setAccessTokenProvider(provider);*/
        return template;
    }
}
