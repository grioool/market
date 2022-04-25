package by.sam_solutions.grigorieva.olga.backend.domain.validation;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.internal.engine.messageinterpolation.util.InterpolationHelper;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Component
public class PatternValidator implements ConstraintValidator<CustomPattern, CharSequence>, ApplicationContextAware {

	private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());

	private Pattern pattern;
	private String escapedRegexp;

	private ApplicationContext applicationContext;

	@Override
	public void initialize(CustomPattern parameters) {
		try {
			String resolvedRegexp = applicationContext.getEnvironment().getProperty(parameters.patternKey());
			pattern = Pattern.compile(resolvedRegexp);
		} catch (PatternSyntaxException e) {
			throw LOG.getInvalidRegularExpressionException(e);
		}
		escapedRegexp = InterpolationHelper.escapeMessageParameter(parameters.patternKey());
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null) {
			return true;
		}

		if (constraintValidatorContext instanceof HibernateConstraintValidatorContext) {
			constraintValidatorContext
					.unwrap(HibernateConstraintValidatorContext.class)
					.addMessageParameter("regexp", escapedRegexp);
		}
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
