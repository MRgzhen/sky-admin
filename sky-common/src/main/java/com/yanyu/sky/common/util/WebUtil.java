package com.yanyu.sky.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yanyu
 */
public class WebUtil {

    /**
     * 响应
     * @param response 响应
     * @param data 数据
     * @param fileName 文件名
     */
    public static void outStream(HttpServletResponse response, byte[] data, String fileName) {
        try (ServletOutputStream pw = response.getOutputStream()){
            response.setCharacterEncoding("UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.zip\"",fileName));
            response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            pw.write(data);
        } catch (IOException e) {
        }
    }
}
