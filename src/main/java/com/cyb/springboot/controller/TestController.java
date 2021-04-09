package com.cyb.springboot.controller;


import com.cyb.springboot.model.TestBean;
import com.cyb.springboot.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("test22")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "成功接收到请求！";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<TestBean> query(){
        return testService.query();
    }

    @ApiOperation("导出excel")
    @GetMapping("/excel")
    public void excel( HttpServletResponse response)throws IOException {
        testService.excel(response);
        return;
    }


/**
 * excel导入数据
 */
    @PostMapping("importexcel")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Object importWatchExcel(@RequestParam("excelFile") MultipartFile xlsFile) {
        Map<String, Object> result = new HashMap<>();
        // contentType
        // String contentType = file.getContentType();
        // excel文件名
        // String fileName = file.getOriginalFilename();
        if (xlsFile.isEmpty()) {
            result.put("code", 500);
            result.put("message", "导入文件为空！");
            return result;
        }
        // 根据不同excel创建不同对象,Excel2003版本-->HSSFWorkbook,Excel2007版本-->XSSFWorkbook
        Workbook wb = null;
        InputStream im = null;
        try {
            im = xlsFile.getInputStream();
            wb = WorkbookFactory.create(im);
            // 根据页面index 获取sheet页
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            // 循环sheet页中数据从第x行开始,例:第3行开始为导入数据
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 获取每一行数据
                row = sheet.getRow(i);
                // 输出表格内容,此处可替换为数据插入操作
                TestBean testBean = new TestBean();
                testBean.setName(row.getCell(0).toString());
                System.out.println(row.getCell(0));
                //插入数据库
                //testService.insert(testBean);

                // 日期,表格数字格式为日期
                /*if (null != row.getCell(0) && "" != row.getCell(0).toString()) {
                    System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(row.getCell(0).getDateCellValue()));
                }
                // 内容,表格数字格式为常规
                if (null != row.getCell(1) && "" != row.getCell(1).toString()) {
                    // 如果表格内容为数字,需要设置CellType为string，否则调用getStringCellValue()会出现获取类型错误
                    row.getCell(1).setCellType(CellType.STRING);
                    System.out.println(row.getCell(1).getStringCellValue());
                }*/
            }
            result.put("code", 200);
            result.put("message", "导入成功!");
        } catch (Exception e1) {
            // 回滚数据
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e1.printStackTrace();
        } finally {
            try {
                im.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return result;

    }



}

