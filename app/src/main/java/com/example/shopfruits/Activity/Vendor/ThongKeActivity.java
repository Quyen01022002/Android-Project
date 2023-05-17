package com.example.shopfruits.Activity.Vendor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.MainActivity;
import com.example.shopfruits.Activity.User.ThanhToanDialog;
import com.example.shopfruits.Adapter.ProductAdapterTop5;
import com.example.shopfruits.Adapter.ProductAdapterTop5OfStore;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.ProductOnTop5;
import com.example.shopfruits.Models.ThongKeModel;
import com.example.shopfruits.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    APIService apiService;
    int storeID;
    private CombinedChart mChart;
    List<ThongKeModel> listDoanhThuNam;
    LineData d = new LineData();
    ProductAdapterTop5OfStore productAdaptertop5ofStore;
    RecyclerView rctop5;
    ProductOnTop5 pro;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thongke);
        Intent intent = getIntent();
        String idStore= intent.getStringExtra("idst");

        apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getThongKeTheoThang(2023,Integer.parseInt(idStore)).enqueue(new Callback<List<ThongKeModel>>() {
            @Override
            public void onResponse(Call<List<ThongKeModel>> call, Response<List<ThongKeModel>> response) {
                listDoanhThuNam=response.body();

                ArrayList<Entry> entries = new ArrayList<>();
                int i=0;
                for (int index = 0; index < 12; index++) {
                    if ((index +1)==listDoanhThuNam.get(i).getThang()) {
                        entries.add(new Entry(index, listDoanhThuNam.get(i).getDoanhthuthang()));
                        if (listDoanhThuNam.size() == (i + 1)) {
                            i = i;
                        } else {
                            i++;
                        }
                    }
                    else {
                        entries.add(new Entry(index, 0));
                    }
                }

                LineDataSet set = new LineDataSet(entries, "Doanh thu theo tháng");
                set.setColor(Color.GREEN);
                set.setLineWidth(2.5f);
                set.setCircleColor(Color.GREEN);
                set.setCircleRadius(5f);
                set.setFillColor(Color.GREEN);
                set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set.setDrawValues(true);
                set.setValueTextSize(10f);
                set.setValueTextColor(Color.BLACK);

                set.setAxisDependency(YAxis.AxisDependency.LEFT);
                d.addDataSet(set);
                vebieudo(set);
            }

            @Override
            public void onFailure(Call<List<ThongKeModel>> call, Throwable t) {
                Toast.makeText(ThongKeActivity.this, "Không load được api", Toast.LENGTH_SHORT).show();
            }
        });

        rctop5=findViewById(R.id.top5SpBanChay);
        GetProductTop5(Integer.parseInt(idStore));
        back=findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ThongKeActivity.this, VendorActivity.class);
                startActivity(it);
            }
        });



    }

    void vebieudo(LineDataSet data){

        mChart = (CombinedChart) findViewById(R.id.piechart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

        CombinedData dataCombined = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) data);

        dataCombined.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(dataCombined);
        mChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Doanh thu tháng " + (int)h.getX() +": " + e.getY() + "vnđ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected() {

    }

    private void GetProductTop5(int idStore) {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);


        apiService.getTop5ProOfStore(idStore,2023).enqueue(new Callback<List<ProductOnTop5>>() {
            @Override
            public void onResponse(Call<List<ProductOnTop5>> call, Response<List<ProductOnTop5>> response) {
                if(response.isSuccessful()){
                    List<ProductOnTop5> productListtop5=response.body();
                    List<ProductOnTop5> newlisttop5 = new ArrayList<>();
                    int n = productListtop5.size();
                    for (int index=0; index<n;index++){
                        pro = new ProductOnTop5();
                        pro.setProductID(productListtop5.get(index).getProductID());
                        pro.setCountSold(productListtop5.get(index).getCountSold());
                        pro.setName(productListtop5.get(index).getName());
                        pro.setImg(productListtop5.get(index).getImg());
                        newlisttop5.add(pro);
                    }

                    productAdaptertop5ofStore=new ProductAdapterTop5OfStore(newlisttop5, ThongKeActivity.this);
                    rctop5.setHasFixedSize(true);
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager((getApplicationContext()) , LinearLayoutManager.VERTICAL, false);

                    rctop5.setLayoutManager(layoutManager);
                    rctop5.setAdapter(productAdaptertop5ofStore);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<ProductOnTop5>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }



}