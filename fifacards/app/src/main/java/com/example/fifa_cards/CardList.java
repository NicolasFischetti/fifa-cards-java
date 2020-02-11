package com.example.fifa_cards;

public class CardList {
    private Integer id;
    private String name;
    private String playerImage;
    private String position;
    private Integer pac;
    private Integer sho;
    private Integer pas;
    private Integer dri;
    private Integer def;
    private Integer phy;
    private Boolean isSelected = false;

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selection) {
        isSelected = selection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public Integer getPac() {
        return pac;
    }

    void setPac(Integer pac) {
        this.pac = pac;
    }

    public Integer getSho() {
        return sho;
    }

    void setSho(Integer sho) {
        this.sho = sho;
    }

    public Integer getPas() {
        return pas;
    }

    void setPas(Integer pas) {
        this.pas = pas;
    }

    public Integer getDri() {
        return dri;
    }

    void setDri(Integer dri) {
        this.dri = dri;
    }

    public Integer getDef() {
        return def;
    }

    void setDef(Integer def) {
        this.def = def;
    }

    public Integer getPhy() {
        return phy;
    }

    void setPhy(Integer phy) {
        this.phy = phy;
    }
}
