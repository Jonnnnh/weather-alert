package com.example.bot.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@EnableConfigurationProperties(ApplicationProperties.class)
class ApplicationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Test
    void testApplicationPropertiesLoadedFromYML() {
        assertNotNull(applicationProperties);

        assertEquals("botService", applicationProperties.applicationName());

        assertNotNull(applicationProperties.telegram());
        assertEquals("Weather Bot", applicationProperties.telegram().botName());
        assertNotNull(applicationProperties.telegram().token());
        assertTrue(applicationProperties.telegram().token().startsWith("7766662444:"));

        assertNotNull(applicationProperties.rabbitmq());
        assertEquals("weatherRequestQueue", applicationProperties.rabbitmq().weatherQueue());
        assertEquals("userUpdateQueue", applicationProperties.rabbitmq().userQueue());
        assertEquals("weather-exchange.dlx", applicationProperties.rabbitmq().dlx());
        assertEquals("weather-routing-key", applicationProperties.rabbitmq().weatherKey());
        assertEquals("user-routing-key", applicationProperties.rabbitmq().userKey());
    }

    @Test
    void testRabbitMQConfigNotNull() {
        assertNotNull(applicationProperties.rabbitmq());
    }

    @Test
    void testTelegramConfigNotNull() {
        assertNotNull(applicationProperties.telegram());
    }

    @Test
    void testApplicationName() {
        assertEquals("botService", applicationProperties.applicationName());
    }

    @Test
    void testRabbitMQWeatherQueue() {
        assertEquals("weatherRequestQueue", applicationProperties.rabbitmq().weatherQueue());
    }

    @Test
    void testRabbitMQUserQueue() {
        assertEquals("userUpdateQueue", applicationProperties.rabbitmq().userQueue());
    }

    @Test
    void testRabbitMQDLX() {
        assertEquals("weather-exchange.dlx", applicationProperties.rabbitmq().dlx());
    }

    @Test
    void testRabbitMQWeatherKey() {
        assertEquals("weather-routing-key", applicationProperties.rabbitmq().weatherKey());
    }

    @Test
    void testRabbitMQUserKey() {
        assertEquals("user-routing-key", applicationProperties.rabbitmq().userKey());
    }

    @Test
    void testTelegramBotName() {
        assertEquals("Weather Bot", applicationProperties.telegram().botName());
    }

    @Test
    void testTelegramTokenFormat() {
        assertNotNull(applicationProperties.telegram().token());
        assertTrue(applicationProperties.telegram().token().startsWith("7766662444:"));
    }
}
