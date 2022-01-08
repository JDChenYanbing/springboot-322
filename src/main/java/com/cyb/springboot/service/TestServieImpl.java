package com.cyb.springboot.service;


import com.cyb.springboot.mapper.TestMapper;
import com.cyb.springboot.model.TestBean;
import com.cyb.springboot.util.ExcelUtil;
import com.mysql.cj.util.TestUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

@Service
public class TestServieImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestBean> query() {
        return testMapper.query();
    }

    @Override
    public void excel(HttpServletResponse response)throws IOException {
        //确定编码的格式
        response.setCharacterEncoding("UTF-8");
        //获取导出的数据
        List<TestBean> list = testMapper.query();
        //创建excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建sheet页
        XSSFSheet sheet = wb.createSheet("测试-4-2");
        //创建标题行
        XSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("ID");
        titleRow.createCell(1).setCellValue("姓名");
        titleRow.createCell(2).setCellValue("年龄");

        //遍历将数据放到excel列中
        for (int i = 0; i < list.size(); i++) {
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(list.get(i).getId());
            dataRow.createCell(1).setCellValue(list.get(i).getName());
            dataRow.createCell(2).setCellValue(list.get(i).getAge());

        }
        // 设置下载时客户端Excel的名称   （上面注释的改进版本，上面的中文不支持）
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                //该版本为2007-的版本
                + new String("名单".getBytes("UTF-8"), "iso-8859-1")
                + ".xlsx");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();

    }

    @Override
    public int insert(TestBean testBean) {
        String name = testBean.getName();
        Integer age = testBean.getAge();

        return testMapper.insert(name,age);
    }

}
