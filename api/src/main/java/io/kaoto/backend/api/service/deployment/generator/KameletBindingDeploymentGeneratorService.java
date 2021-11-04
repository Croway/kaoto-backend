package io.kaoto.backend.api.service.deployment.generator;

import io.kaoto.backend.model.deployment.kamelet.KameletBinding;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingSpec;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStep;
import io.kaoto.backend.model.deployment.kamelet.KameletBindingStepRef;
import io.kaoto.backend.model.step.Step;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class KameletBindingDeploymentGeneratorService
        implements DeploymentGeneratorService {

    @Override
    public String parse(final String name, final List<Step> steps) {
        if (!appliesTo(steps)) {
            return "";
        }

        KameletBindingSpec spec = new KameletBindingSpec();

        spec.setSource(createKameletBindingStep(steps.get(0)));

        for (int i = 1; i < steps.size() - 1; i++) {
            spec.getSteps().add(createKameletBindingStep(steps.get(i)));
        }

        spec.setSink(createKameletBindingStep(steps.get(steps.size() - 1)));

        KameletBinding binding = new KameletBinding(name, spec);

        Representer representer = new Representer() {
            @Override
            protected NodeTuple representJavaBeanProperty(
                    final Object javaBean,
                    final Property property,
                    final Object propertyValue,
                    final Tag customTag) {
                if (propertyValue == null) {
                    return null;
                }
                return super.representJavaBeanProperty(javaBean, property,
                        propertyValue, customTag);
            }
        };
        representer.getPropertyUtils().setAllowReadOnlyProperties(true);

        Yaml yaml = new Yaml(new Constructor(KameletBinding.class),
                    representer);
        return yaml.dumpAsMap(binding);
    }

    private KameletBindingStep createKameletBindingStep(final Step step) {
        KameletBindingStep kameletStep = new KameletBindingStep();

        KameletBindingStepRef ref = new KameletBindingStepRef();
        ref.setName(step.getName());
        kameletStep.setRef(ref);

        if (step.getParameters() != null) {
            for (var p : step.getParameters()) {
                if (p.getValue() != null) {
                    kameletStep.getProperties().put(p.getId(),
                            p.getValue().toString());
                }
            }
        }

        return kameletStep;
    }

    @Override
    public boolean appliesTo(final List<Step> steps) {
        if (steps.size() < 2) {
            return false;
        }

        for (Step s : steps) {
            if ("KAMELET".equalsIgnoreCase(s.getSubType())) {
                return true;
            }
        }

        return false;
    }
}
