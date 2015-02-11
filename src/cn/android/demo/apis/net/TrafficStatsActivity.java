package cn.android.demo.apis.net;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.net.TrafficStats;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
	static long  getMobileRxBytes()  //获取通过Mobile连接收到的字节总数，不包含WiFi  
	static long  getMobileRxPackets()  //获取Mobile连接收到的数据包总数,不包含WiFi  
	static long  getMobileTxBytes()  //Mobile发送的总字节数  
	static long  getMobileTxPackets()  //Mobile发送的总数据包数  
	
	static long  getTotalRxBytes()  //获取总的接受字节数，包含Mobile和WiFi等  
	static long  getTotalRxPackets()  //总的接受数据包数，包含Mobile和WiFi等  
	static long  getTotalTxBytes()  //总的发送字节数，包含Mobile和WiFi等  
	static long  getTotalTxPackets()  //发送的总数据包数，包含Mobile和WiFi等  
	
	static long  getUidRxBytes(int uid)  //获取某个网络UID的接受字节数  
	static long  getUidTxBytes(int uid) //获取某个网络UID的发送字节数  
*/

/**
 * 
 * 流量统计 Class android.net.TrafficStats provides network traffic statistics.
 * These statistics include bytes transmitted and received and network packets
 * transmitted and received, over all interfaces, over the mobile interface, and
 * on a per-UID basis.
 * 
 * These statistics may not be available on all platforms. If the statistics are
 * not supported by this device, UNSUPPORTED will be returned.
 * 
 * @author Elroy
 * 
 */
public class TrafficStatsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_traffic_stats);
		final TextView textMobileRxBytes = (TextView) findViewById(R.id.MobileRxBytes);
		final TextView textMobileRxPackets = (TextView) findViewById(R.id.MobileRxPackets);
		final TextView textMobileTxBytes = (TextView) findViewById(R.id.MobileTxBytes);
		final TextView textMobileTxPackets = (TextView) findViewById(R.id.MobileTxPackets);

		final TextView textTotalRxBytes = (TextView) findViewById(R.id.TotalRxBytes);
		final TextView textTotalRxPackets = (TextView) findViewById(R.id.TotalRxPackets);
		final TextView textTotalTxBytes = (TextView) findViewById(R.id.TotalTxBytes);
		final TextView textTotalTxPackets = (TextView) findViewById(R.id.TotalTxPackets);

		Button buttonLoadTrafficStats = (Button) findViewById(R.id.loadtrafficstats);
		buttonLoadTrafficStats.setOnClickListener(new OnClickListener() {
			// TrafficStatsCompat
			@Override
			public void onClick(View v) {
				if (TrafficStats.getMobileRxBytes() == TrafficStats.UNSUPPORTED) {
					textMobileRxBytes.setText("MobileRxBytes:  UNSUPPORTED");
				} else {
					textMobileRxBytes.setText("MobileRxBytes:  "
							+ String.valueOf(TrafficStats.getMobileRxBytes()));
				}

				if (TrafficStats.getMobileRxPackets() == TrafficStats.UNSUPPORTED) {
					textMobileRxPackets.setText("MobileRxPackets: "
							+ "UNSUPPORTED!");
				} else {
					textMobileRxPackets.setText("MobileRxPackets: "
							+ String.valueOf(TrafficStats.getMobileRxPackets()));
				}

				if (TrafficStats.getMobileTxBytes() == TrafficStats.UNSUPPORTED) {
					textMobileTxBytes.setText("MobileTxBytes: "
							+ "UNSUPPORTED!");
				} else {
					textMobileTxBytes.setText("MobileTxBytes: "
							+ String.valueOf(TrafficStats.getMobileTxBytes()));
				}

				if (TrafficStats.getMobileTxPackets() == TrafficStats.UNSUPPORTED) {
					textMobileTxPackets.setText("MobileTxPackets: "
							+ "UNSUPPORTED!");
				} else {
					textMobileTxPackets.setText("MobileTxPackets: "
							+ String.valueOf(TrafficStats.getMobileTxPackets()));
				}

				if (TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED) {
					textTotalRxBytes.setText("TotalRxBytes: " + "UNSUPPORTED!");
				} else {
					textTotalRxBytes.setText("TotalRxBytes: "
							+ String.valueOf(TrafficStats.getTotalRxBytes()));
				}

				if (TrafficStats.getTotalRxPackets() == TrafficStats.UNSUPPORTED) {
					textTotalRxPackets.setText("TotalRxPackets: "
							+ "UNSUPPORTED!");
				} else {
					textTotalRxPackets.setText("TotalRxPackets: "
							+ String.valueOf(TrafficStats.getTotalRxPackets()));
				}

				if (TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED) {
					textTotalTxBytes.setText("TotalTxBytes: " + "UNSUPPORTED!");
				} else {
					textTotalTxBytes.setText("TotalTxBytes: "
							+ String.valueOf(TrafficStats.getTotalTxBytes()));
				}

				if (TrafficStats.getTotalTxPackets() == TrafficStats.UNSUPPORTED) {
					textTotalTxPackets.setText("TotalTxPackets: "
							+ "UNSUPPORTED!");
				} else {
					textTotalTxPackets.setText("TotalTxPackets: "
							+ String.valueOf(TrafficStats.getTotalTxPackets()));
				}

			}
		});
	}
}
