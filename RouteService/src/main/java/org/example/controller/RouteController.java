package org.example.controller;

import com.septemberhx.common.base.MResponse;
import com.septemberhx.mclient.annotation.MApiFunction;
import com.septemberhx.mclient.annotation.MRestApiType;
import com.septemberhx.mclient.base.MObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class RouteController extends MObject {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;




    /**
     * @param
     * @return get the info the travel route
     *
     * called by the interface in Travel Service and it also call the function in the travel service
     */
    @ResponseBody
    @MRestApiType
    @MApiFunction
    @PostMapping("/getRouteInfo")
    public MResponse getRouteInfo(@RequestParam(value = "userId") String userId){
        MResponse result = new MResponse();
        //id destination
        result.set("from","威海");
        result.set("to","上饶");
        result.set("flight", "MU5542");
        String url = "http://TravelService/getSeatDistribute";
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("flight","MU5542");
        MResponse mResponse = restTemplate.postForObject(url,map,MResponse.class);
        if (mResponse.get("seat")!=null){
            result.set("seat",mResponse.get("seat"));
        }
        return result;
    }

}
