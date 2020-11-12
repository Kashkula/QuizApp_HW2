package com.aziz.ui.fragment.activity.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import android.os.Bundle;
import android.view.View;

import com.aziz.R;
import com.aziz.data.pagerAdapter.adapter.OnClickNextItemQA;
import com.aziz.data.pagerAdapter.adapter.QuestionAdapter;
import com.aziz.data.pagerAdapter.model.QuestionModel;
import com.aziz.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity implements OnClickNextItemQA {
    protected ActivityQuestionBinding binding;
    protected QuestionAdapter adapter;
    protected ArrayList<QuestionModel> list;
    protected String[] question = {"How are you?", "How are you?", "How are you?", "How are you?", "How are you?", "How are you?", "How are you?", "How are you?", "How are you?", "How are you?"};
    protected String[] answers = {"Nice", "hai", "Excellent", "Nice", "hai", "Excellent", "Nice", "hai", "Excellent", "So good"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        onClick();
        listAdd();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        list = new ArrayList<>();
        adapter = new QuestionAdapter(list, this);
        binding.rv.setAdapter(adapter);
        rvSet();
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);
    }

    public void onClick() {
        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void rvSet() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rv.setLayoutManager(layoutManager);
    }

    public void listAdd() {
        Random random = new Random();
        for (int i = 0; i < question.length; i++) {
            adapter.add(new QuestionModel(question[i] + i, answers[random.nextInt(10)], answers[random.nextInt(10)], answers[random.nextInt(10)], answers[random.nextInt(10)], answers[random.nextInt(10)], answers[random.nextInt(10)], random.nextBoolean()));
        }
    }

    @Override
    public void nextItem(int position) {
        binding.rv.scrollToPosition(position + 1);
    }
}