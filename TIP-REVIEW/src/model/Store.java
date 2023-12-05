package model;

public class Store {
    private Long storeId;
    private String name;
    private String address;
    private String phone;
    private Byte[] imageUrl;

    public Byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
