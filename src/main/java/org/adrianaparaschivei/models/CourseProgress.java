package org.adrianaparaschivei.models;

public class CourseProgress {
    private int completionPercentage;

    public CourseProgress(){
        this.completionPercentage = 0;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        if(completionPercentage > 100){
            this.completionPercentage = 100;
        }
        else if(completionPercentage < 0){
            this.completionPercentage = 0;
        }
        else{
            this.completionPercentage = completionPercentage;
        }
    }

    @Override
    public String toString() {
        return completionPercentage + "% completed";
    }
}
