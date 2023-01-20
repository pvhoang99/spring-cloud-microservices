package com.example.common.translator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class GlobalTranslator {

    private static Translator globalTranslator;

    private final Translator translator;

    public GlobalTranslator(Translator translator) {
        this.translator = translator;
    }

    @PostConstruct
    public void init() {
        GlobalTranslator.globalTranslator = this.translator;
    }

    @PreDestroy
    public void preDestroy() {
        GlobalTranslator.globalTranslator = null;
    }

    public static String translate(String message, Object... args) {
        return globalTranslator.translate(message, args);
    }

}
