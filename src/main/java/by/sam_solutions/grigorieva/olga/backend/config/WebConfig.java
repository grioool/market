package by.sam_solutions.grigorieva.olga.backend.config;

import by.sam_solutions.grigorieva.olga.backend.converter.to.dto.*;
import by.sam_solutions.grigorieva.olga.backend.converter.to.entity.*;
import by.sam_solutions.grigorieva.olga.backend.converter.to.report.ReportWBDtoToReportDtoConverter;
import by.sam_solutions.grigorieva.olga.backend.service.profit.ProfitService;
import by.sam_solutions.grigorieva.olga.backend.service.supply.SupplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value ("${username.mail.sender}")
    private String username;

    @Value ("${password.mail.sender}")
    private String password;

    private final ConversionService mConversionService;

    private final SupplyService supplyService;

    private final ProfitService profitService;

    @Autowired
    public WebConfig(@Lazy ConversionService conversionService, SupplyService supplyService, ProfitService profitService) {
        mConversionService = conversionService;
        this.supplyService = supplyService;
        this.profitService = profitService;
    }

    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CountryToDtoConverter());
        registry.addConverter(new PurchaseToDtoConverter());
        registry.addConverter(new ReportToDtoConverter(mConversionService));
        registry.addConverter(new RoleToDtoConverter());
        registry.addConverter(new StorageToDtoConverter(mConversionService));
        registry.addConverter(new SupplyProductToDtoConverter(mConversionService));
        registry.addConverter(new TownToDtoConverter());
        registry.addConverter(new UserToDtoConverter());
        registry.addConverter(new CountryToEntityConverter());
        registry.addConverter(new PurchaseToEntityConverter());
        registry.addConverter(new ReportToEntityConverter(mConversionService));
        registry.addConverter(new RoleToEntityConverter());
        registry.addConverter(new StorageToEntityConverter(mConversionService));
        registry.addConverter(new SupplyTableRowToSupplyProductConverter(mConversionService));
        registry.addConverter(new TownToEntityConverter());
        registry.addConverter(new UserToEntityConverter());
        registry.addConverter(new ReportWBDtoToReportDtoConverter(mConversionService, supplyService, profitService));
        registry.addConverter(new ExceptionToDtoConverter());
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        slr.setSupportedLocales(List.of(Locale.ENGLISH, new Locale("ru")));
        return slr;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
