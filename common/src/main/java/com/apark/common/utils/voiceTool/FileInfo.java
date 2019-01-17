package com.apark.common.utils.voiceTool;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public  class FileInfo {
    /**
     * 文件所在的目录
     */
    private String path;


    /**
     * 文件名 （不包含后缀）
     */
    private String filename;


    /**
     * 文件类型
     */
    private String fileType;


    public String getAbsFile() {
        return path  + "\\" + filename + "." + fileType;
    }
}