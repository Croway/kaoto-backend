package io.zimara.backend.model;

/**
 * 🐱class Parameter
 * Represents a parameter on a form.
 */
public interface Parameter<T> {
    /*
     * 🐱property label: String
     *
     * Human name for the view
     */
    String getLabel();

    /*
     * 🐱property id: String
     *
     * Identifier of the parameter
     */
    String getId();

    /*
     * 🐱property type: String
     *
     * Type of parameter: text, integer, float, boolean,...
     */
    String getType();

    /*
     * 🐱property default: String
     *
     * Default value, if there is any
     */
    T getDefault();

    /*
     * 🐱property description: String
     *
     * Helping text describing the parameter
     */
    String getDescription();


    /*
     * 🐱property value: String
     *
     * Actual value of this parameter. Used when describing a configured element.
     */
    void setValue(T value);

    T getValue();
}
