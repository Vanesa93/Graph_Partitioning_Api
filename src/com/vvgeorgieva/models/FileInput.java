package com.vvgeorgieva.models;

import org.springframework.web.multipart.MultipartFile;

public class FileInput {

    public MultipartFile fileInput;
    public String dataFormat;
    public String separator;

    public MultipartFile getFileInput() {
        return fileInput;
    }

    public void setFileInput(MultipartFile fileInput) {
        this.fileInput = fileInput;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }
    
    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

}