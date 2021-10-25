import { noChange, nothing } from 'lit';
import { directive, Directive, PartType } from 'lit/directive.js';
import { _fromString, getBinderNode } from './Models';
export class AbstractFieldStrategy {
    constructor(element) {
        this.element = element;
        this.validate = async () => [];
    }
    get value() {
        return this.element.value;
    }
    set value(value) {
        this.element.value = value;
    }
    set errorMessage(_) { } // eslint-disable-line @typescript-eslint/no-empty-function
    setAttribute(key, val) {
        if (val) {
            this.element.setAttribute(key, '');
        }
        else {
            this.element.removeAttribute(key);
        }
    }
}
export class VaadinFieldStrategy extends AbstractFieldStrategy {
    set required(value) {
        this.element.required = value;
    }
    set invalid(value) {
        this.element.invalid = value;
    }
    set errorMessage(value) {
        this.element.errorMessage = value;
    }
}
export class GenericFieldStrategy extends AbstractFieldStrategy {
    set required(value) {
        this.setAttribute('required', value);
    }
    set invalid(value) {
        this.setAttribute('invalid', value);
    }
}
export class CheckedFieldStrategy extends GenericFieldStrategy {
    set value(val) {
        this.element.checked = /^(true|on)$/i.test(String(val));
    }
    get value() {
        return this.element.checked;
    }
}
export class ComboBoxFieldStrategy extends VaadinFieldStrategy {
    get value() {
        const { selectedItem } = this.element;
        return selectedItem === null ? undefined : selectedItem;
    }
    set value(val) {
        this.element.selectedItem = val === undefined ? null : val;
    }
}
export class SelectedFieldStrategy extends GenericFieldStrategy {
    set value(val) {
        this.element.selected = val;
    }
    get value() {
        return this.element.selected;
    }
}
export function getDefaultFieldStrategy(elm) {
    switch (elm.localName) {
        case 'vaadin-checkbox':
        case 'vaadin-radio-button':
            return new CheckedFieldStrategy(elm);
        case 'vaadin-combo-box':
            return new ComboBoxFieldStrategy(elm);
        case 'vaadin-list-box':
            return new SelectedFieldStrategy(elm);
        case 'vaadin-rich-text-editor':
            return new GenericFieldStrategy(elm);
        default:
            if (elm.localName === 'input' && /^(checkbox|radio)$/.test(elm.type)) {
                return new CheckedFieldStrategy(elm);
            }
            return elm.constructor.version ? new VaadinFieldStrategy(elm) : new GenericFieldStrategy(elm);
    }
}
/**
 * Binds a form field component into a model.
 *
 * Example usage:
 *
 * ```
 * <vaadin-text-field ...="${field(model.name)}">
 * </vaadin-text-field>
 * ```
 */
export const field = directive(class extends Directive {
    constructor(partInfo) {
        super(partInfo);
        if (partInfo.type !== PartType.PROPERTY && partInfo.type !== PartType.ELEMENT) {
            throw new Error('Use as "<element {field(...)}" or <element ...={field(...)}"');
        }
    }
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    render(model, effect) {
        return nothing;
    }
    update(part, [model, effect]) {
        const element = part.element;
        const binderNode = getBinderNode(model);
        const fieldStrategy = binderNode.binder.getFieldStrategy(element);
        const convertFieldValue = (fieldValue) => {
            const fromString = model[_fromString];
            return typeof fieldValue === 'string' && fromString ? fromString(fieldValue) : fieldValue;
        };
        if (!this.fieldState) {
            this.fieldState = {
                name: '',
                value: '',
                required: false,
                invalid: false,
                errorMessage: '',
                strategy: fieldStrategy
            };
            const { fieldState } = this;
            const updateValueFromElement = () => {
                fieldState.value = fieldState.strategy.value;
                binderNode.value = convertFieldValue(fieldState.value);
                if (effect !== undefined) {
                    effect.call(element, element);
                }
            };
            element.oninput = () => {
                updateValueFromElement();
            };
            const changeBlurHandler = () => {
                updateValueFromElement();
                binderNode.visited = true;
            };
            element.onblur = changeBlurHandler;
            element.onchange = changeBlurHandler;
            element.checkValidity = () => !fieldState.invalid;
        }
        const { fieldState } = this;
        const { name } = binderNode;
        if (name !== fieldState.name) {
            fieldState.name = name;
            element.setAttribute('name', name);
        }
        const { value } = binderNode;
        const valueFromField = convertFieldValue(fieldState.value);
        if (value !== valueFromField && !(Number.isNaN(value) && Number.isNaN(valueFromField))) {
            fieldState.value = value;
            fieldState.strategy.value = value;
        }
        const { required } = binderNode;
        if (required !== fieldState.required) {
            fieldState.required = required;
            fieldState.strategy.required = required;
        }
        const firstError = binderNode.ownErrors ? binderNode.ownErrors[0] : undefined;
        const errorMessage = (firstError && firstError.message) || '';
        if (errorMessage !== fieldState.errorMessage) {
            fieldState.errorMessage = errorMessage;
            fieldState.strategy.errorMessage = errorMessage;
        }
        const { invalid } = binderNode;
        if (invalid !== fieldState.invalid) {
            fieldState.invalid = invalid;
            fieldState.strategy.invalid = invalid;
        }
        return noChange;
    }
});
//# sourceMappingURL=Field.js.map