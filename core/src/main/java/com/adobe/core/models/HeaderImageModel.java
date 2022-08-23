package com.adobe.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.mail.Header;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderImageModel {

    @ChildResource
    private List<Resource> items;

    Logger logger = LoggerFactory.getLogger(HeaderImageModel.class);


    private List<ImageDetailsModel> imageDetails= new LinkedList<>();

    @PostConstruct
    void init(){
        logger.info("12345ndjbdmb");
//        imageDetails = new LinkedList<>();
        if(Objects.nonNull(items)){
            for(Resource item: items){
                ImageDetailsModel imageModel = item.adaptTo(ImageDetailsModel.class);
                if(Objects.nonNull(imageModel)){
                    imageDetails.add(imageModel);
                }
            }
        }
    }

    public List<ImageDetailsModel> getImageDetails() {
        return imageDetails;
    }
}
