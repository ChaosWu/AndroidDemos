package cn.android.demo.apis.project.rss;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDetails extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.project_rss_show_details);
		TextView detailsTitle = (TextView) findViewById(R.id.detailstitle);
		TextView detailsDescription = (TextView) findViewById(R.id.detailsdescription);
		TextView detailsPubdate = (TextView) findViewById(R.id.detailspubdate);
		TextView detailsLink = (TextView) findViewById(R.id.detailslink);

		Bundle bundle = this.getIntent().getExtras();

		detailsTitle.setText(bundle.getString("keyTitle"));
		detailsDescription.setText(bundle.getString("keyDescription"));
		detailsPubdate.setText(bundle.getString("keyPubdate"));
		detailsLink.setText(bundle.getString("keyLink"));

	}
}
