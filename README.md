# Customized-Progress-Bar
Multicolored &amp; Segmented Progress Bar

1- Create your progress Bar your layout

   <ProgressBar
                            android:id="@+id/progress_bar_test"
                            style="?android:attr/progressBarStyleHorizontal"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_margin="15dp"
                            android:layout_marginLeft="16dp"
                            android:layout_marginRight="16dp"
                            android:max="100" />


2- In your activity file Create the color list 

 int[] segmentedProgresBarColors = new int[]{
                Color.RED,      // Color for the first segment
                Color.rgb(255, 165, 0), // Orange
                Color.YELLOW,   // Yellow
                Color.GREEN,  // Green
        };

        drawable = new ProgressDrawable(segmentedProgresBarColors, Color.LTGRAY); // Gray as the background color

        progressBar.setProgressDrawable(drawable);
        progressBar.setProgress(70);

![image](https://github.com/user-attachments/assets/a1e0bef2-0256-4989-9b19-0b2a6b732164)

