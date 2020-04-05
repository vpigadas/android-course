package com.example.channelapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    int[] images = {R.drawable.star_channel,
                    R.drawable.alpha_channel,
                    R.drawable.skai_channel,
                    R.drawable.ant1_channel,
                    R.drawable.mega_channel,
                    R.drawable.open_channel};

    String[] Names = {"STAR",
                        "ALPHA",
                        "SKAI",
                        "ANT1",
                        "MEGA",
                        "OPEN"};

    String[] Websites = {"https://www.star.gr",
                         "https://www.alphatv.gr",
                         "https://www.skai.gr",
                         "https://www.antenna.gr",
                         "https://www.megatv.com",
                         "https://www.tvopen.gr"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        Adapter1 adapter1 = new Adapter1();
        mListView.setAdapter(adapter1);





    }

    class Adapter1 extends BaseAdapter {





        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.list_item,null);

            ImageView mImageView = view.findViewById(R.id.imageView);
            TextView mTextView = view.findViewById(R.id.textView);
            final ImageButton mImageButton = view.findViewById(R.id.imageButton);

            mImageView.setImageResource(images[position]);
            mTextView.setText(Names[position]);


            mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = Websites.toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                    startActivity(intent);

                    }


                  });



            return view;
            }



    }
            }




