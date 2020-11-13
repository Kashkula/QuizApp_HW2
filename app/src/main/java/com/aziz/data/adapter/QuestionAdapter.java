package com.aziz.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aziz.R;
import com.aziz.data.model.question.QuestionModel;
import com.aziz.databinding.ListItemBinding;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    protected ArrayList<QuestionModel> list;
    protected OnClickNextItemQA onClickNextItemQA;
    protected ListItemBinding binding;

    public QuestionAdapter(ArrayList<QuestionModel> list, OnClickNextItemQA onClickNextItemQA) {
        this.list = list;
        this.onClickNextItemQA = onClickNextItemQA;
    }

    public void add(QuestionModel model) {
        list.add(model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        //may be and so...
//        binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        String type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding.setVh(this);
        }

        public void bind(QuestionModel model) {
            type = model.getType();
            binding.setQm(model);
            if (type.equals("False")) {
                binding.linearBoolean.setVisibility(View.GONE);
                binding.linearMultitype.setVisibility(View.VISIBLE);
            } else {
                binding.linearMultitype.setVisibility(View.GONE);
                binding.linearBoolean.setVisibility(View.VISIBLE);
            }
        }

        public void onClick() {
            onClickNextItemQA.nextItem(getAdapterPosition());
        }
    }
}
