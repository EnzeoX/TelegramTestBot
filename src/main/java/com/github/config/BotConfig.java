package com.github.config;

import java.io.IOException;
import java.util.Properties;

public class BotConfig {

    private String name;

    private String token;

    public BotConfig() {
        configureBot();
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    private void configureBot() {
        Properties properties = new Properties();
        try {
            properties.load(BotConfig.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        name = properties.getProperty("bot.name");
        token = properties.getProperty("bot.token");
    }
}
