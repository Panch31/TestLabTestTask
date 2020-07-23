package util;

public enum Url {

    HOMEPAGE("http://prestashop-automation.qatestlab.com.ua/ru/");

    String link;

    Url(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
