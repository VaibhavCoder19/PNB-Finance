package com.adobe.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=post servlet class",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.resourceTypes=" + "training/components/page",
        "sling.servlet.selectors="+"json",
        "sling.servlet.extensions="+"mongo"})
public class MongoServlet extends SlingAllMethodsServlet {
    private static final Logger logger = LoggerFactory.getLogger(MongoServlet.class);


    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response){
        logger.info("doPost Running");
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");

        Map<String, String> mp = new HashMap<>();
        mp.put("name", name);
        mp.put("location", location);
        mp.put("mobile", mobile);
        mp.put("email", email);
        logger.info("Data Map: {}", mp);
        JSONObject resultObj = new JSONObject(mp);
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(String.valueOf(resultObj));
        } catch (IOException e) {
            logger.error("Writer Error {}", e.getMessage());
        }

    }
//    public void connectDB(){
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://<username>:<password>@cluster0.omdnodp.mongodb.net/?retryWrites=true&w=majority");
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .serverApi(ServerApi.builder()
//                        .version(ServerApiVersion.V1)
//                        .build())
//                .build();
//        MongoClient mongoClient = (MongoClient) MongoClients.create(settings);
//        MongoDatabase database = mongoClient.getDatabase("test");
//
//    }
}
