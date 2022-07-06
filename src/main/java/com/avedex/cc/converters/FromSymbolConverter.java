package com.avedex.cc.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class FromSymbolConverter implements Converter<String> {
    public static final String WTRX = "WTRX";
    public static final String FTD = "FTD";
    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return cellData.getStringValue();
    }


    @Override
    public CellData convertToExcelData(String symbol, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (WTRX.equalsIgnoreCase(symbol)) {
            symbol = "入金";
        } else if (FTD.equalsIgnoreCase(symbol)) {
            symbol = "出金";
        }
        return new CellData(symbol);
    }
}
