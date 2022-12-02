package org.example.controller;

import com.septemberhx.common.base.MResponse;
import com.septemberhx.mclient.annotation.MApiFunction;
import com.septemberhx.mclient.annotation.MRestApiType;
import com.septemberhx.mclient.base.MObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yang
 * @version 1.0
 * @date 2022/12/01
 */
@RestController
public class TravelController extends MObject {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * @param
     * @return get the detail info of the traveller by useId
     * call the interface in Route Service to get the route info of the traveller
     */
    @ResponseBody
    @MRestApiType
    @MApiFunction
    @PostMapping("/getDetailInfo")
    public MResponse getDetailInfo(@RequestParam("userId") String userId){
        MResponse result = new MResponse();

        if (userId.equals("00001")){
            String url = "http://RouteService/getRouteInfo";
            MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("userId",userId);
            MResponse mResponse1 = restTemplate.postForObject(url,multiValueMap,MResponse.class);
            if (mResponse1.getStatus().equals("Success")){
                result.setValueMap(mResponse1.getValueMap());
            }
        }
        result.set("name","张三");
        result.set("type","单人游");

        return result;


    }


    /**
     * @param
     * @return search the seat info of the traveller by flight
     */
    @ResponseBody
    @MRestApiType
    @MApiFunction
    @PostMapping("/getSeatDistribute")
    public MResponse getSeatDistribute(@RequestParam(value = "flight") String flight){
        MResponse result = new MResponse();
        if (flight.equals("MU5542")){
            result.set("seat", "06B");
        }
        return  result;
    }

}
