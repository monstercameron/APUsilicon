package com.apusilicon.blog.classes.real;

import com.apusilicon.blog.classes.imaginery.Image;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.CollectionType;

/**
 *
 * @author earlcameron
 */
@Entity
public class TempLaptop {

    @Id
    @GeneratedValue
    int Id;

    private String brand, sku, family, weight, price, link, apu, extgraphics,
            dispsize, dispx, dispy, dispbrand, dispsku, disprefresh,
            disprefreshmin, disprefreshmax, rambrand, ramsku, ramchannel,
            ramtech, ramspeed, ramcap, storbrand, storsku, storphysical,
            stortype, storconnector, storcap, batbrand, batsku, batvol,
            batcap, batcell, batchem, sdcard, dvd, bluray, finger, faceunlock, img;
    
    private String enabled = "false";

    public TempLaptop() {
    }

    @Override
    public String toString() {
        return "TempLaptop{" + "Id=" + Id + ", brand=" + brand + ", sku=" + sku + ", family=" + family + ", weight=" + weight + ", price=" + price + ", link=" + link + ", apu=" + apu + ", extgraphics=" + extgraphics + ", dispsize=" + dispsize + ", dispx=" + dispx + ", dispy=" + dispy + ", dispbrand=" + dispbrand + ", dispsku=" + dispsku + ", disprefresh=" + disprefresh + ", disprefreshmin=" + disprefreshmin + ", disprefreshmax=" + disprefreshmax + ", rambrand=" + rambrand + ", ramsku=" + ramsku + ", ramchannel=" + ramchannel + ", ramtech=" + ramtech + ", ramspeed=" + ramspeed + ", ramcap=" + ramcap + ", storbrand=" + storbrand + ", storsku=" + storsku + ", storphysical=" + storphysical + ", stortype=" + stortype + ", storconnector=" + storconnector + ", storcap=" + storcap + ", batbrand=" + batbrand + ", batsku=" + batsku + ", batvol=" + batvol + ", batcap=" + batcap + ", batcell=" + batcell + ", batchem=" + batchem + ", sdcard=" + sdcard + ", dvd=" + dvd + ", bluray=" + bluray + ", finger=" + finger + ", faceunlock=" + faceunlock + ", img=" + img + '}';
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getApu() {
        return apu;
    }

    public void setApu(String apu) {
        this.apu = apu;
    }

    public String getExtgraphics() {
        return extgraphics;
    }

    public void setExtgraphics(String extgraphics) {
        this.extgraphics = extgraphics;
    }

    public String getDispsize() {
        return dispsize;
    }

    public void setDispsize(String dispsize) {
        this.dispsize = dispsize;
    }

    public String getDispx() {
        return dispx;
    }

    public void setDispx(String dispx) {
        this.dispx = dispx;
    }

    public String getDispy() {
        return dispy;
    }

    public void setDispy(String dispy) {
        this.dispy = dispy;
    }

    public String getDispbrand() {
        return dispbrand;
    }

    public void setDispbrand(String dispbrand) {
        this.dispbrand = dispbrand;
    }

    public String getDispsku() {
        return dispsku;
    }

    public void setDispsku(String dispsku) {
        this.dispsku = dispsku;
    }

    public String getDisprefresh() {
        return disprefresh;
    }

    public void setDisprefresh(String disprefresh) {
        this.disprefresh = disprefresh;
    }

    public String getDisprefreshmin() {
        return disprefreshmin;
    }

    public void setDisprefreshmin(String disprefreshmin) {
        this.disprefreshmin = disprefreshmin;
    }

    public String getDisprefreshmax() {
        return disprefreshmax;
    }

    public void setDisprefreshmax(String disprefreshmax) {
        this.disprefreshmax = disprefreshmax;
    }

    public String getRambrand() {
        return rambrand;
    }

    public void setRambrand(String rambrand) {
        this.rambrand = rambrand;
    }

    public String getRamsku() {
        return ramsku;
    }

    public void setRamsku(String ramsku) {
        this.ramsku = ramsku;
    }

    public String getRamchannel() {
        return ramchannel;
    }

    public void setRamchannel(String ramchannel) {
        this.ramchannel = ramchannel;
    }

    public String getRamtech() {
        return ramtech;
    }

    public void setRamtech(String ramtech) {
        this.ramtech = ramtech;
    }

    public String getRamspeed() {
        return ramspeed;
    }

    public void setRamspeed(String ramspeed) {
        this.ramspeed = ramspeed;
    }

    public String getRamcap() {
        return ramcap;
    }

    public void setRamcap(String ramcap) {
        this.ramcap = ramcap;
    }

    public String getStorbrand() {
        return storbrand;
    }

    public void setStorbrand(String storbrand) {
        this.storbrand = storbrand;
    }

    public String getStorsku() {
        return storsku;
    }

    public void setStorsku(String storsku) {
        this.storsku = storsku;
    }

    public String getStorphysical() {
        return storphysical;
    }

    public void setStorphysical(String storphysical) {
        this.storphysical = storphysical;
    }

    public String getStortype() {
        return stortype;
    }

    public void setStortype(String stortype) {
        this.stortype = stortype;
    }

    public String getStorconnector() {
        return storconnector;
    }

    public void setStorconnector(String storconnector) {
        this.storconnector = storconnector;
    }

    public String getStorcap() {
        return storcap;
    }

    public void setStorcap(String storcap) {
        this.storcap = storcap;
    }

    public String getBatbrand() {
        return batbrand;
    }

    public void setBatbrand(String batbrand) {
        this.batbrand = batbrand;
    }

    public String getBatsku() {
        return batsku;
    }

    public void setBatsku(String batsku) {
        this.batsku = batsku;
    }

    public String getBatvol() {
        return batvol;
    }

    public void setBatvol(String batvol) {
        this.batvol = batvol;
    }

    public String getBatcap() {
        return batcap;
    }

    public void setBatcap(String batcap) {
        this.batcap = batcap;
    }

    public String getBatcell() {
        return batcell;
    }

    public void setBatcell(String batcell) {
        this.batcell = batcell;
    }

    public String getBatchem() {
        return batchem;
    }

    public void setBatchem(String batchem) {
        this.batchem = batchem;
    }

    public String getSdcard() {
        return sdcard;
    }

    public void setSdcard(String sdcard) {
        this.sdcard = sdcard;
    }

    public String getDvd() {
        return dvd;
    }

    public void setDvd(String dvd) {
        this.dvd = dvd;
    }

    public String getBluray() {
        return bluray;
    }

    public void setBluray(String bluray) {
        this.bluray = bluray;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public String getFaceunlock() {
        return faceunlock;
    }

    public void setFaceunlock(String faceunlock) {
        this.faceunlock = faceunlock;
    }
    
    

}
