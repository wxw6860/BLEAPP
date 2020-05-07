package com.wxwteam.bleapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clj.fastble.BleManager;
import com.clj.fastble.data.BleDevice;
import com.wxwteam.bleapp.R;

import java.util.List;

public class ScanBleDevicesAdapter extends RecyclerView.Adapter {

    Context context;
    List<BleDevice> data;

    public ScanBleDevicesAdapter(Context context, List<BleDevice> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item =  LayoutInflater.from(context).inflate(R.layout.item_scan_ble_devices, parent, false);
        return new ScanBleDevicesHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (data == null || data.size() <= position) {
            return;
        }
        BleDevice bleDevice = data.get(position);
        if (bleDevice == null) {
            return;
        }

        ((ScanBleDevicesHolder) holder).name.setText(TextUtils.isEmpty(bleDevice.getName()) ? "null" : bleDevice.getName());
        ((ScanBleDevicesHolder) holder).mac.setText(TextUtils.isEmpty(bleDevice.getMac()) ? "null" : bleDevice.getMac());
        ((ScanBleDevicesHolder) holder).rssi.setText(bleDevice.getRssi() + "");
        ((ScanBleDevicesHolder) holder).rssi.setText(bleDevice.getDevice().getType() + "");

        boolean isConnected = BleManager.getInstance().isConnected(bleDevice);
        ((ScanBleDevicesHolder) holder).btn_bind.setVisibility(isConnected ? View.GONE : View.VISIBLE);
        ((ScanBleDevicesHolder) holder).btn_unbind.setVisibility(isConnected ? View.VISIBLE : View.GONE);
        ((ScanBleDevicesHolder) holder).btn_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onConnect(bleDevice);
                }
            }
        });

        ((ScanBleDevicesHolder) holder).btn_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDisConnect(bleDevice);
                }
            }
        });

        ((ScanBleDevicesHolder) holder).item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    mListener.onDetail(bleDevice);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ScanBleDevicesHolder extends RecyclerView.ViewHolder {

        View item;
        TextView name;
        TextView mac;
        TextView btn_bind;
        TextView btn_unbind;
        TextView rssi;


        public ScanBleDevicesHolder(@NonNull View itemView) {
            super(itemView);
            this.item = itemView;
            this.name = itemView.findViewById(R.id.name);
            this.mac = itemView.findViewById(R.id.mac);
            this.btn_bind = itemView.findViewById(R.id.btn_bind);
            this.btn_unbind = itemView.findViewById(R.id.btn_unbind);
            this.rssi = itemView.findViewById(R.id.rssi);

        }


    }


    public interface OnDeviceClickListener {
        void onConnect(BleDevice bleDevice);

        void onDisConnect(BleDevice bleDevice);

        void onDetail(BleDevice bleDevice);
    }

    private OnDeviceClickListener mListener;

    public void setOnDeviceClickListener(OnDeviceClickListener listener) {
        this.mListener = listener;
    }

}
