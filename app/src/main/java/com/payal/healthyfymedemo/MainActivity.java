package com.payal.healthyfymedemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.payal.healthyfymedemo.adapter.DateAdapter;
import com.payal.healthyfymedemo.interfaces.SlotsApi;
import com.payal.healthyfymedemo.pojo.Slots;
import com.payal.healthyfymedemo.utility.Utils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<Slots> {
    private ViewPager vp_dates;
    private TextView tv_month;
    private TabLayout tabs;
    public static FetchMonth fetchMonth = null;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://108.healthifyme.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SlotsApi stackOverflowAPI = retrofit.create(SlotsApi.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching data...");

        progressDialog.show();

        Call<Slots> call = stackOverflowAPI.loadSlots();
        call.enqueue(this);

        vp_dates = (ViewPager) findViewById(R.id.vp_dates);
        tv_month = (TextView) findViewById(R.id.tv_month);

        tabs = (TabLayout) findViewById(R.id.tabs);


    }


    @Override
    public void onResponse(Response<Slots> response, Retrofit retrofit) {

        DateAdapter adapter = new DateAdapter(getSupportFragmentManager(), response.body().getDates());


        vp_dates.setAdapter(adapter);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(vp_dates);

        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.appRed));

        if(progressDialog.isShowing())
            progressDialog.dismiss();

        vp_dates.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (fetchMonth != null)
                    tv_month.setText(Utils.getMonth(fetchMonth.getMonthData(position)));
            }

            @Override
            public void onPageSelected(int position) {
               /* if(fetchMonth!=null)
                    tv_month.setText(Utils.getMonth(fetchMonth.getMonthData(position)));
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onFailure(Throwable t) {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
        Toast.makeText(MainActivity.this, "Failed to get data from server.", Toast.LENGTH_SHORT).show();
    }


    public interface FetchMonth {

        public String getMonthData(int position);
    }

}
