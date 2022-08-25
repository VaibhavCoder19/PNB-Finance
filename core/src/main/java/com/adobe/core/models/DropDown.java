package com.adobe.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model( adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = DropDown.class)
public class DropDown {

    @ValueMapValue
    private String innertitle;

    @ValueMapValue
    private String path;

    public String getInnerTitle() {
        return innertitle;
    }

    public String getPath() {
        return path;
    }
}
