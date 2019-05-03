package com.example.echo.views.fragments;

public class StuContent {
    public static class StuItemContent{

        public String stuName,stuNum;
        public int stuPhotoId;

        public StuItemContent(String stuName,String stuNum,int stuPhotoId){
            this.stuName = stuName;
            this.stuNum = stuNum;
            this.stuPhotoId = stuPhotoId;
        }
    }
}
