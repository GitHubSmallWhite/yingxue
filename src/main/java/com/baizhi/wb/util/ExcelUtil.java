package com.baizhi.wb.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.wb.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    public static void ExcelExport(List<User> users, HttpServletRequest request) throws IOException {
        for (int i = 0; i < users.size();i++) {
            User user = users.get(i);
            String picImg = user.getPicImg();
            String realPath = request.getServletContext().getRealPath(picImg);
            user.setPicImg(realPath);
            users.set(i,user);
        }
        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("用户详细信息","用户信息"),User.class,users);
        workbook.write(new FileOutputStream(new File("E:/用户信息表.xls")));
        workbook.close();
    }
    public static void ExcelImport(MultipartFile file, HttpServletRequest request) throws IOException {
        ImportParams params=new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(2);
        File file1=(File) file;
        String absolutePath = file1.getAbsolutePath();
        List<User> users = ExcelImportUtil.importExcel(new File(absolutePath), User.class, params);
        for (User user : users) {
            System.out.println(user);
        }
    }
    public static List<User> ExcelImport(){
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<User> users = ExcelImportUtil.importExcel(new File("E://用户信息表.xls"), User.class, params);
        return users;
    }
}
