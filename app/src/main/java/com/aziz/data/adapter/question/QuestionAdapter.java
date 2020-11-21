package com.aziz.data.adapter.question;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.databinding.ListItemBinding;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    protected ArrayList<QuestionModel> list = new ArrayList<>();
    protected OnClickNextItemQA onClickNextItemQA;
    protected OnClickOpenActivity onClickOpenActivity;
    protected ListItemBinding binding;


    public QuestionAdapter(OnClickNextItemQA onClickNextItemQA, OnClickOpenActivity onClickOpenActivity) {
        this.onClickNextItemQA = onClickNextItemQA;
        this.onClickOpenActivity = onClickOpenActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        //may be and so...
//        binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
//        binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        binding.setQm(list.get(position));
        binding.setVh(holder);
        holder.clear();
        holder.setBackground(list.get(holder.getAdapterPosition()).getBtnPosition());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setQuestions(ArrayList<QuestionModel> questions) {
        this.list = questions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button[] btns_multiple = {binding.btnFirst, binding.btnSecond, binding.btnThird, binding.btnFourth, binding.btnFifth, binding.btnSixth};

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void methods(String text, int position) {
            boolean knopka = false;
            QuestionModel qm = list.get(getAdapterPosition());
            if (qm.isKnopka() && text.equals(qm.getCorrectAnswer())) {
                qm.setKnopka(false);
                knopka = true;
            }
            setBackground(position);
            onClickNextItemQA.nextItem(knopka, getAdapterPosition());
            if (getAdapterPosition() == list.size() - 1)
                onClickOpenActivity.answersMethod();
        }

        private void setBackground(int positionBtn) {
            QuestionModel qm = binding.getQm();
            if (qm.getBtnPosition() == 100)
                qm.setBtnPosition(positionBtn);

            for (int i = 0; i < btns_multiple.length; i++) {
                if (qm.getBtnPosition() == i)
                    dontRepeat(i);
            }
        }

        void dontRepeat(int position) {
            Button btn = btns_multiple[position];
            QuestionModel qm = list.get(getAdapterPosition());
            if (btn.getText().equals(qm.getCorrectAnswer()))
                btn.setBackgroundResource(R.drawable.for_true_variant);
            else {
                correctAnswerByDefault();
                btn.setBackgroundResource(R.drawable.for_false_variant);
            }
        }

        void correctAnswerByDefault() {
            QuestionModel qm = binding.getQm();
            for (Button btn : btns_multiple) {
                if (btn.getText().equals(qm.getCorrectAnswer())) {
                    btn.setTextColor(Color.WHITE);
                    btn.setBackgroundResource(R.drawable.for_true_variant);
                }
            }
        }

        void clear() {
            for (Button btn : btns_multiple) {
                btn.setBackgroundResource(R.drawable.for_variants);
                btn.setTextColor(R.style.ForBtn);
            }
        }
    }
}