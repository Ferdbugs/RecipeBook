package com.example.recipebook;

public class RecipeGet {

        int _id;
        String Title,Instructions,Rating;

        public RecipeGet(){}
        public RecipeGet(int Id, String Title, String Instructions, String Rating){
            this._id= Id;
            this.Title = Title;
            this.Instructions = Instructions;
            this.Rating = Rating;
        }
        public RecipeGet(String Title, String Instructions, String Rating){
            this.Title = Title;
            this.Instructions = Instructions;
            this.Rating = Rating;
        }
        public void setID(int id) {
            this._id = id;
        }
        public int getID() {
            return this._id;
        }
        public String getTitle()
        {
            return Title;
        }
        public void setTitle(String Title)
        {
            this.Title=Title;
        }
        public String getInstructions()
        {
            return Instructions;
        }
        public void setInstructions(String Instructions)
        {
            this.Instructions=Instructions;
        }
        public String getRatings()
        {
            return Rating;
        }
        public void setRatings(String Rating)
        {
            this.Rating=Rating;
        }

}
