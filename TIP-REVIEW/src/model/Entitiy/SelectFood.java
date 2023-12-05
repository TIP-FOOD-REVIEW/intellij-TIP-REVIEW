package model.Entitiy;

public class SelectFood {
    private Long selectFoodId;

    public Long getSelectFoodId() {
        return selectFoodId;
    }

    public void setSelectFoodId(Long selectFoodId) {
        this.selectFoodId = selectFoodId;
    }

    private Long foodId;
    private Long reviewId;
    private Long storeId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;

    }
}
