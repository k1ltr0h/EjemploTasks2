package cl.telematica.tasks2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ThreadActivity extends Activity {
	
	private ProgressBar progressBar;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
				    public void run() {
				    	progressBar.post(new Runnable() {
					        public void run() {
					        	progressBar.setProgress(0);
					        }
					    });
					 
					    for(int i=1; i<=10; i++) {
					        task();
					        progressBar.post(new Runnable() {
					            public void run() {
					            	progressBar.incrementProgressBy(10);
					                }
					            });
					    }
					 
					    runOnUiThread(new Runnable() {
					        public void run() {
					            Toast.makeText(ThreadActivity.this, "Tarea finalizada!",
					                Toast.LENGTH_SHORT).show();
					        }
					    });
				    }
				}).start();
			}
		});
	}
	
	private void task()
	{
	    try {
	        Thread.sleep(1000);
	    } catch(InterruptedException e) {}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thread, menu);
		return true;
	}

}
