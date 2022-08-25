package com.adobe.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Model( adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DropDownModel {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @ChildResource
    List<Resource> droplist;

    List<DropDown> list=new LinkedList<>();

    @PostConstruct
    public void init(){
        logger.info("Creating the drop down list");
        for(Resource item: droplist)
        {
            DropDown dropDown=item.adaptTo(DropDown.class);
            logger.info(dropDown.getPath()+"                     "+dropDown.getInnerTitle());
            list.add(dropDown);
        }
    }


    public List<DropDown> getList() {
        return list;
    }
}
