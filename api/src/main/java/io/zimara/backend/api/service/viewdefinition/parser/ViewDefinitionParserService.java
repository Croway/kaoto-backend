package io.zimara.backend.api.service.viewdefinition.parser;

import io.zimara.backend.model.step.Step;
import io.zimara.backend.model.view.ViewDefinition;

import java.util.List;
/**
 * 🐱class ViewDefinitionParserService
 * 🐱relationship dependsOn ViewDefinition
 *
 * Generic interface for all viewDefinition parsers.
 *
 */
public interface ViewDefinitionParserService<T extends ViewDefinition> {

    /*
     * 🐱method parse: List[ViewDefinition]
     * 🐱param steps: List[Step]
     *
     * Based on the list of steps, offer a list of compatible ViewDefinitions.
     */
    List<T> parse(List<Step> steps);

    /*
     * 🐱method appliesTo: boolean
     * 🐱param steps: List[Step]
     * 🐱param viewDefinition: ViewDefinition
     *
     * Check if the viewDefinition applies to the steps
     */
    boolean appliesTo(List<Step> steps, ViewDefinition viewDefinition);

}
