package com.vsks.service.impl;

import static com.vsks.constants.StringConstants.*;

import com.vsks.domain.Response;
import com.vsks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String fetchEmployeeEmailId(Long empId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT_HEADER_VALUE);
        HttpEntity<String> reqEntity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(END_POINT_URL  + SLASH + empId, HttpMethod.GET, reqEntity, Response.class);
        if (HttpStatus.OK != responseEntity.getStatusCode()) {
            if (null == responseEntity.getBody()) {
                return "No response";
            } else {
                return "Status is " + responseEntity.getStatusCode().value() + ":" + responseEntity.getStatusCode().name();
            }
        }
        if (null != responseEntity.getBody().getData()) {
            return "Email Id for the emp Id " + empId + " is " + responseEntity.getBody().getData().getEmail();
        }
        return "No email address added for the emp Id " + empId;
    }

}
