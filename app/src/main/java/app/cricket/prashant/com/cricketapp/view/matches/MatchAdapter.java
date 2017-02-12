/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.cricket.prashant.com.cricketapp.view.matches;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.Collections;
import java.util.List;

import app.cricket.prashant.com.cricketapp.R;
import app.cricket.prashant.com.cricketapp.databinding.ContentMainBinding;
import app.cricket.prashant.com.cricketapp.model.GetData;
import app.cricket.prashant.com.cricketapp.viewmodel.ItemViewModel;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchAdapterViewHolder> {

    private List<GetData> mMatchList;

    public MatchAdapter() {
        this.mMatchList = Collections.emptyList();
    }

    @Override
    public MatchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContentMainBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.content_main,
                        parent, false);
        return new MatchAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(MatchAdapterViewHolder holder, int position) {
        holder.bindPeople(mMatchList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMatchList.size();
    }

    public void setMatchList(List<GetData> matchList) {
        this.mMatchList = matchList;
        notifyDataSetChanged();
    }

    public static class MatchAdapterViewHolder extends RecyclerView.ViewHolder {
        ContentMainBinding mItemPeopleBinding;

        public MatchAdapterViewHolder(ContentMainBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }

        void bindPeople(GetData match) {
            if (mItemPeopleBinding.getMatchHolder() == null) {
                mItemPeopleBinding.setMatchHolder(
                        new ItemViewModel(match, itemView.getContext()));
            } else {
                mItemPeopleBinding.getMatchHolder().setmatch(match);
            }
        }
    }
}
