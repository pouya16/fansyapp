package com.example.esppad.fansy4.Classes;

import org.json.JSONArray;
import org.json.JSONObject;

public class Categories {
    private String categoryTitle;
    private boolean categoryIsVisible;
    private boolean categoryIsActive;
    private int categoryUserID;
    private String categoryImage;
    private String categoryDateModified;
    private JSONObject secondCategoriesJSONArray;
    private int numberOfSecondCategories = 0;
    public Categories(String categoryTitle, boolean categoryIsVisible, boolean categoryIsActive, int categoryUserID, String categoryImage, String categoryDateModified, JSONObject secondCategoriesJSONArray) {
        this.categoryTitle = categoryTitle;
        this.categoryIsVisible = categoryIsVisible;
        this.categoryIsActive = categoryIsActive;
        this.categoryUserID = categoryUserID;
        this.categoryImage = categoryImage;
        this.categoryDateModified = categoryDateModified;
        this.secondCategoriesJSONArray = secondCategoriesJSONArray;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public boolean isCategoryIsVisible() {
        return categoryIsVisible;
    }

    public boolean isCategoryIsActive() {
        return categoryIsActive;
    }

    public int getCategoryUserID() {
        return categoryUserID;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public String getCategoryDateModified() {
        return categoryDateModified;
    }

    public JSONObject getSecondCategoriesJSONArray() {
        return secondCategoriesJSONArray;
    }

    public int getNumberOfSecondCategories() {
        return numberOfSecondCategories;
    }

    private class SecondCategory{
        private String secCatTitle;
        private String secCatImg;
        private boolean secCatVisible;
        private boolean secCatActive;
        private int secCatUserID;
        private String secCatCategoryID;
        private String secCatID;
        private String secCatDateModified;
        private int numberOfLastCategory=0;
        private JSONArray lastCategoryJSONArray;

        public SecondCategory(String secCatTitle, String secCatImg, boolean secCatVisible, boolean secCatActive, int secCatUserID, String secCatCategoryID, String secCatID, String secCatDateModified, JSONArray lastCategoryJSONArray) {
            this.secCatTitle = secCatTitle;
            this.secCatImg = secCatImg;
            this.secCatVisible = secCatVisible;
            this.secCatActive = secCatActive;
            this.secCatUserID = secCatUserID;
            this.secCatCategoryID = secCatCategoryID;
            this.secCatID = secCatID;
            this.secCatDateModified = secCatDateModified;
            this.lastCategoryJSONArray = lastCategoryJSONArray;
        }

        public String getSecCatTitle() {
            return secCatTitle;
        }

        public String getSecCatImg() {
            return secCatImg;
        }

        public boolean isSecCatVisible() {
            return secCatVisible;
        }

        public boolean isSecCatActive() {
            return secCatActive;
        }

        public int getSecCatUserID() {
            return secCatUserID;
        }

        public String getSecCatCategoryID() {
            return secCatCategoryID;
        }

        public String getSecCatID() {
            return secCatID;
        }

        public String getSecCatDateModified() {
            return secCatDateModified;
        }

        public int getNumberOfLastCategory() {
            return numberOfLastCategory;
        }

        public JSONArray getLastCategoryJSONArray() {
            return lastCategoryJSONArray;
        }
        public void createLastCategories(JSONArray lastCategoriesArray){

        }

        private class LastCategory{
            private String finalCatTitle;
            private String finalCatPic;
            private boolean finalCatVisible;
            private boolean finalCatisActive;
            private int finalCatUSerID;
            private String finalCatID;
            private String catID;

            public LastCategory(String finalCatTitle, String finalCatPic, boolean finalCatVisible, boolean finalCatisActive, int finalCatUSerID, String finalCatID, String catID) {
                this.finalCatTitle = finalCatTitle;
                this.finalCatPic = finalCatPic;
                this.finalCatVisible = finalCatVisible;
                this.finalCatisActive = finalCatisActive;
                this.finalCatUSerID = finalCatUSerID;
                this.finalCatID = finalCatID;
                this.catID = catID;
            }

            public String getFinalCatTitle() {
                return finalCatTitle;
            }

            public String getFinalCatPic() {
                return finalCatPic;
            }

            public boolean isFinalCatVisible() {
                return finalCatVisible;
            }

            public boolean isFinalCatisActive() {
                return finalCatisActive;
            }

            public int getFinalCatUSerID() {
                return finalCatUSerID;
            }

            public String getFinalCatID() {
                return finalCatID;
            }

            public String getCatID() {
                return catID;
            }
        }

    }
}
