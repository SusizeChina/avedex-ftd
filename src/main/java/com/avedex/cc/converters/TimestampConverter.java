package com.avedex.cc.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter implements Converter<Integer> {

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return cellData.getDataFormat();
    }


    @Override
    public CellData convertToExcelData(Integer timestamp, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Date date = new Date(timestamp * 1000L);
        return new CellData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }
}
