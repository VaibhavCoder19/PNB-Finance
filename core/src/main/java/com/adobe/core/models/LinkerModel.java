package com.adobe.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinkerModel {
//    protected static final String RESOURCE_TYPE="/pnb/components/header";

    Logger logger= LoggerFactory.getLogger(LinkerModel.class);
    @ChildResource
    private List<Resource> multifield;

    private List<Linker> list;

    @PostConstruct
    void init(){
        logger.info("Printing the values.................");
        list=new LinkedList<>();
        if(Objects.nonNull(multifield))
        {
            for(Resource field : multifield)
            {
                Linker linker=field.adaptTo(Linker.class);
                list.add(linker);
                logger.info("-------------------------"+linker.getPath()+"        "+linker.getTitle());
            }
        }
    }

    public List<Linker> getList(){
        return list;
    }
}
