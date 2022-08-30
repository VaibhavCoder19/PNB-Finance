package com.adobe.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

//@Component(service= Servlet.class, immediate=true,
//        property = {
//                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
//                "sling.servlet.paths=" + "/bin/itemdata"
//        })
@Component(service= Servlet.class)
@SlingServletResourceTypes(
        resourceTypes="granite/ui/components/shell/page",
        methods= HttpConstants.METHOD_POST)


public class FormPostServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = -159625176093879129L;

    private static final Logger log = LoggerFactory.getLogger(FormPostServlet.class);

    private ResourceResolver resourceResolver;


    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {

            PrintWriter out = response.getWriter();

            resourceResolver = request.getResourceResolver();

            Resource resource = resourceResolver.getResource("/content/items");

            String modelid = request.getParameter("modelid");
            String productid = request.getParameter("productid");
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");
            String dealerid = request.getParameter("dealerid");

            Node node = resource.adaptTo(Node.class);
            Node newNode = node.addNode(productid, "nt:unstructured");
            newNode.setProperty("Product-ID",productid);
            newNode.setProperty("Model-ID", modelid);
            newNode.setProperty("Start-Date", startdate);
            newNode.setProperty("End-Date", enddate);
            newNode.setProperty("Dealer-ID", dealerid);

            resourceResolver.commit();
            out.write(modelid);
        }
        catch(Exception e){
            log.info(e.getMessage());
        }
    }
}

