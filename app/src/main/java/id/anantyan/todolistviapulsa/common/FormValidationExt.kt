package id.anantyan.todolistviapulsa.common

import android.content.Context
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import id.anantyan.todolistviapulsa.R
import io.github.anderscheow.validator.Validation
import io.github.anderscheow.validator.Validator
import io.github.anderscheow.validator.constant.Mode
import io.github.anderscheow.validator.rules.common.notEmpty
import io.github.anderscheow.validator.rules.common.notNull
import io.github.anderscheow.validator.validation
import io.github.anderscheow.validator.validator

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
private fun TextInputLayout.generalValid(): Validation {
    return validation(this) {
        rules {
            +notNull(R.string.txt_not_null)
            +notEmpty(R.string.txt_not_empty)
        }
    }
}

enum class ValidationType {
    GENERAL
}

fun Context.onValidation(
    vararg validationParams: Pair<TextInputLayout, ValidationType>,
    onSuccess: () -> Unit
) {
    val validations = mutableListOf<Validation>()
    validationParams.forEach { (textInputLayout, validationType) ->
        val validation = when (validationType) {
            ValidationType.GENERAL -> textInputLayout.generalValid()
        }
        validations.add(validation)
    }

    validator(this) {
        mode = Mode.SINGLE
        listener = object : Validator.OnValidateListener {
            override fun onValidateFailed(errors: List<String>) {}

            override fun onValidateSuccess(values: List<String>) {
                onSuccess.invoke()
            }
        }
        validate(*validations.toTypedArray())
    }
}