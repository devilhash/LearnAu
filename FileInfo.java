package com.app.learnau.CSE.Fragments;

public class FileInfo {
    String filename,fileurl;
    int nov,nol;

    public FileInfo(String filename, String fileurl) {
    }

    public void setNov(int nov) {
        this.nov = nov;
    }

    public void setNol(int nol) {
        this.nol = nol;
    }

    public int getNov() {
        return nov;
    }

    public int getNol() {
        return nol;
    }

    public FileInfo(String filename, String fileurl,int nov,int nol) {
        this.filename = filename;
        this.fileurl = fileurl;
        this.nov = nov;
        this.nol = nol;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

}
