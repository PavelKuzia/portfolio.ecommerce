package ecommerce_app.configuration;

import ecommerce_app.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
    EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH};
        cors.addMapping("/api/**").allowedOrigins(this.allowedOrigins);
        disableHttpMethods(Product.class, restConfig, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, restConfig, theUnsupportedActions);
        disableHttpMethods(Country.class, restConfig, theUnsupportedActions);
        disableHttpMethods(State.class, restConfig, theUnsupportedActions);
        disableHttpMethods(Order.class, restConfig, theUnsupportedActions);
        exposeIds(restConfig);
    }

    private void disableHttpMethods(Class myClass, RepositoryRestConfiguration config, HttpMethod[] unsupportedMethods) {
        config
                .getExposureConfiguration()
                .forDomainType(myClass)
                .withItemExposure(((_, httpMethods) -> httpMethods.disable(unsupportedMethods)))
                .withCollectionExposure(((_, httpMethods) -> httpMethods.disable(unsupportedMethods)));

    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();

        for (EntityType entity : entities) {
            entityClasses.add(entity.getJavaType());
        }
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
