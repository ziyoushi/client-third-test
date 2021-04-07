package com.cc.develop.third.client.service.impl;

import com.cc.develop.third.client.service.IDataPushConfigService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

/**
 * com.cc.develop.third.client.service.impl
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-20 15:21:55
 */
@Service
public class DataPushConfigServiceImpl implements IDataPushConfigService {

    @Override
    public String createJavaBean() {

        try {
            File sourceFile = new File("source.txt");

            FileWriter fileWriter = new FileWriter(sourceFile,true);

        }catch (Exception e){

        }

        return null;
    }
}
