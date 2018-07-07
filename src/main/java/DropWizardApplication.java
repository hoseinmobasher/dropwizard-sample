import configuration.DropWizardConfiguration;
import health.DropWizardHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.DropWizardResources;

public class DropWizardApplication extends Application<DropWizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropWizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    /**
     * An initialize method is used to configure aspects of the application
     * required before the application is run, like bundles, configuration
     * source providers, etc.
     */
    @Override
    public void initialize(Bootstrap<DropWizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DropWizardConfiguration dropWizardConfiguration,
                    Environment environment) {
        final DropWizardResources resources = new DropWizardResources(
                dropWizardConfiguration.getTemplate(),
                dropWizardConfiguration.getDefaultName()
        );

        final DropWizardHealthCheck healthCheck = new DropWizardHealthCheck(
                dropWizardConfiguration.getTemplate()
        );

        environment.healthChecks().register("hello-world", healthCheck);
        environment.jersey().register(resources);
    }
}
