package com.aziz.data.pagerAdapter.model;

public class QuestionModel {
    public String question;
    public String first_answer;
    public String second_answer;
    public String third_answer;
    public String fourth_answer;
    public String fifth_answer;
    public String sixth_answer;
    public boolean type;

    public QuestionModel(String question, String first_answer, String second_answer, String third_answer, String fourth_answer, String fifth_answer, String sixth_answer, boolean type) {
        this.question = question;
        this.first_answer = first_answer;
        this.second_answer = second_answer;
        this.third_answer = third_answer;
        this.fourth_answer = fourth_answer;
        this.fifth_answer = fifth_answer;
        this.sixth_answer = sixth_answer;
        this.type = type;
    }
}
