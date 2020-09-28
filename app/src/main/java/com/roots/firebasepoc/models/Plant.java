package com.roots.firebasepoc.models;

import java.util.Date;

public class Plant {
    private Species species;
    private int age;
    private Date registrationDate;
    private boolean isBonsaiAble;
    private String origin;
    private int height;
    private int container;
    private boolean isSaleable;

    public Plant(Species species, int age, Date registrationDate, boolean isBonsaiAble, String origin, int height, int container, boolean isSaleable) {
        setSpecies(species);
        setAge(age);
        setRegistrationDate(registrationDate);
        setBonsaiAble(isBonsaiAble);
        setOrigin(origin);
        setHeight(height);
        setContainer(container);
        setSaleable(isSaleable);
    }

    public Species getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public boolean isBonsaiAble() {
        return isBonsaiAble;
    }

    public String getOrigin() {
        return origin;
    }

    public int getHeight() {
        return height;
    }

    public int getContainer() {
        return container;
    }

    public boolean isSaleable() {
        return isSaleable;
    }

    private void setSpecies(Species species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    private void setBonsaiAble(boolean bonsaiAble) {
        isBonsaiAble = bonsaiAble;
    }

    private void setOrigin(String origin) {
        this.origin = origin;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private void setContainer(int container) {
        this.container = container;
    }

    private void setSaleable(boolean saleable) {
        isSaleable = saleable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plant{");
        sb.append("species=").append(getSpecies());
        sb.append(", age=").append(getAge());
        sb.append(", registrationDate=").append(getRegistrationDate());
        sb.append(", isBonsaiAble=").append(isBonsaiAble());
        sb.append(", origin='").append(getOrigin()).append('\'');
        sb.append(", height=").append(getHeight());
        sb.append(", container=").append(getContainer());
        sb.append(", isSaleable=").append(isSaleable());
        sb.append('}');
        return sb.toString();
    }
}
