/*
Laptop model
 */
package com.apusilicon.blog.classes.real;

import com.apusilicon.blog.classes.imaginery.Links;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author earlcameron
 */
public class Laptop {
    private int id;
    private String brand;
    private String family;
    private double weight;
    private String sku;
    private double price;
    private APU apu;
    private Display display;
    private Ram ram;
    private Storage storage;
    private Accessory accessory;
    private Battery battery;
    private List<Links> links = new ArrayList<>();
}
