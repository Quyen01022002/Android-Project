package com.example.shopfruits.Models;




public class ReviewModel {

	    private int reviewID;


	    private int orderID;
	    

	    private int userID;


	    private int productID;


	    private int storeID;

	private String avatar;
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	    private String contents;

	    private float rating;
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


	    private String name;
		public int getReviewID() {
			return reviewID;
		}

		public void setReviewID(int reviewID) {
			this.reviewID = reviewID;
		}

		public int getOrderID() {
			return orderID;
		}

		public void setOrderID(int orderID) {
			this.orderID = orderID;
		}

		public int getUserID() {
			return userID;
		}

		public void setUserID(int userID) {
			this.userID = userID;
		}

		public int getProductID() {
			return productID;
		}

		public void setProductID(int productID) {
			this.productID = productID;
		}

		public int getStoreID() {
			return storeID;
		}

		public void setStoreID(int storeID) {
			this.storeID = storeID;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}

		public float getRating() {
			return rating;
		}

		public void setRating(float rating) {
			this.rating = rating;
		}

		public ReviewModel() {
			super();
		}
	    
}
