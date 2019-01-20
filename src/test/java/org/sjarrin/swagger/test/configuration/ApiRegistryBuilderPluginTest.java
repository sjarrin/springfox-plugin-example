package org.sjarrin.swagger.test.configuration;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.sjarrin.swagger.test.annotations.AuthLevel2;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class ApiRegistryBuilderPluginTest {

    @InjectMocks
    private ApiRegistryBuilderPlugin apiRegistryBuilderPlugin;

    @Test
    public void should_addExtension_when_relevantAnnotationIsPresent() {
        //GIVEN
        OperationContext context = mock(OperationContext.class);
        AuthLevel2 authLevel2Annotation = mock(AuthLevel2.class);
        when(authLevel2Annotation.issuers()).thenReturn(new String[] {"issuer-1", "issuer-2"});
        Optional<AuthLevel2> loa2Optional = Optional.of(authLevel2Annotation);
        when(context.findAnnotation(eq(AuthLevel2.class))).thenReturn(loa2Optional);
        when(context.operationBuilder()).thenCallRealMethod();

        //WHEN
        apiRegistryBuilderPlugin.apply(context);

        //THEN
        List<String> expected = Lists.newArrayList("authentication-1", "authentication-2");
        assertThat(context.getDocumentationContext().getVendorExtentions(), is(expected));
    }
}
