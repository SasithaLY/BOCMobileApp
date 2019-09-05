package com.example.sasitha.bocmobileapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.wdullaer.materialdatetimepicker.Utils;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class OwnAccountTransactionSuccessFragment extends Fragment {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView id, fromAcc, toAcc, amount, date, txtTime, description, saving;
    Button home, save;

    String path;

    String signature_pdf_;

    Button btn, btnScroll;
    LinearLayout llPdf;
    Bitmap bitmap;

    String name = "pdffromlayout";

    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.own_account_transfer_success, null);

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle2 = getArguments();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        saving = (TextView) view.findViewById(R.id.textViewSaving);
        progressBar.setVisibility(View.GONE);
        saving.setVisibility(View.GONE);

        id = (TextView) view.findViewById(R.id.textViewID);
        fromAcc = (TextView) view.findViewById(R.id.textViewFromACC);
        toAcc = (TextView) view.findViewById(R.id.textViewToACC);
        amount = (TextView) view.findViewById(R.id.textAmount);
        date = (TextView) view.findViewById(R.id.textViewDateTime);
        description = (TextView) view.findViewById(R.id.textViewDesc);

        home = (Button) view.findViewById(R.id.buttonHome2);
        save = (Button) view.findViewById(R.id.buttonSavePDF);

        llPdf = (LinearLayout) view.findViewById(R.id.constraintLayoutPrint);



        if(bundle2 != null){

            id.setText(bundle2.getString("id"));
            fromAcc.setText(bundle2.getString("fromAcc"));
            toAcc.setText(bundle2.getString("toAcc"));
            amount.setText(bundle2.getString("amount"));
            date.setText(bundle2.getString("date"));
            description.setText(bundle2.getString("description"));


        }



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentD = new DashboardFragment();
                fragmentTransaction.replace(R.id.screen_area, fragmentD);

                fragmentTransaction.commit();
                getActivity().setTitle("Home");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = id.getText().toString();
                Log.d("size"," "+llPdf.getWidth() +"  "+llPdf.getWidth());
                bitmap = loadBitmapFromView(llPdf, llPdf.getWidth(), llPdf.getHeight());
                createPdf();
                //openGeneratedPDF();
            }
        });






    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void createPdf(){

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pdffromlayout.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();


        int delay = 3000;

        progressBar.setVisibility(View.VISIBLE);
        saving.setVisibility(View.VISIBLE);

        countDownTimer.start();

//        new Timer().schedule(
//                new TimerTask(){
//
//                    @Override
//                    public void run(){
//
//                        //if you need some code to run when the delay expires
//
//                    }
//
//                }, delay);








    }

    public CountDownTimer countDownTimer =
            new CountDownTimer(3000, 100) {
                public void onTick(long millisUntilFinished) {
                    progressBar.setProgress(Math.abs((int) millisUntilFinished / 100 - 100));

                }

                @Override
                public void onFinish() {
                    progressBar.setVisibility(View.GONE);
                    saving.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "PDF is created!!!", Toast.LENGTH_SHORT).show();
                    openGeneratedPDF();
                }
            };

    private void openGeneratedPDF(){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pdffromlayout.pdf");
        if (file.exists())
        {


            try
            {
                //Intent intent=new Intent(Intent.ACTION_VIEW);
                //Uri uri = Uri.fromFile(file);
                //intent.setDataAndType(uri, "application/pdf");
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);


                Uri uri;
                if (Build.VERSION.SDK_INT < 24) {
                    uri = Uri.fromFile(file);
                } else {
                    uri = Uri.parse(file.getPath()); // My work-around for new SDKs, causes ActivityNotFoundException in API 10.
                }
                Intent viewFile = new Intent(Intent.ACTION_VIEW);

                viewFile.setDataAndType(uri, "application/pdf");
                startActivity(viewFile);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(getContext(), "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "No File!", Toast.LENGTH_LONG).show();
        }
    }




}
