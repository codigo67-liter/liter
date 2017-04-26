package mx.codigo67.liter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;


public class InicioFragment extends Fragment {

    Button btnMapa;
    Button btnEscaner;

    TextView tvValue;

    LinearLayout llCounter;
    TextView tvMsg;
    Button btnFin;

    TextView tvFin;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    public static InicioFragment newInstance() {
        InicioFragment fragment = new InicioFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        tvValue = (TextView) view.findViewById(R.id.tv_value);

        tvMsg = (TextView) view.findViewById(R.id.tv_msg);

        tvFin = (TextView) view.findViewById(R.id.tv_fin);

        llCounter = (LinearLayout) view.findViewById(R.id.ll_counter);

        btnMapa = (Button) view.findViewById(R.id.btn_mapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btnEscaner = (Button) view.findViewById(R.id.btn_escanear);
        btnEscaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch barcode activity.
                Intent intent = new Intent(getActivity(), BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent.putExtra(BarcodeCaptureActivity.UseFlash, false);

                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
        });

        btnFin = (Button) view.findViewById(R.id.btn_fin);
        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMsg.setVisibility(View.GONE);
                llCounter.setVisibility(View.GONE);
                btnFin.setVisibility(View.GONE);
                tvFin.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Log.d("*a*", "---" + R.string.barcode_success);
                    Log.d("*a*", barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                    btnEscaner.setVisibility(View.GONE);
                    btnMapa.setVisibility(View.GONE);
                    tvMsg.setVisibility(View.VISIBLE);
                    llCounter.setVisibility(View.VISIBLE);
                    btnFin.setVisibility(View.VISIBLE);
                    increase();
                } else {
                    Log.d("*a*", "-----" + R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                Log.d("*a*", String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void increase() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                int num = Integer.parseInt(tvValue.getText().toString()) + 1;
                tvValue.setText(String.valueOf(num));
                increase();
            }
        }, 5000);
    }

}