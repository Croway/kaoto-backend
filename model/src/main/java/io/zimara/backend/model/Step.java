package io.zimara.backend.model;

/**
 * 🐱class Step
 * Represents a step inside an integration
 */
public interface Step {
    /*
     * 🐱property ID: String
     *
     * Unique identifier for this step in our whole Zimara environment
     */
    String getID();    /*
     * 🐱property name: String
     *
     * This represents the first part of the camel connection string.
     * It may or may not be the same as the ID depending on the type of step.
     */

    String getName();

    /*
     * 🐱property type: String
     *
     * Type of step: transformation, connector
     */
    String getType();

    /*
     * 🐱property subtype: String
     *
     * If we need a subtype like kamelet connector, camel connector,...
     */
    String getSubType();

}
