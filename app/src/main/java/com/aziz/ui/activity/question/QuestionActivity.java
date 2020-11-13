package com.aziz.ui.activity.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import android.os.Bundle;
import android.view.View;

import com.aziz.R;
import com.aziz.data.adapter.OnClickNextItemQA;
import com.aziz.data.adapter.QuestionAdapter;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.databinding.ActivityQuestionBinding;

import java.util.ArrayList;

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


    @Override
    public void nextItem(int position) {
        binding.rv.scrollToPosition(position + 1);
    }
}