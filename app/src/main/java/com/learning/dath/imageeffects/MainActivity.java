package com.learning.dath.imageeffects;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;


public class MainActivity extends AppCompatActivity {

    ImageView dathImageView;
    Drawable  penguins;
    Bitmap    bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dathImageView = findViewById(R.id.dathImageView);

        penguins =  getResources().getDrawable(R.drawable.imagepenguin);
        bitmapImage = ((BitmapDrawable) penguins).getBitmap();
        Bitmap newPhoto = invertImage(bitmapImage);
        dathImageView.setImageBitmap(newPhoto);

        MediaStore.Images.Media.insertImage(getContentResolver(), newPhoto,"title","description"); // this will store the new formatted image into your phone gallery

//        Drawable[] layers = new Drawable[2];
//        layers[0] = getResources().getDrawable(R.drawable.imagepenguin);
//        layers[1] = getResources().getDrawable(R.drawable.imageframe);
//        LayerDrawable layerDrawable = new LayerDrawable(layers);
//        dathImageView.setImageDrawable(layerDrawable);

    }

//    Invert a bitmap Image
    public static Bitmap invertImage(Bitmap orginial) {


        Bitmap finalImage = Bitmap.createBitmap(orginial.getWidth(), orginial.getHeight(), orginial.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = orginial.getHeight();
        int width  = orginial.getWidth();


        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                pixelColor = orginial.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(x,y, Color.argb(A, R, G, B));
            }
        }
        return finalImage;

    }


}
