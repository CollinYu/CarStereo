package edu.up.yu18.carstereo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private TextView radioDisplay;
    private TextView tuning;
    private TextView volume;
    private SeekBar tuningBar;
    private SeekBar volumeBar;
    private Button amButton;
    private Button fmButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private ImageButton powerButton;
    private int amTune;
    private int fmTune;
    private boolean isOff = true;
    private boolean isAM = false;
    private int[] amPresets = {550, 600, 650, 700, 750, 800};
    private double[] fmPresets = {90.9, 92.9, 94.9, 96.9, 98.9, 100.9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioDisplay = (TextView)findViewById(R.id.RadioDisplay);
        tuning = (TextView)findViewById(R.id.Tuning);
        volume = (TextView)findViewById(R.id.Volume);
        volumeBar = (SeekBar)findViewById(R.id.VolumeBar);
        tuningBar = (SeekBar)findViewById(R.id.TuningBar);
        powerButton = (ImageButton)findViewById(R.id.powerButton);
        powerButton.setOnClickListener(new powerButtonListener());
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button1.setOnClickListener(new presetListener());
        button2.setOnClickListener(new presetListener());
        button3.setOnClickListener(new presetListener());
        button4.setOnClickListener(new presetListener());
        button5.setOnClickListener(new presetListener());
        button6.setOnClickListener(new presetListener());
        button1.setOnLongClickListener(new presetter());
        button2.setOnLongClickListener(new presetter());
        button3.setOnLongClickListener(new presetter());
        button4.setOnLongClickListener(new presetter());
        button5.setOnLongClickListener(new presetter());
        button6.setOnLongClickListener(new presetter());
        amButton = (Button) findViewById(R.id.buttonAM);
        fmButton = (Button) findViewById(R.id.buttonFM);
        amButton.setOnClickListener(new amFMButtonListener());
        fmButton.setOnClickListener(new amFMButtonListener());
        tuningBar.setOnSeekBarChangeListener(new tunerListener());
    }
    private class powerButtonListener implements View.OnClickListener{
        public void onClick(View v)
        {
            if(isOff) {
                radioDisplay.setTextColor(0xFF00FF00);
                tuning.setTextColor(0xFF00FF00);
                volume.setTextColor(0xFF00FF00);
                powerButton.setImageResource(R.drawable.power_button);

                isOff = false;
            }
            else {
                radioDisplay.setTextColor(0xFF000000);
                tuning.setTextColor(0xFF000000);
                volume.setTextColor(0xFF5F5252);
                powerButton.setImageResource(R.drawable.power_buttonoff);
                isOff = true;
            }
        }
    }

    private class amFMButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            if(!isOff) {
                if (v == amButton) {
                    radioDisplay.setText("AM Radio");
                    tuningBar.setMax(117);
                    isAM = true;
                    tuningBar.setProgress(0);
                    tuning.setText("" + 530);
                } else if (v == fmButton) {
                    radioDisplay.setText("FM Radio");
                    tuningBar.setMax(99);
                    isAM = false;
                    tuningBar.setProgress(0);
                    tuning.setText("" + 88.1);
                }
            }
        }
    }

    private class tunerListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int tuneAM = 530 + i * 10;
            double placeholder = (double) i;
            double tuneFM = 88.1 + placeholder/5;
            if (isAM) {
                tuning.setText("" + tuneAM);
            } else {
                tuning.setText(String.format("%.1f", tuneFM));
            }

        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
        private class presetListener implements View.OnClickListener {
            public void onClick(View v) {
                if (!isOff) {
                    if (isAM) {
                        if (v == button1) {
                            tuningBar.setProgress((amPresets[0] - 530) / 10);
                            tuning.setText("" + amPresets[0]);
                        } else if (v == button2) {
                            tuningBar.setProgress((amPresets[1] - 530) / 10);
                            tuning.setText("" + amPresets[1]);
                        } else if (v == button3) {
                            tuningBar.setProgress((amPresets[2] - 530) / 10);
                            tuning.setText("" + amPresets[2]);
                        } else if (v == button4) {
                            tuningBar.setProgress((amPresets[3] - 530) / 10);
                            tuning.setText("" + amPresets[3]);
                        } else if (v == button5) {
                            tuningBar.setProgress((amPresets[4] - 530) / 10);
                            tuning.setText("" + amPresets[4]);
                        } else if (v == button6) {
                            tuningBar.setProgress((amPresets[5] - 530) / 10);
                            tuning.setText("" + amPresets[5]);
                        }
                    } else if (!isAM) {
                        if (v == button1) {
                            int bla0 = (int) (fmPresets[0] - 88.1);
                            tuningBar.setProgress(bla0 * 5);
                            tuning.setText("" + fmPresets[0]);
                        } else if (v == button2) {
                            int bla1 = (int) (fmPresets[1] - 88.1);
                            tuningBar.setProgress(bla1 * 5);
                            tuning.setText("" + fmPresets[1]);
                        } else if (v == button3) {
                            int bla2 = (int) (fmPresets[2] - 88.1);
                            tuningBar.setProgress(bla2 * 5);
                            tuning.setText("" + fmPresets[2]);
                        } else if (v == button4) {
                            int bla3 = (int) (fmPresets[3] - 88.1);
                            tuningBar.setProgress(bla3 * 5);
                            tuning.setText("" + fmPresets[3]);
                        } else if (v == button5) {
                            int bla4 = (int) (fmPresets[4] - 88.1);
                            tuningBar.setProgress(bla4 * 5);
                            tuning.setText("" + fmPresets[4]);
                        } else if (v == button6) {
                            int bla5 = (int) (fmPresets[5] - 88.1);
                            tuningBar.setProgress(bla5 * 5);
                            tuning.setText("" + fmPresets[5]);
                        }
                    }
                }

            }
        }
            private class presetter implements View.OnLongClickListener {
                public boolean onLongClick(View v) {
                    if (!isOff) {
                        if (isAM) {
                            if (v == button1) {
                                amPresets[0] = tuningBar.getProgress()*10 + 530;
                            } else if (v == button2) {
                                amPresets[1] = tuningBar.getProgress()*10 + 530;
                            } else if (v == button3) {
                                amPresets[2] = tuningBar.getProgress()*10 + 530;
                            } else if (v == button4) {
                                amPresets[3] = tuningBar.getProgress()*10 + 530;
                            } else if (v == button5) {
                                amPresets[4] = tuningBar.getProgress()*10 + 530;
                            } else if (v == button6) {
                                amPresets[5] = tuningBar.getProgress()*10 + 530;
                            }
                        } else if (!isAM) {
                            if (v == button1) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[0] = place/5 + 88.1;
                            } else if (v == button2) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[1] = place/5 + 88.1;
                            } else if (v == button3) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[2] = place/5 + 88.1;
                            } else if (v == button4) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[3] = place/5 + 88.1;
                            } else if (v == button5) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[4] = place/5 + 88.1;
                            } else if (v == button6) {
                                double place = (double) tuningBar.getProgress();
                                fmPresets[5] = place/5 + 88.1;
                            }
                        }
                    }
                    return true;
            }
        }
}

