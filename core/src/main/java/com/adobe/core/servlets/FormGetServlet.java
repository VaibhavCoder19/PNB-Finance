package com.adobe.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component(service= Servlet.class)
@SlingServletResourceTypes(
        resourceTypes="granite/ui/components/shell/page",
        methods= HttpConstants.METHOD_GET)

public class FormGetServlet extends SlingSafeMethodsServlet {

    @Reference
    private ResourceResolverFactory resolverFactory;


    private static final Logger log = LoggerFactory.getLogger(FormPostServlet.class);

//    private ResourceResolver resourceResolver;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        ResourceResolver resourceResolver = request.getResourceResolver();


        String productid = request.getParameter("productid");

        Resource resource = resourceResolver.getResource("/content/items");

        Node node = resource.adaptTo(Node.class);

        try {
            Node newnode = node.getNode(productid);

            PropertyIterator propertyIterator = newnode.getProperties();
            out.println("<html>");
            out.println("<head><title>A Test Servlet</title></head>");
            out.println("<body>");
            out.println("<table>");
            while (propertyIterator.hasNext()) {
                out.println("<tr>");
                Property property = propertyIterator.nextProperty();
                String propName = property.getName();
                if (propName.equals("jcr:primaryType")) {
                    continue;
                }
                out.println("<td>" + propName + "</td>");
                String propValue = property.getValue().getString();
                out.println("<td>" + propValue + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");
            resourceResolver.commit();

        } catch (RepositoryException e) {
            out.println("No Item Found with this product id");
            log.info(e.getMessage());
        }
    }
}
