package com.example.bot.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableConfigurationProperties(ApplicationConfig.class)
class ApplicationConfigTest {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Test
    void testConfigLoadedFromEnv() {
        assertNotNull(applicationConfig);

        assertEquals("tgBotService", applicationConfig.applicationName());

        assertNotNull(applicationConfig.server());
        assertEquals(8080, applicationConfig.server().port());

        assertNotNull(applicationConfig.logging());
        assertEquals("info", applicationConfig.logging().rootLevel());
        assertEquals("debug", applicationConfig.logging().botLevel());

        assertNotNull(applicationConfig.rabbitmq());
        assertEquals("localhost", applicationConfig.rabbitmq().host());
        assertEquals(5672, applicationConfig.rabbitmq().port());
        assertEquals("guest", applicationConfig.rabbitmq().username());
        assertEquals("guest", applicationConfig.rabbitmq().password());

        assertNotNull(applicationConfig.telegram());
        assertEquals("Weather_Big_Bot", applicationConfig.telegram().botName());
        assertNotNull(applicationConfig.telegram());
        assertTrue(applicationConfig.telegram().token().startsWith("7766662444:"));
    }
}
