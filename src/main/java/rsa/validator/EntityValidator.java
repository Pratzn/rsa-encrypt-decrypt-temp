package rsa.validator;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import rsa.entity.Entity;

public class EntityValidator implements Validator{
	@Override
	  public boolean supports(Class<?> clazz) {
	      return Entity.class == clazz;
	  }

	  @Override
	  public void validate(Object target, Errors errors) {
	      
	      ValidationUtils.rejectIfEmpty(errors, "price", "price.empty",
	              "Price cannot be empty");
	      Entity entity = (Entity) target;
	      if (entity.getPrice() != null &&
	              entity.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
	          errors.rejectValue("price", "price.invalid", "Price must be greater than 0");
	      }
	  }
}
