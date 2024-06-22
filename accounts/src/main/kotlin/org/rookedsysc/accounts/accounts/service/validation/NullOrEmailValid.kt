package org.rookedsysc.accounts.accounts.service.validation

import jakarta.validation.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [NullOrEmailValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NullOrEmailValid(
        val message: String = "Invalid email",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class NullOrEmailValidator : ConstraintValidator<NullOrEmailValid, String?> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator

        val emailCheck = EmailCheck(value)
        val constraintViolations = validator.validate(emailCheck)

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

data class EmailCheck(
    @field:NotBlank(message = "Email cannot be blank.")
    @field:Email(message = "Email addr should be a valid value.")
    val email: String?
)
