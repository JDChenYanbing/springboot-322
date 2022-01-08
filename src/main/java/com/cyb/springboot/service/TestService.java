package com.cyb.springboot.service;

import com.cyb.springboot.model.TestBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface TestService {
    List<TestBean> query();

    void excel(HttpServletResponse response) throws IOException;


    int insert(TestBean testBean);
}
