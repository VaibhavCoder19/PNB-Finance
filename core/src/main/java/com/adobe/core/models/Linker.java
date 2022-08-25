package com.adobe.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
adapters = Linker.class)
public class Linker {

    @ValueMapValue
    private String path;

    @ValueMapValue
    private String title;

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
