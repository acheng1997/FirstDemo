package com.cc.pojo;

public class MyFile {
    //    id
    private Integer id;
    //    映射路径
    private String filePath;
    //    用户输入的文件名
    private String fileName;
    //    用户名
    private String userName;
    //    文件大小
    private String fileSize;
    //    文件下载次数
    private Integer fileNumber;
    //    文件实际名
    private String fileDownload;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Integer fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(String fileDownload) {
        this.fileDownload = fileDownload;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", userName='" + userName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileNumber=" + fileNumber +
                ", fileDownload='" + fileDownload + '\'' +
                '}';
    }
}
