package org.acme.getting.started.commandmode;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.Date;
import java.util.Map;


@QuarkusMain
public class InitMain implements QuarkusApplication {



    @Override
    public int run(String... args) throws Exception {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        String url = System.getenv("CHECK_URL");
        if(url == null || "".equals(url)){
            System.out.println("url is empty exit");
            return 0;
        }
        do{
            try{
                HttpRequest request = requestFactory.buildGetRequest(
                        new GenericUrl(url));
                String rawResponse = request.execute().parseAsString();;
                System.out.println("rawResponse:"+ rawResponse);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> jsonMap = mapper.readValue(rawResponse,
                        new TypeReference<Map<String,String>>(){});
                if(jsonMap.containsKey("code") && "0".equals(jsonMap.get("code"))){
                    System.out.println("--------------------- init successful exit --------------------------");
                    return 0;
                }
            }catch (Exception e){
                System.out.println("--------------------- init fail continue  " + new Date() + "--------------------------");
                e.printStackTrace();
            }
            Thread.sleep(2000);
        }while (true);

    }


}