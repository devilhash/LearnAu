package com.app.learnau.CSE.Fragments;

public class model {
    String filename,fileurl;
    int nov,nol;

    public model(String filename, String fileurl, int nov, int nol) {
        this.filename = filename;
        this.fileurl = fileurl;

    }

    public model() {
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
