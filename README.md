# Customized-Progress-Bar
Multicolored &amp; Segmented Progress Bar

1- Add a ProgressBar to Your Layout
```
   <ProgressBar
                        android:id="@+id/progress_bar_test"
                            style="?android:attr/progressBarStyleHorizontal"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:layout_margin="15dp"
                            android:layout_marginLeft="16dp"
                            android:layout_marginRight="16dp"
                            android:max="100" />

```
2- Create a Custom ProgressDrawable
Custom drawable class to handle the segmented colors for the progress bar.

3- Use the Custom Drawable in Your Activity

Create an array to store the colors for each segment of the progress bar. This array will be used in the custom drawable class to define the colors of the segments:
```
 int[] segmentedProgresBarColors = new int[]{
                Color.RED,      // Color for the first segment
                Color.rgb(255, 165, 0), // Orange
                Color.YELLOW,   // Yellow
                Color.GREEN,  // Green
        };
```
Now, you can use the ProgressDrawable in your activity:
```
        drawable = new ProgressDrawable(segmentedProgresBarColors, Color.LTGRAY); // Gray as the background color

        progressBar.setProgressDrawable(drawable);
        progressBar.setProgress(70);
```

<p align="center">
  <img src="https://github.com/user-attachments/assets/a1e0bef2-0256-4989-9b19-0b2a6b732164" width="350" title="hover text">

</p>
