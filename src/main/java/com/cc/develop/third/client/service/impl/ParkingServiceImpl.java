package com.cc.develop.third.client.service.impl;

import com.cc.develop.third.client.model.SeedUserScanExcelData;
import com.cc.develop.third.client.service.IParkingService;
import com.cc.develop.third.client.util.RegexUtil;
import com.cc.develop.third.client.util.TransCarPlateColor2Code;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * com.cc.develop.third.client.service.impl
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-02 14:02:34
 */
@Slf4j
@Service
public class ParkingServiceImpl implements IParkingService {

    @Override
    public String importExcelData(MultipartFile file) {
        //将导入文件中的数据写入到本地文件中
        try {

            File sourceFile = new File("source.txt");

            FileWriter fileWriter = new FileWriter(sourceFile,true);

            InputStream inputStream = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            // 每扫描一个 添加一个进去
            for (int rowNum = 1; rowNum < rowCount; rowNum++) {
                Row rowData = sheet.getRow(rowNum);
                SeedUserScanExcelData data = new SeedUserScanExcelData();
                StringBuffer sbf = new StringBuffer("shareData:");

                String parkingName = rowData.getCell(8).toString();
                data.setParkingName(parkingName);
                String code = "PT012102";
                if (StringUtils.isEmpty(code)) {
                    return "failure";
                }
                data.setParkingCode(code);

                String carPlateNo = rowData.getCell(7).toString();
                data.setCarPlateNo(carPlateNo);
                sbf.append(carPlateNo);
                sbf.append(":");
                String carPlateColor = TransCarPlateColor2Code.transCarPlateColorToCode(rowData.getCell(6).toString());
                data.setCarPlateColor(carPlateColor);
                sbf.append(carPlateColor);
                sbf.append(":");
                sbf.append(code);

                data.setUserType(rowData.getCell(5).toString());

                Cell c3 = rowData.getCell(3);
                //开始时间
                data.setStartDatetime(getDate2String(c3));
                // 截至时间
                Cell c4 = rowData.getCell(4);
                data.setEndDatetime(getDate2String(c4));

                data.setPhone(rowData.getCell(2).toString());
                data.setCarOwnerName(rowData.getCell(1).toString());

                // 封装 车牌：车牌颜色：停车场code carPlateNo:color:code
                fileWriter.write(sbf.toString());
                fileWriter.write(",");

            }
            //关闭流
            fileWriter.close();

            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }

    }

    @Override
    public List<String> getLocalFileData() {

        List<String> list = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File("source.txt"));

            StringBuffer sbf = new StringBuffer();
            char[] cbuf = new char[5];
            int len;
            while((len = fileReader.read(cbuf)) != -1){
                String str = new String(cbuf,0,len);
                sbf.append(str);
            }
            //逗号分割处理数据
            String[] split = sbf.toString().split(",");
            list = Arrays.asList(split);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return list;

    }


    private String getDate2String(Cell cell) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "";
        //判断是否是时间类型
        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            Date date = cell.getDateCellValue();
            str = sdf.format(date);
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            //检查时间格式是否正确
            boolean checkDate = RegexUtil.isLegalDate(cell.toString());
            if (checkDate) {
                //字符串 解析时间
                Date parseDate = null;
                try {
                    parseDate = sdf.parse(cell.toString());
                } catch (ParseException e) {
                    parseDate = new Date();
                    e.printStackTrace();
                }
                str = sdf.format(parseDate);
            } else {
                str = sdf.format(new Date());
            }
        }
        return str;
    }

}
