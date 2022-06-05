package com.paz.razabi.driving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class HomeMenuTemplateActivity extends AppCompatActivity {
    private Intent serviceIntent;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();

        if (itemID == R.id.opLogout) {
            FirebaseAuth.getInstance().signOut();
            Globals.firstEnter = true;
            startActivity(new Intent(this , LoginActivity.class));
        }
        if(itemID == R.id.opHomePage){
            startActivity(new Intent(this , HomeActivity.class));
        }
        if(itemID == R.id.opStartMusic){
            serviceIntent = new Intent(getApplicationContext(), MyService.class);
            startService(new Intent(getApplicationContext(), MyService.class));
        }
        if(itemID == R.id.opStopMusic){
            serviceIntent = new Intent(getApplicationContext(), MyService.class);
            stopService(new Intent(getApplicationContext(), MyService.class));
        }

        return super.onOptionsItemSelected(item);
    }
}