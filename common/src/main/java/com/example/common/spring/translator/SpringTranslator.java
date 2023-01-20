package com.example.common.spring.translator;

import com.example.common.translator.Translator;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpringTranslator implements Translator {

    private final MessageSource messageSource;

    public String translate(String message, Object... args) {
        return this.messageSource.getMessage(message, args, message, LocaleContextHolder.getLocale());
    }

}
