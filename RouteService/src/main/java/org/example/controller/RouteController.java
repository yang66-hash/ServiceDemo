package org.example.controller;

import com.septemberhx.common.base.MResponse;
import com.septemberhx.mclient.annotation.MApiFunction;
import com.septemberhx.mclient.annotation.MRestApiType;
import com.septemberhx.mclient.base.MObject;
import com.wangteng.mclient.annotation.MLogFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class RouteController extends MObject {

    @Autowired
    private RestTemplate restTemplate;




    /**
     * @param
     * @return get the info the travel route
     *
     * called by the interface in Travel Service and it also call the function in the travel service
     */
    @MLogFunction
//    @MRestApiType
//    @MApiFunction
    @GetMapping("/getRouteInfo")
    public MResponse getRouteInfo(@RequestParam(value = "userId") String useId,@RequestHeader HttpHeaders httpHeaders){
        useId = useId.toLowerCase();
        MResponse result = new MResponse();
        //id destination
        result.set("from","威海");
        result.set("to","上饶");
        result.set("flight", "MU5542");
        String url = "http://TravelService/getSeatDistribute/?flight={flight}";
//        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        Map<String,Object> map = new HashMap<>();
        map.put("flight","MU5542");
        MResponse mResponse = restTemplate.getForObject(url,MResponse.class,map);
        if (mResponse.get("seat")!=null){
            result.set("seat",mResponse.get("seat"));
        }
        return result;
    }

}
