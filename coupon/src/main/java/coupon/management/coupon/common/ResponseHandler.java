package coupon.management.coupon.common;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(
            String message,
            String status,
            Object response,
            HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", response);

        return new ResponseEntity<Object>(map, httpStatus);
    }
}
