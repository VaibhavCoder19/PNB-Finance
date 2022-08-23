package com.adobe.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = ImageDetailsModel.class)
public class ImageDetailsModel {


    @ValueMapValue
    private String fileName;

    @ValueMapValue
    private String linkUrl;


    public String getLinkUrl() {
        return linkUrl;
    }

    public String getFileName() {
        return fileName;
    }
}
