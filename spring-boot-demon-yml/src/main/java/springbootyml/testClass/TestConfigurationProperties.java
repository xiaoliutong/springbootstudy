package springbootyml.testClass;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "man")
@Data
public class TestConfigurationProperties {
    private String age;
    private String name;
    private List<Object> objects;
    private Map<Object,Object> map;
}
