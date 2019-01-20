package org.sjarrin.swagger.test.configuration;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.sjarrin.swagger.test.annotations.AuthLevel2;
import org.sjarrin.swagger.test.annotations.AuthLevel3;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Arrays;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class ApiRegistryBuilderPlugin implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        String notes = "";

        Optional<AuthLevel2> loa2Annotation = context.findAnnotation(AuthLevel2.class);
        if (loa2Annotation.isPresent()) {
            notes += "Auth level 2";
            context.operationBuilder().extensions(
                    Lists.newArrayList(
                            new StringVendorExtension(
                                    "x-authLevel2",
                                    Boolean.toString(true)),
                            new ListVendorExtension(
                                    "x-loa2-issuers",
                                    Arrays.asList(loa2Annotation.get().issuers())
                            )
                    )
            );
        }
        Optional<AuthLevel3> loa3Annotation = context.findAnnotation(AuthLevel3.class);
        if (loa3Annotation.isPresent()) {
            notes += "Auth level 3";
            context.operationBuilder().extensions(
                    Lists.newArrayList(
                            new StringVendorExtension(
                                    "x-authLevel3",
                                    Boolean.toString(true)),
                            new ListVendorExtension(
                                    "x-loa3-issuers",
                                    Arrays.asList(loa3Annotation.get().issuers())
                            )
                    )
            );
        }

        context.operationBuilder().notes(notes);
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}
