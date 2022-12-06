package org.example.controller;

import com.septemberhx.common.base.MResponse;
import com.wangteng.mclient.annotation.MLogFunction;
import com.wangteng.mclient.base.MObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

import java.util.Map;

/**
 * @author yang
 * @version 1.0
 * @date 2022/12/01
 */
@Slf4j
@RestController
public class TravelController extends MObject {





    @Resource
    private RestTemplate restTemplate;



    /**
     * @param
     * @return get the detail info of the traveller by useId
     * call the interface in Route Service to get the route info of the traveller
     */
    @MLogFunction
//    @MRestApiType
//    @MApiFunction
    @ResponseBody
    @GetMapping("/getDetailInfo")
    public MResponse getDetailInfo(@RequestParam(value = "userId") String userId){
        MResponse result = new MResponse();

        if (userId.equals("00001")){
            String url = "http://traceRouteService/getRouteInfo/?userId={userId}";
//            MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
            Map<String,Object> multiValueMap = new HashMap<>();
            multiValueMap.put("userId",userId);
            MResponse mResponse1 = restTemplate.getForObject(url,MResponse.class,multiValueMap);
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
    @MLogFunction
//    @MRestApiType
//    @MApiFunction
    @ResponseBody
    @GetMapping("/getSeatDistribute")
    public MResponse getSeatDistribute(@RequestParam(value = "flight") String flight){
        MResponse result = new MResponse();
        if (flight.equals("MU5542")){
            result.set("seat", "06B");
        }
        return  result;
    }

}
