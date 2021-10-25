import { getBinderNode, NumberModel } from './Models';
import { Required } from './Validators';
export class ValidationError extends Error {
    constructor(errors) {
        super([
            'There are validation errors in the form.',
            ...errors.map((e) => `${e.property} - ${e.validator.constructor.name}${e.message ? ': ' + e.message : ''}`)
        ].join('\n - '));
        this.errors = errors;
        this.name = this.constructor.name;
    }
}
export class ServerValidator {
    constructor(message) {
        this.message = message;
        this.validate = () => false;
    }
}
export async function runValidator(model, validator) {
    const value = getBinderNode(model).value;
    // If model is not required and value empty, do not run any validator. Except
    // always validate NumberModel, which has a mandatory builtin validator
    // to indicate NaN input.
    if (!getBinderNode(model).required && !new Required().validate(value) && !(model instanceof NumberModel)) {
        return [];
    }
    return (async () => validator.validate(value, getBinderNode(model).binder))().then((result) => {
        if (result === false) {
            return [{ property: getBinderNode(model).name, value, validator, message: validator.message }];
        }
        else if (result === true || (Array.isArray(result) && result.length === 0)) {
            return [];
        }
        else if (Array.isArray(result)) {
            return result.map((result2) => ({ message: validator.message, ...result2, value, validator }));
        }
        else {
            return [{ message: validator.message, ...result, value, validator }];
        }
    });
}
//# sourceMappingURL=Validation.js.map