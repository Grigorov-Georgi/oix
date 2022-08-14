package bg.softuni.oix.config;

import bg.softuni.oix.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private LocaleChangeInterceptor localeChangeInterceptor;
    private LogInterceptor logInterceptor;

    public WebConfig(LocaleChangeInterceptor localeChangeInterceptor, LogInterceptor logInterceptor1) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.logInterceptor = logInterceptor1;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/offers");
    }
}
