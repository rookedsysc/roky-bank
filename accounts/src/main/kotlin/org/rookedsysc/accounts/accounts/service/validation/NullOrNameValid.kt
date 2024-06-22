package org.rookedsysc.accounts.accounts.service.validation

import jakarta.validation.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [NullOrNameValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NullOrNameValid(
        val message: String = "Invalid name",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class NullOrNameValidator : ConstraintValidator<NullOrNameValid, String?> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator

        val nameCheck = NameCheck(value)
        val constraintViolations = validator.validate(nameCheck)

        if (constraintViolations.isNotEmpty()) {
            for (violation in constraintViolations) {
                context.disableDefaultConstraintViolation()
                context.buildConstraintViolationWithTemplate(violation.message)
                        .addConstraintViolation()
            }
            return false
        }
        return true
    }
}

data class NameCheck(
        @field:NotBlank(message = "Name cannot be blank.")
        @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
        val name: String?
)
