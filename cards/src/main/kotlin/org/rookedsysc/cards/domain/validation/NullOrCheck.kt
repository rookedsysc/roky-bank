package org.rookedsysc.cards.domain.validation

import jakarta.validation.*
import jakarta.validation.constraints.*
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [GenericValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class NullOrCheck(
        val message: String = "Invalid value",
        val type: ValidationType,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)

class GenericValidator : ConstraintValidator<NullOrCheck, Any?> {
    private lateinit var type: ValidationType

    override fun initialize(constraintAnnotation: NullOrCheck) {
        this.type = constraintAnnotation.type
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator

        val constraintViolations = when (type) {
            ValidationType.EMAIL -> validator.validate(EmailCheck(value as String))
            ValidationType.NAME -> validator.validate(NameCheck(value as String))
            ValidationType.MOBILE -> validator.validate(MobileCheck(value as String))
            ValidationType.POSITIVE -> validator.validate(PositiveCheck(value as Int))
            ValidationType.POSITIVE_OR_ZERO -> validator.validate(value as Int)
        }

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

data class MobileCheck(
        @field:Pattern(regexp = "(^$|[0-9]{10})", message = "Password must contain at least one digit, one lowercase, one uppercase, and 8 characters.")
        @field:Size(min = 10, max = 10, message = "Mobile number must be 10 digits.")
        val mobileNumber: String?
)


data class EmailCheck(
        @field:NotBlank(message = "Email cannot be blank.")
        @field:Email(message = "Email addr should be a valid value.")
        val email: String?
)

data class NameCheck(
        @field:NotBlank(message = "Name cannot be blank.")
        @field:Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
        val name: String?
)

data class PositiveCheck(
        @field:PositiveOrZero(message = "Amount used can not be negative")
        val amount: Int?
)

data class PositiveOrZeroCheck(
        @field:PositiveOrZero(message = "Amount used can not be negative")
        val amount: Int?
)

data class CardCheck(
        val card: String?
)

enum class ValidationType {
    EMAIL,
    NAME,
    MOBILE,
    POSITIVE,
    POSITIVE_OR_ZERO,
}