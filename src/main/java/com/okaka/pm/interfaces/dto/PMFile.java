package com.okaka.pm.interfaces.dto;

import lombok.Data;

/**
 * @author okaka
 * @date 2023-09-19
 */
@Data
public class PMFile {

    private String fileName;

    private String filePath;

    private FileType fileType;


    public PMFile(String fileName, String filePath, FileType fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return fileName;
    }

    public enum FileType {
        FILE,
        FOLDER
    }

}
