package ma.ensaf.newsapp;

public class CategoryRVModal
{
    String category;
    String categoryUrlImage;

    public CategoryRVModal(String category, String categoryUrlImage) {
        this.category = category;
        this.categoryUrlImage = categoryUrlImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryUrlImage() {
        return categoryUrlImage;
    }

    public void setCategoryUrlImage(String categoryUrlImage) {
        this.categoryUrlImage = categoryUrlImage;
    }
}
