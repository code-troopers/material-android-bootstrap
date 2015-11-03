package com.codetroopers.materialAndroidBootstrap;

import com.codetroopers.materialAndroidBootstrap.core.components.ComponentsFactory;

import org.junit.rules.ExternalResource;

/**
 * Rule which replace the dagger Components used by the application by another specific to the test
 * (for mocking purposes).
 * Reset the production component after the tests for the class are done (in order to prevent some
 * failures when running all tests, because the Application is not recreated)
 */
public class TestComponentsRule extends ExternalResource {

    private final ComponentsFactory testComponentsFactory;
    private ComponentsFactory productionComponentsFactory;

    public TestComponentsRule(ComponentsFactory testComponentsFactory) {
        this.testComponentsFactory = testComponentsFactory;
    }

    @Override
    protected void before() throws Throwable {
        productionComponentsFactory = ComponentsFactory.get();
        ComponentsFactory.register(testComponentsFactory);
    }

    @Override
    protected void after() {
        ComponentsFactory.register(productionComponentsFactory);
    }
}
