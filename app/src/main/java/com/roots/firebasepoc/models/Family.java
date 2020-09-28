package com.roots.firebasepoc.models;

public class Family {

    private String name;
    private boolean isStratifaible;
    private String description;

    public Family(String name, boolean isStratifaible, String description) {
        setName(name);
        setStratifaible(isStratifaible);
        setDescription(description);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setStratifaible(boolean stratifaible) {
        isStratifaible = stratifaible;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public boolean isStratifaible() {
        return isStratifaible;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Family{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", isStratifaible=").append(isStratifaible());
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
