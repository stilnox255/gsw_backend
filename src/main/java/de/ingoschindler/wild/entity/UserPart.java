package de.ingoschindler.wild.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class UserPart {

    private Long id;
    private String name;
    private String weight;
    private String price;
    private String whatsapp;
    private String mail;
    private boolean isReh;
    private boolean isWs;
    private boolean isHoney;
    private Date freezeDate;

    public UserPart(Part p) {

        this.id = p.getId();
        BigDecimal w;
        String u;
        Category c = p.getCategory();
        Unit pu = c.getPriceUnit();

        this.name = c.getName();

        if (p.getWeight().compareTo(pu.getConversionFactor()) >= 1) {
            w = p.getWeight().divide(pu.getConversionFactor(), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            u = pu.getShortUnit();
        } else {
            w = p.getWeight().setScale(0, RoundingMode.HALF_UP);
            u = pu.getFactorUnit();

        }
        this.weight = w.toString();
        this.weight += u;

        this.price = c.getPrice().divide(pu.getConversionFactor(), RoundingMode.HALF_UP).multiply(p.getWeight())
                .setScale(2, RoundingMode.HALF_EVEN).toString();
        this.price += pu.getCurrency() + " (" + c.getPrice().setScale(2, RoundingMode.HALF_UP).toString() + pu.getCurrency() + "/"
                + pu.getShortUnit() + ")";

        this.freezeDate = p.getFreezeDate();

        this.isReh = false;
        this.isWs = false;
        this.isHoney = false;

        switch (c.getName()) {
            case "Reh":
                this.isReh = true;
                break;
            case "Wildschwein":
                this.isWs = true;
                break;
            case "Honig":
                this.isHoney = true;
                break;
        }

        this.whatsapp = "Hallo! ich hätte gerne " + name +
                " (" +
                this.weight +
                ") für " +
                this.price +
                ". Lieben Dank!";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isReh() {
        return isReh;
    }

    public void setReh(boolean isReh) {
        this.isReh = isReh;
    }

    public boolean isWs() {
        return isWs;
    }

    public void setWs(boolean isWs) {
        this.isWs = isWs;
    }

    public boolean isHoney() {
        return isHoney;
    }

    public void setHoney(boolean isHoney) {
        this.isHoney = isHoney;
    }

    public Date getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Date freezeDate) {
        this.freezeDate = freezeDate;
    }

}
