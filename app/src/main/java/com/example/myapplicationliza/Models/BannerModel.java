package com.example.myapplicationliza.Models;

public class BannerModel {
   public  BannerModel(){
      ////emty constructor////////
   }
   private int banner_img;

   public BannerModel(int banner_img) {
      this.banner_img = banner_img;
   }

   public int getBanner_img() {
      return banner_img;
   }

   public void setBanner_img(int banner_img) {
      this.banner_img = banner_img;
   }
}
