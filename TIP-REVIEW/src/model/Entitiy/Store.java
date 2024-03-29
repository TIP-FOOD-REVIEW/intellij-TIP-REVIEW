package model.Entitiy;

public class Store {
    private Long storeId;
    private String storeName;
    private String address;
    private String phone;
    private String description;
    private String location;
    private Double rating;
    private Long ReviewCount;

    public Long getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        ReviewCount = reviewCount;
    }

    public Double getRating() {return rating;}

    public void setRating(Double rating) {this.rating = rating;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}


    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
