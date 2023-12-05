package model;

public class Food {
    private Long foodId;
    private String name;
    private Byte[] imageUrl;

    public Byte[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Byte[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private Long storeId;
    private Long price;
}
