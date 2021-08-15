package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    public ImageButton mBackButton;
    @BindView(R.id.profile_neighbour_image)
    public ImageView mImage;
    @BindView(R.id.favoris_button)
    public FloatingActionButton mButtonFav;
    @BindView(R.id.neighbour_name_text)
    public TextView mTextName;
    @BindView(R.id.neighbour_aboutme_text)
    public TextView mTextAbout;
    @BindView(R.id.neighbour_name2_text)
    public TextView mTextName2;
    @BindView(R.id.neighbour_address_text)
    public TextView mTextAddress;
    @BindView(R.id.neighbour_phone_text)
    public TextView mTextPhone;
    @BindView(R.id.neighbour_website_text)
    public TextView mTextWebsite;

    private NeighbourApiService mApiService;
    String mFacebookLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        Intent mIntent = getIntent();
        long id = mIntent.getLongExtra("ID",0);
        Neighbour neighbour = mApiService.getNeighbour(id);


        Glide.with(mImage.getContext())
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mImage);

        mFacebookLink = "https://facebook.com/" + neighbour.getName();

        mTextName.setText(neighbour.getName());
        mTextAbout.setText(neighbour.getAboutMe());
        mTextName2.setText(neighbour.getName());
        mTextAddress.setText(neighbour.getAddress());
        mTextPhone.setText(neighbour.getPhoneNumber());
        mTextWebsite.setText(mFacebookLink);

        setImageFavorite(neighbour);

        mBackButton.setOnClickListener(v -> finish());

        mButtonFav.setOnClickListener(v -> {
            if(!neighbour.getFavorite()) {
                mApiService.addNeighbourFavorite(neighbour);
            }
            else{
                mApiService.removeFavoriteNeighbour(neighbour);
            }
            setImageFavorite(neighbour);
        });
    }

    public void setImageFavorite(Neighbour neighbour) {
        if(!neighbour.getFavorite()) {
            mButtonFav.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        else{
            mButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }
}