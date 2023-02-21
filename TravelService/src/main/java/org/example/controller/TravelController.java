package org.example.controller;


import com.septemberhx.common.base.MResponse;
import com.netflix.ribbon.proxy.annotation.Http;
import com.septemberhx.mclient.annotation.MApiFunction;
import com.septemberhx.mclient.annotation.MRestApiType;
import com.septemberhx.mclient.base.MObject;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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



    /**
     * @param
     * @return get the detail info of the traveller by useId
     * call the interface in Route Service to get the route info of the traveller
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @MRestApiType
    @MApiFunction
    @PostMapping("/getDetailInfo")
    public MResponse getDetailInfo(@RequestParam("userId") String userId,@RequestHeader HttpHeaders httpHeaders){
        MResponse result = new MResponse();

        if (userId.equals("00001")){

            MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("userId",userId);

            HttpEntity httpEntity = new HttpEntity(multiValueMap,httpHeaders);
            String services ="routeservice";
            ResponseEntity<MResponse> responseResponseEntity = restTemplate.exchange("http://routeservice/getRouteInfo", HttpMethod.POST,httpEntity,MResponse.class);

//            MResponse mResponse1 = restTemplate.postForObject(url,multiValueMap,MResponse.class);
            if (responseResponseEntity.getBody().getStatus().equals("Success")){
                result.setValueMap(responseResponseEntity.getBody().getValueMap());
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
    @SuppressWarnings("unchecked")
    @ResponseBody
    @MRestApiType
    @MApiFunction
    @PostMapping("/getSeatDistribute")
    public MResponse getSeatDistribute(@RequestParam(value = "flight") String flight, @RequestHeader HttpHeaders httpHeaders){
//        System.out.println("httpHeaders.toSingleValueMap()::"+httpHeaders.toSingleValueMap());

        MResponse result = new MResponse();
        if (flight.equals("MU5542")){
            result.set("seat", "06B");
        }
        return  result;
    }

}
