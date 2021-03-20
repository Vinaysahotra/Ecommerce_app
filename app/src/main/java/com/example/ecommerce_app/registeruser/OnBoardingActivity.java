package com.example.ecommerce_app.registeruser;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.example.ecommerce_app.R;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_on_boardng);

        AhoyOnboarderCard card1 = new AhoyOnboarderCard("Welcome User!", "We are thrilled to have you on our service.", R.drawable.first);
        AhoyOnboarderCard card2 = new AhoyOnboarderCard("Compare", "Compare Prices of your Favourite products with ease", R.drawable.second);
        AhoyOnboarderCard card3 = new AhoyOnboarderCard("fingerprint", "add fingerprint security to app", R.drawable.third);
        AhoyOnboarderCard card4 = new AhoyOnboarderCard("Enjoy", "search any product across the sites", R.drawable.fourth);

        card1.setBackgroundColor(R.color.black_transparent);
        card2.setBackgroundColor(R.color.black_transparent);
        card3.setBackgroundColor(R.color.black_transparent);
        card4.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        for (AhoyOnboarderCard card : cards) {
            card.setTitleColor(R.color.white);
            card.setIconLayoutParams(400, 600, 5, 5, 5, 5);

            card.setDescriptionColor(R.color.grey_200);
        }
        setFinishButtonTitle("Finish");
        setGradientBackground();
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        setOnboardPages(cards);
    }

    @Override
    public void onFinishButtonPressed() {
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        finish();
    }
}
