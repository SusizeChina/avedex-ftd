package com.avedex.cc.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.avedex.cc.entity.TransactionHistory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    /**
     * @param is    导入文件输入流
     * @param clazz Excel实体映射类
     * @return
     */
    public static Boolean readExcel(InputStream is, Class clazz) {

//        BufferedInputStream bis = null;
//        try {
//            bis = new BufferedInputStream(is);
//            // 解析每行结果在listener中处理
//            AnalysisEventListener listener = new ExcelListener(userDao);
//            ExcelReader excelReader = EasyExcelFactory.getReader(bis, listener);
//            excelReader.read(new Sheet(1, 1, clazz));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return true;
    }

    /**
     * @param response http请求报文
     * @param clazz    Excel实体映射类
     * @param data     导出数据
     * @return
     */
    public static Boolean writeExcel(HttpServletResponse response, Class clazz, String fileName, List<? extends BaseRowModel> data) {
        BufferedOutputStream bos = null;
        try {
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //这边对应的实体类改为你所导出的实体类

            Sheet sheet = new Sheet(1, 0, TransactionHistory.class);
            //设置列宽 设置每列的宽度
            Map columnWidth = new HashMap();
            //设置自适应宽度
            columnWidth.put(0, 5000);
            columnWidth.put(1, 4000);
            columnWidth.put(2, 5000);
            columnWidth.put(3, 5000);
            columnWidth.put(4, 5000);
            columnWidth.put(5, 10000);
            columnWidth.put(6, 5000);
            sheet.setColumnWidthMap(columnWidth);
            sheet.setAutoWidth(Boolean.TRUE);
            // 第一个 sheet 名称
            sheet.setSheetName("交易历史");
            writer.write(data, sheet);
            //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + ".xlsx");
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


}
